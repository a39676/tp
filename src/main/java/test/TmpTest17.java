package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class TmpTest17 {

	public static void main(String[] args) {

		Integer grade = 6;
		String mainUrl = "https://download.pep.com.cn/xsxjc/22xjcsx" + grade + "1x/files/mobile/_.jpg";
		String mainFolder = "D:/auxiliary/textbook/" + grade + "/math/1";
		Integer maxPage = 126;

		String tmpUrl = null;
		for (int i = 1; i < maxPage + 1; i++) {
			tmpUrl = mainUrl.replaceAll("_", String.valueOf(i));
			System.out.println(tmpUrl);
			try {
				saveImg(tmpUrl, (String.valueOf(i) + ".jpg"), mainFolder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String saveImg(String url, String newFileName, String folderPath) throws IOException {
		URL imageURL = new URL(url);
		BufferedImage saveImage = ImageIO.read(imageURL);
		String suffix = "jpg";

		String filePath = folderPath + "/" + newFileName;
		ImageIO.write(saveImage, suffix, new File(filePath));

		System.out.println(filePath);

		return filePath;
	}
}
