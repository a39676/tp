package taobao.service;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import taobao.pojo.dto.SkuDetailDTO;
import taobao.pojo.dto.SkuFileNameDTO;
import toolPack.ioHandle.FileUtilCustom;

public class SkuDataHandler {

//	String fileName = "1688批量导出SKU_1062730282544_03_11_2026, 19_04_19.txt";

	public static SkuFileNameDTO getSkuFileNameDtoFromFileName(String fileName) {
		SkuFileNameDTO dto = new SkuFileNameDTO();

		// 1. 正则表达式提取各部分字符串
		String regex = TaobaoOption.FILE_NAME_PREFIX + "(\\d+)_(\\d{2}_\\d{2}_\\d{4}),\\s*(\\d{2}_\\d{2}_\\d{2})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fileName);

		if (matcher.find()) {
			String skuId = matcher.group(1); // SKU ID 字符串
			String dateStr = matcher.group(2); // 日期部分: 03_11_2026 (日_月_年)
			String timeStr = matcher.group(3); // 时间部分: 19_04_19 (时_分_秒)

			// 2. 拼接成标准的解析格式字符串，例如: "03-11-2026 19:04:19"
			// 根据你的日期格式（日_月_年），这里使用 dd-MM-yyyy HH:mm:ss 进行对应替换
			String dateTimeCombined = dateStr.replace('_', '-') + " " + timeStr.replace('_', ':');

			// 3. 定义对应的 DateTimeFormatter
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

			// 4. 解析为 LocalDateTime 对象
			LocalDateTime localDateTime = LocalDateTime.parse(dateTimeCombined, formatter);

			// 输出结果
			System.out.println("String SKU ID: " + skuId);
			System.out.println("LocalDateTime 时间日期: " + localDateTime);
			dto.setSkuID(skuId);
			dto.setFileDateTime(localDateTime);
		} else {
			System.out.println("未能匹配到对应的数据");
		}
		return dto;
	}

	public static SkuDetailDTO buildSkuDtailDtoFromFileContent(String line) {
		SkuDetailDTO dto = new SkuDetailDTO();
		String[] strArray = line.split(",");
		dto.setProductName(strArray[0]);
		dto.setProductId(strArray[1]);
		dto.setSkuId(strArray[2]);
		dto.setSkuName(strArray[3]);
		dto.setSkuImgUrl(strArray[4]);
		try {
			dto.setAmount(new BigDecimal(strArray[5]));
		} catch (Exception e) {
			dto.setAmount(new BigDecimal(9999999));
		}
		try {
			dto.setStoreNumber(Integer.parseInt(strArray[7]));
		} catch (Exception e) {
			dto.setStoreNumber(0);
		}
		return dto;
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public static String compareSkuDetail(String productId) {
		List<SkuDetailDTO> oldDetailList = getSkuDetailList(productId, TaobaoOption.OLD_INFO);
		if (oldDetailList == null || oldDetailList.isEmpty()) {
			// 旧文件不存在或内容为空，无法比对
			System.out.println("Product ID: " + productId + " -> 旧 SKU 信息文件未找到或为空，跳过比对。");
			return null;
		}

		List<SkuDetailDTO> newDetailList = getSkuDetailList(productId, TaobaoOption.NEW_INFO);
		if (newDetailList == null) {
			newDetailList = new ArrayList<>();
		}

		// 将新 SKU 列表按 skuId 建立映射方便查找
		Map<String, SkuDetailDTO> newSkuMap = newDetailList.stream()
				.collect(Collectors.toMap(SkuDetailDTO::getSkuName, dto -> dto, (k1, k2) -> k1));

		StringBuilder messageBuilder = new StringBuilder();
		String productName = oldDetailList.get(0).getProductName();
		messageBuilder.append("【商品价格/库存变动通知】\n").append("商品ID: ").append(productId).append("\n").append("商品名称: ")
				.append(productName).append("\n");

		boolean hasAnomaly = false;

		// 遍历旧 SKU 数据，进行各项规则比对
		for (SkuDetailDTO oldSku : oldDetailList) {
			SkuDetailDTO newSku = newSkuMap.get(oldSku.getSkuName());

			List<String> reasons = new ArrayList<>();

			if (newSku == null) {
				// 情况3：SKU 消失（完全找不到该 skuId）
				reasons.add("SKU已下架或消失");
				hasAnomaly = true;
			} else {
				// 检查名称是否匹配（如果不匹配也视为 SKU 发生变动/消失）
				if (!StringUtils.equals(oldSku.getSkuName(), newSku.getSkuName())) {
					reasons.add(String.format("SKU名称不匹配 (旧: %s, 新: %s)", oldSku.getSkuName(), newSku.getSkuName()));
					hasAnomaly = true;
				}

				// 情况1：价格上涨 (新价格 > 旧价格)
				if (newSku.getAmount() != null && oldSku.getAmount() != null
						&& newSku.getAmount().compareTo(oldSku.getAmount()) > 0) {
					reasons.add(String.format("价格上涨 (旧: %s, 新: %s)", oldSku.getAmount(), newSku.getAmount()));
					hasAnomaly = true;
				}

				// 情况2：库存少于 200
				if (newSku.getStoreNumber() != null && newSku.getStoreNumber() < 200) {
					reasons.add(String.format("库存不足200 (当前库存: %d)", newSku.getStoreNumber()));
					hasAnomaly = true;
				}
			}

			// 如果当前 SKU 触发了任意异常条件，将其拼接到汇总消息中
			if (!reasons.isEmpty()) {
				messageBuilder.append(stringBuilderSkuAnomaly(oldSku, reasons));
			}
		}

		// 如果检测到异常，发送该 Product ID 的汇总通知消息
		if (hasAnomaly) {
			return messageBuilder.toString();
		} else {
			System.out.println("Product ID: " + productId + " -> 检查正常，无价格上涨、库存少于200或SKU消失情况。");
			return null;
		}
	}

	/**
	 * 拼凑单个 SKU 的异常明细描述
	 */
	private static String stringBuilderSkuAnomaly(SkuDetailDTO oldSku, List<String> reasons) {
		StringBuilder sb = new StringBuilder();
		sb.append("  - [SKU ID: ").append(oldSku.getSkuId()).append(" | SKU 名称: ").append(oldSku.getSkuName())
				.append("]\n");
		for (String reason : reasons) {
			sb.append("    * 异常原因: ").append(reason).append("\n");
		}
		return sb.toString();
	}

	private static List<SkuDetailDTO> getSkuDetailList(String productId, Path dirPath) {
		List<SkuDetailDTO> detailList = new ArrayList<>();
		FileUtilCustom ioUtil = new FileUtilCustom();
		File file = new File(dirPath.toString());
		File[] files = file.listFiles();
		if (files == null || files.length < 1) {
			return detailList;
		}
		File targetFile = null;
		String targetPrefix = TaobaoOption.FILE_NAME_PREFIX + productId;
		for (int i = 0; i < files.length && targetFile == null; i++) {
			File tmpFile = files[i];
			if (tmpFile.getName().startsWith(targetPrefix)) {
				targetFile = tmpFile;
			}
		}
		if (targetFile == null) {
			return detailList;
		}
		String content = ioUtil.getStringFromFile(targetFile.getAbsolutePath());
		String[] lines = content.split(System.lineSeparator());
		for (String line : lines) {
			if (StringUtils.isBlank(line)) {
				continue;
			}
			SkuDetailDTO dto = buildSkuDtailDtoFromFileContent(line);
			detailList.add(dto);
		}
		return detailList;
	}
}
