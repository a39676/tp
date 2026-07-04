package test;

import java.io.File;

public class Tmp44 {
	public static void main(String[] args) {
		// 目标文件夹路径
		String folderPath = "C:\\Users\\daven\\Downloads";
		File folder = new File(folderPath);

		if (folder.isDirectory()) {
			File[] files = folder.listFiles();

			if (files != null) {
				int count = 0;
				for (File file : files) {
					String name = file.getName();
					// 检查文件名是否包含该特征且是 mp3
					if (name.contains("_spotdown.org") && name.endsWith(".mp3")) {
						String newName = name.replace("_spotdown.org", "");
						File newFile = new File(folderPath + File.separator + newName);

						if (file.renameTo(newFile)) {
							System.out.println("已修改: " + name + " -> " + newName);
							count++;
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
