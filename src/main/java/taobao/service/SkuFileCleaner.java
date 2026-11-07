package taobao.service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SkuFileCleaner {

	// 内部类：用于封装文件路径与其解析出的时间
	private static class SkuFile {
		Path path;
		LocalDateTime dateTime;

		public SkuFile(Path path, LocalDateTime dateTime) {
			this.path = path;
			this.dateTime = dateTime;
		}
	}

	@SuppressWarnings("unused")
	public static void cleanDuplicateSkuFiles(String directoryPath) {
		Path dir = Paths.get(directoryPath);
		if (!Files.isDirectory(dir)) {
			System.out.println("提供的路径不是一个有效的目录: " + directoryPath);
			return;
		}

		// 正则表达式匹配文件名
		// 示例: 1688批量导出SKU_985604180379_11_6_2026, 10_01_54 PM.txt
		Pattern pattern = Pattern.compile(
				"^1688批量导出SKU_(\\d+)_(\\d{1,2})_(\\d{1,2})_(\\d{4}),\\s*(\\d{1,2})_(\\d{2})_(\\d{2})\\s*(AM|PM)\\.txt$");

		// Key: SKU ID, Value: 同 SKU 的文件列表
		Map<String, List<SkuFile>> skuMap = new HashMap<>();

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path entry : stream) {
				if (!Files.isRegularFile(entry))
					continue;
				String fileName = entry.getFileName().toString();
				Matcher matcher = pattern.matcher(fileName);

				if (matcher.matches()) {
					String skuId = matcher.group(1);
					int month = Integer.parseInt(matcher.group(2));
					int day = Integer.parseInt(matcher.group(3));
					int year = Integer.parseInt(matcher.group(4));
					int hour = Integer.parseInt(matcher.group(5));
					int minute = Integer.parseInt(matcher.group(6));
					int second = Integer.parseInt(matcher.group(7));
					String amPm = matcher.group(8);

					// 12小时制转 24 小时制
					if ("PM".equalsIgnoreCase(amPm) && hour < 12) {
						hour += 12;
					} else if ("AM".equalsIgnoreCase(amPm) && hour == 12) {
						hour = 0;
					}

					try {
						LocalDateTime dt = LocalDateTime.of(year, month, day, hour, minute, second);
						skuMap.computeIfAbsent(skuId, k -> new ArrayList<>()).add(new SkuFile(entry, dt));
					} catch (Exception e) {
						System.err.println("解析文件名日期时间失败: " + fileName + ", 错误: " + e.getMessage());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 遍历分组，保留最新的文件，删除其余旧文件
		for (Map.Entry<String, List<SkuFile>> entry : skuMap.entrySet()) {
			List<SkuFile> files = entry.getValue();
			if (files.size() <= 1) {
				continue; // 只有单个文件，无需清理
			}

			// 按时间降序排序（最新的排在最前面）
			files.sort((a, b) -> b.dateTime.compareTo(a.dateTime));

			// 列表第一项为最新文件，予以保留
			SkuFile latestFile = files.get(0);
			System.out.println("【保留】SKU: " + entry.getKey() + " -> " + latestFile.path.getFileName());

			// 从第二项开始（较旧的文件）全部删除
			for (int i = 1; i < files.size(); i++) {
				SkuFile oldFile = files.get(i);
				try {
					Files.delete(oldFile.path);
					System.out.println("  [已删除旧文件] " + oldFile.path.getFileName());
				} catch (IOException e) {
					System.err.println("  删除文件失败: " + oldFile.path.getFileName() + ", 原因: " + e.getMessage());
				}
			}
		}
	}

	public static void main(String[] args) {
		// 请将此处替换为你的实际文件夹路径
		String targetDir = System.getProperty("user.home") + "/optionFile/tp/taobao/oldSkuInfo";
		cleanDuplicateSkuFiles(targetDir);
	}
}