package demo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import demo.pojo.type.ImageBlurJobType;

public class ImageBlurTool {

	private static final String MAIN_FOLDER_PATH_STR = System.getProperty("user.home") + "/tmp";
	private static final String PRODUCT_NAME_FOLDER = "大孔珠桶珠圆环算盘珠圆珠树脂珠散珠子手工diy串珠服装发饰配件";
	private static ImageBlurJobType jobType = ImageBlurJobType.NAME_BOTTOM_RIGHT;
	private static final List<String> SUB_FOLDER_NAME_LIST = new ArrayList<>();
	static {
		SUB_FOLDER_NAME_LIST.add("主图");
		SUB_FOLDER_NAME_LIST.add("SKU图片");
		SUB_FOLDER_NAME_LIST.add("详情");
	}

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
	private static BufferedImage blurArea(BufferedImage source, int x, int y, int width, int height, int radius) {
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

	private static void handler(String subFolderName) throws IOException {
		String inputDir = MAIN_FOLDER_PATH_STR + "/" + PRODUCT_NAME_FOLDER + "/" + subFolderName; // 输入目录
		String outputDir = MAIN_FOLDER_PATH_STR + "/output" + "/" + subFolderName; // 输出目录
		
		System.out.println("Going to: " + inputDir);

		File inputFolder = new File(inputDir);
		if (!inputFolder.exists()) {
			System.out.println(inputDir + ", NOT exists");
			return;
		}

		File outputFolder = new File(outputDir);
		if (!outputFolder.exists()) {
			outputFolder.mkdirs();
		}

		int radius = 10;

		File folder = new File(inputDir);
		File[] files = folder.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png"));

		if (files != null && files.length > 0) {
			for (File file : files) {
				System.out.println("Handle: " + file.getName());
				if (!file.getName().endsWith("jpg") && !file.getName().endsWith("png")) {
					System.out.println("Skip: " + file.getName());
					continue;
				}
				BufferedImage img = ImageIO.read(file);
				int height = img.getHeight();
				int width = img.getWidth();
				if (height != width) {
					System.err.println("Skip, NOT a square: " + file.getName());
					continue;
				}

				BufferedImage result = null;

				if (ImageBlurJobType.XY_LOGO_TOP_LEFT.equals(jobType)) {
					result = blurArea(img, 0, 0, 250, 165, radius); // xy左上角标
				} else if (ImageBlurJobType.XY_LOGO_BOTTOM_LEFT.equals(jobType)) {
					// xy左下水印
					Double xStart = height * 0.015;
					Double yStart = height * 0.8;
					Double xLong = height * 0.3;
					Double yLong = height * 0.2;
					result = blurArea(img, xStart.intValue(), yStart.intValue(), xLong.intValue(), yLong.intValue(),
							radius);
				} else if (ImageBlurJobType.XY_LOGO_TOP_RIGHT.equals(jobType)) {
					// xy右上水印
					Double xStart = height * 0.68;
					Double yStart = height * 0.017;
					Double xLong = height * 0.32;
					Double yLong = height * 0.2;
					result = blurArea(img, xStart.intValue(), yStart.intValue(), xLong.intValue(), yLong.intValue(),
							radius);
				} else if (ImageBlurJobType.NAME_BOTTOM_RIGHT.equals(jobType)) {
					// xy右下水印
					Double xStart = height * 0.455;
					Double yStart = height * 0.93;
					Double xLong = height * 0.52;
					Double yLong = height * 0.055;
					result = blurArea(img, xStart.intValue(), yStart.intValue(), xLong.intValue(), yLong.intValue(),
							radius);
				} else if (ImageBlurJobType.KUO_CHENG_BOTTOM_RIGHT.equals(jobType)) {
					// kuo cheng 右下水印
					Double xStart = height * 0.455;
					Double yStart = height * 0.92;
					Double xLong = height * 0.52;
					Double yLong = height * 0.055;
					result = blurArea(img, xStart.intValue(), yStart.intValue(), xLong.intValue(), yLong.intValue(),
							radius);
				} else if (ImageBlurJobType.KUO_CHENG_LOGO_TOP_LEFT.equals(jobType)) {
					// kuo cheng 左上图标
					Double xStart = height * 0.01;
					Double yStart = height * 0.01;
					Double xLong = height * 0.142;
					Double yLong = height * 0.241;
					result = blurArea(img, xStart.intValue(), yStart.intValue(), xLong.intValue(), yLong.intValue(),
							radius);
				}

				File outputFile = new File(outputDir + "/" + file.getName());

				ImageIO.write(result, "jpg", outputFile);
				System.out.println("处理完成: " + file.getName());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		for (String subFolderName : SUB_FOLDER_NAME_LIST) {
			handler(subFolderName);
		}
	}
}
