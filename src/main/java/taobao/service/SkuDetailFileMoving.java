package taobao.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class SkuDetailFileMoving {

	public static void moveToOptionFile() {

		try {
			// 3. 确保目标目录存在，如果不存在则自动创建（包括父目录）
			if (!Files.exists(TaobaoOption.NEW_INFO)) {
				Files.createDirectories(TaobaoOption.NEW_INFO);
				System.out.println("成功创建目标目录: " + TaobaoOption.NEW_INFO);
			}

			// 4. 查找并移动符合条件的文件
			// 文件名前缀匹配 "1688批量导出SKU_" 且后缀为 ".txt"
			try (Stream<Path> stream = Files.list(TaobaoOption.DOWNLOAD_DIR)) {
				stream.filter(path -> {
					String fileName = path.getFileName().toString();
					return Files.isRegularFile(path) && fileName.startsWith(TaobaoOption.FILE_NAME_PREFIX)
							&& fileName.endsWith(".txt");
				}).forEach(sourceFile -> {
					try {
						Path targetFile = TaobaoOption.NEW_INFO.resolve(sourceFile.getFileName());

						// 执行移动操作：若目标文件已存在，则覆盖
						Files.move(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);

						System.out.println("移动成功: " + sourceFile.getFileName() + " -> " + targetFile);
					} catch (IOException e) {
						System.err.println("移动文件失败: " + sourceFile.getFileName() + ", 原因: " + e.getMessage());
					}
				});
			}

		} catch (IOException e) {
			System.err.println("操作过程中发生异常: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
