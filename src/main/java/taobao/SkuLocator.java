package taobao;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.List;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;

public class SkuLocator {

	public static String targetWord = "sku";

	public static void main(String[] args) {
		try {
			// 1. 截取全屏
			BufferedImage screenCapture = captureFullScreen();
			System.out.println("截屏成功！");

			// 2. 将图片处理为灰度或黑白二值化图片（提高 OCR 识别率）
			BufferedImage grayImage = convertToGray(screenCapture);
			// 如果背景比较杂乱，也可以尝试使用二值化（黑白分明）：
			// BufferedImage binaryImage = convertToBinary(grayImage, 128);

			// 2. 使用 OCR 识别文字及坐标
			// 替换为你本地的 tessdata 文件夹路径（里面需要放对应语言的训练数据包）
			String tessDataPath = "C:\\Users\\daven\\soft\\tessdata";

			Point coordinate = findTextCoordinate(screenCapture, targetWord, tessDataPath);

			if (coordinate != null) {
				System.out.println("成功找到目标文字！坐标为: X = " + coordinate.x + ", Y = " + coordinate.y);
			} else {
				System.out.println("未能在屏幕中找到: \"" + targetWord + "\"");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 截取全屏
	 */
	public static BufferedImage captureFullScreen() throws AWTException {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height / 2; // 只取高度的一半（上半部分）

		Rectangle upperRect = new Rectangle(0, 0, width, height);
		Robot robot = new Robot();
		return robot.createScreenCapture(upperRect);
	}

	/**
	 * 在截图中查找指定文字的坐标
	 */
	public static Point findTextCoordinate(BufferedImage image, String targetText, String tessDataPath) {
		ITesseract tesseract = new Tesseract();
		// 设置 tessdata 路径
		tesseract.setDatapath(tessDataPath);
		// 设置识别语言，中英文混合可以设为 "chi_sim+eng"
		tesseract.setLanguage("chi_sim+eng");

		try {
			// 调用 Tess4J 的 getWords 方法获取每个词及其边界框 (Bounding Box)
			// 参数 3 表示按行/词进行解析 (ITessAPI.OEM_TESSERACT_LSTM_COMBINED 等)
			List<Word> words = tesseract.getWords(image, net.sourceforge.tess4j.ITessAPI.FALSE);

			for (Word word : words) {
				String text = word.getText().trim();
				// 检查是否匹配目标文本（可以根据需要改为包含匹配 text.contains(targetText)）
				System.out.println(text);
				if (text.contains(targetText)) {
					Rectangle rect = word.getBoundingBox();
					// 计算文字中心的坐标，或者返回左上角坐标
					int centerX = rect.x + rect.width / 2;
					int centerY = rect.y + rect.height / 2;

					System.out.println("匹配到文字: [" + text + "] 原始区域: " + rect);
					return new Point(centerX, centerY);
				}
			}
		} catch (Exception e) {
			System.err.println("OCR 识别出错: " + e.getMessage());
		}

		return null;
	}

	/**
	 * 方法一：将图片转换为灰度图 (Gray Scale)
	 */
	public static BufferedImage convertToGray(BufferedImage sourceImage) {
		BufferedImage grayImage = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(),
				BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = grayImage.getGraphics();
		g.drawImage(sourceImage, 0, 0, null);
		g.dispose();
		return grayImage;
	}

	/**
	 * 方法二：将灰度图转换为二值化黑白图 (Binary Image)
	 * 
	 * @param threshold 阈值 (0-255)，通常设在 128 左右，可根据实际效果微调
	 */
	public static BufferedImage convertToBinary(BufferedImage grayImage, int threshold) {
		int width = grayImage.getWidth();
		int height = grayImage.getHeight();
		BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int rgb = grayImage.getRGB(x, y);
				// 提取灰度值 (因为是灰度图，RGB三通道值相同，取红色通道即可)
				int red = (rgb >> 16) & 0xFF;
				if (red < threshold) {
					binaryImage.setRGB(x, y, Color.BLACK.getRGB()); // 文字或深色部分设为黑
				} else {
					binaryImage.setRGB(x, y, Color.WHITE.getRGB()); // 背景设为白
				}
			}
		}
		return binaryImage;
	}
}
