package demo.image.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageEdit {

	private static String folderPath = "D:/tmp/一年级数学上学期复习册_FIT/";
	private static String mazePath = folderPath + "一年级数学上学期复习册_FIT-*.jpg";
	private static String mazeResultPath = folderPath + "*.jpg";
	private static String rainbowImg = "d:/z2/打印材料/rainbow2.png";
	private static int start = 1;
	private static int end = 10;

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
			rainbowRgbList.add(rainbowBi.getRGB(i, 0));
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
				if (!target.equals(mazeBufferImage.getRGB(w, h))) {
					rainRate = (w.doubleValue() + h) / (imgWidth + imgHeight);
					rainbowIndex = rainbowRgbList.size() * rainRate;
					mazeBufferImage.setRGB(w, h, rainbowRgbList.get(rainbowIndex.intValue()));
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

		int widthCenter = mazeBufferImage.getWidth() / 2;
		int heightCenter = mazeBufferImage.getHeight() / 2;

		Integer widthDistance = 0;
		Integer heightDistance = 0;

		for (Integer w = 0; w < imgWidth; w++) {
			for (Integer h = 0; h < imgHeight; h++) {
//				if (target.equals(mazeBufferImage.getRGB(w, h))) {
				if (mazeBufferImage.getRGB(w, h) >= black && mazeBufferImage.getRGB(w, h) <= -13000000) {
					if (w > widthCenter) {
						widthDistance = w - widthCenter;
					} else {
						widthDistance = widthCenter - w;
					}

					if (h > heightCenter) {
						heightDistance = h - heightCenter;
					} else {
						heightDistance = heightCenter - h;
					}

					Integer longestDistance = widthCenter + heightCenter;

					rainRate = (widthDistance * widthDistance + heightDistance * heightDistance)
							/ (longestDistance * longestDistance.doubleValue()) * 2;
					rainbowIndex = rainbowRgbList.size() * rainRate;
					if (rainbowIndex >= rainbowRgbList.size()) {
						rainbowIndex = rainbowRgbList.size() - 1D;
					}
					mazeBufferImage.setRGB(w, h, rainbowRgbList.get(rainbowIndex.intValue()));
				}
			}
		}

		try {
			if (index < 10) {
				ImageIO.write(mazeBufferImage, "PNG",
						new File(mazeResultPath.replaceAll("\\*", "0" + index.toString())));
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
		ImageEdit t = new ImageEdit();
		t.loadRainbowList();
		t.loadRainbowListDouble();

		BufferedImage mazeBufferImage = null;

		for (Integer i = start; i <= end; i++) {
			if (i < 10) {
				mazeBufferImage = t.readImage(mazePath.replaceAll("\\*", "0" + i.toString()));
			} else {
				mazeBufferImage = t.readImage(mazePath.replaceAll("\\*", i.toString()));
			}

			t.colorReplaceCenterOfCircle(mazeBufferImage, i);
//			t.colorReplaceSlash(mazeBufferImage, i);
//			t.printXiaoFangGe(mazeBufferImage, i);
		}
	}
}
