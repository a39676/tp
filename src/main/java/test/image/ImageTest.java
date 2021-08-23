package test.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import toolPack.ioHandle.ImageTool;

public class ImageTest {

	public static void main(String[] args) throws IOException {
		ImageTool imgTool = new ImageTool();
		
		String mainFolderPathStr = "D:/tmp/orna";

		BufferedImage bi1 = ImageIO.read(new File(mainFolderPathStr + "/preFightingWeak.png"));
		Integer rgb1 = bi1.getRGB(864, 574);
		System.out.println(rgb1);
		System.out.println(imgTool.hasRGB(bi1, -34493, 1, 2, 2, 629));
		
		BufferedImage bi2 = ImageIO.read(new File(mainFolderPathStr + "/mainMap2.png"));
		Integer rgb2 = bi2.getRGB(650, 501);
		System.out.println(rgb2);
//		rgb2 = bi2.getRGB(914, 571);
//		System.out.println(rgb2);
//		rgb2 = bi2.getRGB(1362, 300);
//		System.out.println(rgb2);
		
	}
	
}
