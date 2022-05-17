package demo.image.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageEdit {

	private static String mazePath = "d:/z2/经常打印/mazeTmp*.png";
	private static String mazeResultPath = "d:/z2/经常打印/mazeResult*.png";
	private static String rainbowImg = "d:/z2/经常打印/rainbow2.png";
	private static int start = 1;
	private static int end = 4;

	private static List<Integer> rainbowRgbList;

	private static Integer rainbowWidth = null;
	private static Integer black = -16777216;
	@SuppressWarnings("unused")
	private static Integer white = -0;
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
				if (target.equals(mazeBufferImage.getRGB(w, h))) {
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
				if (target.equals(mazeBufferImage.getRGB(w, h))) {
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
					
					rainRate = (widthDistance * widthDistance + heightDistance * heightDistance) / (longestDistance * longestDistance.doubleValue()) * 2;
					rainbowIndex = rainbowRgbList.size() * rainRate;
					if(rainbowIndex >= rainbowRgbList.size()) {
						rainbowIndex = rainbowRgbList.size() - 1D;
					}
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

	public static void main(String[] args) {
		ImageEdit t = new ImageEdit();
		t.loadRainbowList();
//		t.loadRainbowListDouble();

		BufferedImage mazeBufferImage = null;

		for (Integer i = start; i <= end; i++) {
			mazeBufferImage = t.readImage(mazePath.replaceAll("\\*", i.toString()));

			t.colorReplaceCenterOfCircle(mazeBufferImage, i);
		}
	}
}
