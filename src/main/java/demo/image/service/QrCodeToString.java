package demo.image.service;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class QrCodeToString {

	private static String folderPath = "D:/tmp";
	private static String targetFilePath = folderPath + "/002.png";

	private static Integer black = -16777216;
	@SuppressWarnings("unused")
	private static Integer white = -1;
	private static Integer target = black;

	public BufferedImage readImage(String filePathStr) {
		BufferedImage bufImg = null;
		try {
			bufImg = ImageIO.read(new File(filePathStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufImg;
	}

	public void trans(BufferedImage mazeBufferImage) {
		int imgWidth = mazeBufferImage.getWidth();
		int imgHeight = mazeBufferImage.getHeight();

		for (Integer h = 0; h < imgHeight; h = h + 5) {
			for (Integer w = 0; w < imgWidth; w = w + 5) {
				if (target.equals(mazeBufferImage.getRGB(w, h))) {
					System.out.print("■");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		QrCodeToString t = new QrCodeToString();

		BufferedImage mazeBufferImage = t.readImage(targetFilePath);
		t.trans(mazeBufferImage);
	}
}
