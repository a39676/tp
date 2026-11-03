package taobao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import taobao.pojo.dto.SkuDetailDTO;

public class SkuDataHandler {

//	String fileName = "1688批量导出SKU_1062730282544_03_11_2026, 19_04_19.txt";

	public static SkuDetailDTO getSkuDetailDtoFromFileName(String fileName) {
		SkuDetailDTO dto = new SkuDetailDTO();

		// 1. 正则表达式提取各部分字符串
		String regex = "1688批量导出SKU_(\\d+)_(\\d{2}_\\d{2}_\\d{4}),\\s*(\\d{2}_\\d{2}_\\d{2})";
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
}
