package demo.image.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageEdit2 {

	private static String folderPath = System.getProperty("user.home") + "\\nextG\\bookForRead\\绿山墙的安妮";
	private static String mazePath = folderPath + "/绿山墙的安妮_page-*.jpg";
	private static String mazeResultPath = folderPath + "/*.png";
	private static String rainbowImg = System.getProperty("user.home") + "/tmp/rainbow.jpg";
	private static int start = 2;
	private static int end = 32;

	private static List<Integer> rainbowRgbList;

	private static Integer rainbowWidth = null;
	private static Integer black = -16777216;
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

	public List<Integer> loadRainbowList() {
		if (rainbowRgbList == null) {
			rainbowRgbList = new ArrayList<Integer>();
		}
		BufferedImage rainbowBi = readImage(rainbowImg);
		rainbowWidth = rainbowBi.getWidth();
		for (int i = 0; i < rainbowWidth; i++) {
			rainbowRgbList.add(rainbowBi.getRGB(i, 10));
		}
		return rainbowRgbList;
	}

	public List<Integer> loadRainbowListDouble() {
		rainbowRgbList = loadRainbowList();
		rainbowRgbList.addAll(rainbowRgbList);
		return rainbowRgbList;
	}

	public void colorReplaceSlash(BufferedImage mazeBufferImage, Integer index) {
		Double rainbowIndex = null;
		Double rainRate = null;

		int imgWidth = mazeBufferImage.getWidth();
		int imgHeight = mazeBufferImage.getHeight();

		for (Integer w = 0; w < imgWidth; w++) {
			for (Integer h = 0; h < imgHeight; h++) {
				if (target.equals(mazeBufferImage.getRGB(w, h))) {
					rainRate = (w.doubleValue() + h) / (imgWidth + imgHeight);
					rainbowIndex = rainbowRgbList.size() * rainRate;
					Integer color = rainbowRgbList.get(rainbowIndex.intValue());
					mazeBufferImage.setRGB(w, h, color);
				}
			}
		}

		try {
			ImageIO.write(mazeBufferImage, "PNG", new File(mazeResultPath.replaceAll("\\*", index.toString())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void colorReplaceCenterOfCircle(BufferedImage mazeBufferImage, Integer index) {
		Double rainbowIndex = null;
		Double rainRate = null;

		int imgWidth = mazeBufferImage.getWidth();
		int imgHeight = mazeBufferImage.getHeight();

		int imageWidthCenter = mazeBufferImage.getWidth() / 2;
		int imageHeightCenter = mazeBufferImage.getHeight() / 2;

		Integer widthDistance = 0;
		Integer heightDistance = 0;

		for (Integer imgWidthIndex = 0; imgWidthIndex < imgWidth; imgWidthIndex++) {
			for (Integer imageHeightIndex = 0; imageHeightIndex < imgHeight; imageHeightIndex++) {
				if (!target.equals(mazeBufferImage.getRGB(imgWidthIndex, imageHeightIndex))) {
//				if (mazeBufferImage.getRGB(imgWidthIndex, imageHeightIndex) >= black
//						&& mazeBufferImage.getRGB(imgWidthIndex, imageHeightIndex) <= -13000000) {
					if (imgWidthIndex > imageWidthCenter) {
						widthDistance = imgWidthIndex - imageWidthCenter;
					} else {
						widthDistance = imageWidthCenter - imgWidthIndex;
					}

					if (imageHeightIndex > imageHeightCenter) {
						heightDistance = imageHeightIndex - imageHeightCenter;
					} else {
						heightDistance = imageHeightCenter - imageHeightIndex;
					}

					Integer longestDistance = imageWidthCenter + imageHeightCenter;

					rainRate = (widthDistance * widthDistance + heightDistance * heightDistance)
							/ (longestDistance * longestDistance.doubleValue()) * 2;
					rainbowIndex = rainbowRgbList.size() * rainRate;
					if (rainbowIndex >= rainbowRgbList.size()) {
						rainbowIndex = rainbowRgbList.size() - 1D;
					}
					mazeBufferImage.setRGB(imgWidthIndex, imageHeightIndex,
							rainbowRgbList.get(rainbowIndex.intValue()));
				}
			}
		}

		try {
			if (index < 10) {
				ImageIO.write(mazeBufferImage, "PNG", new File(mazeResultPath.replaceAll("\\*", index.toString())));
			} else {
				ImageIO.write(mazeBufferImage, "PNG", new File(mazeResultPath.replaceAll("\\*", index.toString())));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printXiaoFangGe(BufferedImage mazeBufferImage, Integer index) {
		int imgWidth = mazeBufferImage.getWidth();
		int imgHeight = mazeBufferImage.getHeight();

		for (Integer w = 0; w < imgWidth; w++) {
			for (Integer h = 0; h < imgHeight; h++) {
				if (w % 39 == 0 || h % 39 == 0) {
					mazeBufferImage.setRGB(w, h, black);
				} else {
					mazeBufferImage.setRGB(w, h, white);
				}
			}
		}

		try {
			ImageIO.write(mazeBufferImage, "PNG", new File(mazeResultPath.replaceAll("\\*", index.toString())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ImageEdit2 t = new ImageEdit2();
		t.loadRainbowList();
		t.loadRainbowListDouble();

		BufferedImage mazeBufferImage = null;

		for (Integer i = start; i <= end; i++) {
			if (i < 10) {
				mazeBufferImage = t.readImage(mazePath.replaceAll("\\*", String.format("%04d", i)));
			} else {
				mazeBufferImage = t.readImage(mazePath.replaceAll("\\*", String.format("%04d", i)));
			}

//			t.colorReplaceCenterOfCircle(mazeBufferImage, i);
			t.colorReplaceSlash(mazeBufferImage, i);
//			t.printXiaoFangGe(mazeBufferImage, i);
		}
	}
}
