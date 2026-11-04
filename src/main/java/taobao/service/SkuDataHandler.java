package taobao.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import taobao.pojo.dto.SkuDetailDTO;
import taobao.pojo.dto.SkuFileNameDTO;

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

	public static SkuDetailDTO getSkuDtailDtoFromFileContent(String line) {
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
			dto.setStoreNumber(Integer.parseInt(strArray[6]));
		} catch (Exception e) {
			dto.setStoreNumber(0);
		}
		return dto;
	}

	public static void compareSkuDetail(String productId) {
//		TODO
		
	}
}
