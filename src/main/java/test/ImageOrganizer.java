package test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class ImageOrganizer {

	public static void main(String[] args) {
		// 目标基准目录
		String baseDirPath = "C:\\Users\\daven\\tmp\\厂家直销72号玉线diy手工编织线材料编绳红绳编手绳手链项链玉绳\\SKU图片";
		Path baseDir = Paths.get(baseDirPath);

		if (!Files.exists(baseDir) || !Files.isDirectory(baseDir)) {
			System.err.println("错误：目标目录不存在 -> " + baseDirPath);
			return;
		}

		try {
			// 遍历 SKU图片 目录下的所有文件
			Files.walkFileTree(baseDir, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) {
					System.err.println("无法访问文件: " + file + " 错误: " + exc.getMessage());
					return null;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					// 只处理图片文件（根据后缀判断）
					String fileName = file.getFileName().toString().toLowerCase();
					if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")) {

						// 检查该文件是否已经在目标根目录下，避免死循环处理已生成的图片
						if (file.getParent().equals(baseDir)) {
							return FileVisitResult.CONTINUE;
						}

						// 解析路径以获取 sku** 级别的文件夹名称
						// 目标结构：baseDir -> sku-3-白色 -> 72号玉线 -> 约50米.jpg
						// 我们需要找到 baseDir 的直接子目录名称
						Path relativePath = baseDir.relativize(file);
						if (relativePath.getNameCount() >= 2) {
							String skuFolderName = relativePath.getName(0).toString(); // 获取 "sku-3-白色"

							// 获取原文件的后缀名（例如 .jpg）
							String extension = fileName.substring(fileName.lastIndexOf("."));

							// 构造新的文件名和目标路径： SKU图片/sku-3-白色.jpg
							String newFileName = skuFolderName + extension;
							Path targetPath = baseDir.resolve(newFileName);

							// 如果文件名冲突（例如一个sku文件夹下有多张图），加一个计数器后缀
							int counter = 1;
							while (Files.exists(targetPath)) {
								String distinctName = skuFolderName + "_" + counter + extension;
								targetPath = baseDir.resolve(distinctName);
								counter++;
							}

							// 执行复制操作（如需移动，可改为 Files.move）
							Files.copy(file, targetPath, StandardCopyOption.REPLACE_EXISTING);
							System.out.println(
									"成功复制: [" + file.getFileName() + "] -> [" + targetPath.getFileName() + "]");
						}
					}
					return FileVisitResult.CONTINUE;
				}
			});

			System.out.println("\n--- 处理完成！所有图片已重命名并集中至 SKU图片 目录 ---");

		} catch (IOException e) {
			System.err.println("遍历文件时发生异常: " + e.getMessage());
			e.printStackTrace();
		}
	}
}