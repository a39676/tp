package demo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import demo.pojo.type.ImageBlurJobType;

public class ImageBlurTool {

	/**
	 * 对指定区域进行高斯模糊
	 * 
	 * @param source 源图片
	 * @param x      区域左上角 X
	 * @param y      区域左上角 Y
	 * @param width  区域宽度
	 * @param height 区域高度
	 * @param radius 模糊半径（数值越大越模糊）
	 */
	public static BufferedImage blurArea(BufferedImage source, int x, int y, int width, int height, int radius) {
		// 1. 裁剪出需要模糊的区域
		BufferedImage subImage = source.getSubimage(x, y, width, height);

		// 2. 生成高斯核
		int size = radius * 2 + 1;
		float weight = 1.0f / (size * size);
		float[] data = new float[size * size];
		for (int i = 0; i < data.length; i++) {
			data[i] = weight;
		}

		// 3. 应用卷积运算进行模糊
		Kernel kernel = new Kernel(size, size, data);
		ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		BufferedImage blurredSubImage = op.filter(subImage, null);

		// 4. 将模糊后的部分贴回原图
		Graphics2D g2d = source.createGraphics();
		g2d.drawImage(blurredSubImage, x, y, null);
		g2d.dispose();

		return source;
	}

	public static void main(String[] args) throws IOException {
		String inputDir = "C:\\Users\\daven\\tmp\\新款10-16mm炫彩欧泊溏心树脂珠手工diy手链项链手机链饰品配件批\\详情\\新建文件夹"; // 输入目录
		String outputDir = "C:\\Users\\daven\\tmp\\output"; // 输出目录
		int radius = 10;

		File folder = new File(inputDir);
		File[] files = folder.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png"));
		ImageBlurJobType jobType = ImageBlurJobType.XY_LOGO;

		if (files != null && files.length > 0) {
			for (File file : files) {
				if (!file.getName().endsWith("jpg") && !file.getName().endsWith("png")) {
					continue;
				}
				BufferedImage img = ImageIO.read(file);

				BufferedImage result = null;

				if (ImageBlurJobType.XY_LOGO.equals(jobType)) {
					result = blurArea(img, 0, 0, 250, 165, radius); // xy左上角标
				} else if (ImageBlurJobType.XY_BOTTOM_RIGHT.equals(jobType)) {
					// xy右下水印
					int height = img.getHeight();
					Double xStart = height * 0.455;
					Double yStart = height * 0.93;
					Double xLong = height * 0.52;
					Double yLong = height * 0.055;
					result = blurArea(img, xStart.intValue(), yStart.intValue(), xLong.intValue(), yLong.intValue(),
							radius);
//					if (height == 800) {
//						result = blurArea(img, 357, 734, 420, 60, radius); // xy右下水印
//					} else if (height == 1000) {
//						result = blurArea(img, 470, 935, 500, 50, radius); // xy右下水印
//					} else if (height == 1920) {
//						result = blurArea(img, 900, 1800, 960, 80, radius); // xy右下水印
//					} else {
//						System.out.println(file.getName() + ", height:" + height + ", 未设定");
//					}
				}

				File outputFile = new File(outputDir + "/" + file.getName());
				if (!outputFile.exists()) {
					outputFile.mkdirs();
				}
				ImageIO.write(result, "jpg", outputFile);
				System.out.println("处理完成: " + file.getName());
			}
		}
	}
}
