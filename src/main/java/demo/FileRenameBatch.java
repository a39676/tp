package demo;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class FileRenameBatch {

	public static void main(String[] args) {
		// 目标文件夹路径
		String folderPath = "C:\\Users\\daven\\tmp\\小猪佩奇\\s8";
		String keyword = "";
		// 正则表达式：匹配类似 _Media_各种字符_001_1080p 的后缀
		String patternStr = "(_Media_.*_001_1080p)(\\.[a-zA-Z0-9]+)$";
		Pattern pattern = Pattern.compile(patternStr);
		File folder = new File(folderPath);

		if (folder.isDirectory()) {
			File[] files = folder.listFiles();

			if (files != null) {
				int count = 0;
				for (File file : files) {
					String oldName = file.getName();
					if (StringUtils.isNotBlank(keyword) && oldName.contains(keyword)) {
						String newName = oldName.replace(keyword, "");
						File newFile = new File(folderPath + File.separator + newName);

						if (file.renameTo(newFile)) {
							System.out.println("已修改: " + oldName + " -> " + newName);
							count++;
						}
					}

					if (StringUtils.isNotBlank(patternStr)) {
						oldName = file.getName();
						Matcher matcher = pattern.matcher(oldName);
						if (matcher.find()) {
							String newName = oldName.replace(matcher.group(1), "");
							File newFile = new File(folder, newName);

							if (file.renameTo(newFile)) {
								System.out.println("成功: " + oldName + " -> " + newName);
							} else {
								System.out.println("失败: " + oldName);
							}
						}
					}

				}
				System.out.println("--- 批量处理完成，共修改了 " + count + " 个文件 ---");
			}
		} else {
			System.out.println("指定的路径不是一个有效的文件夹。");
		}
	}
}
