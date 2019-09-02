package tool_package.io_tools.image_tools;

//import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.madgag.gif.fmsware.AnimatedGifEncoder;

public class CreateGif02 {

	/**
	 * 把多张jpg图片合成一张
	 * 
	 * @param pic
	 *            String[] 多个jpg文件名 包含路径
	 * @param newPic
	 *            String 生成的gif文件名 包含路径
	 */
	public static void jpgToGif(List<String> filePaths, String newPic) {
		try {
			AnimatedGifEncoder e = new AnimatedGifEncoder();
			e.setRepeat(0);
			e.start(newPic);
			BufferedImage src[] = new BufferedImage[filePaths.size()];
			for (int i = 0; i < src.length; i++) {
				e.setDelay(200); 
				src[i] = ImageIO.read(new File(filePaths.get(i))); 
				e.addFrame(src[i]); 
			}
			e.finish();
		} catch (Exception e) {
			System.out.println("jpgToGif Failed:");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		List<String> filePaths = new ArrayList<String>();
		filePaths.add("D:/auxiliary/tmp/01/BODY_monster_000001a_1-1.png");
		filePaths.add("D:/auxiliary/tmp/01/BODY_monster_000001a_1-2.png");
		filePaths.add("D:/auxiliary/tmp/01/BODY_monster_000001a_1-3.png");
		filePaths.add("D:/auxiliary/tmp/01/BODY_monster_000001a_1-4.png");
		filePaths.add("D:/auxiliary/tmp/01/BODY_monster_000001a_1-5.png");
		filePaths.add("D:/auxiliary/tmp/01/BODY_monster_000001a_1-6.png");
		filePaths.add("D:/auxiliary/tmp/01/BODY_monster_000001a_1-7.png");
		filePaths.add("D:/auxiliary/tmp/01/BODY_monster_000001a_1-8.png");
		filePaths.add("D:/auxiliary/tmp/01/BODY_monster_000001a_1-9.png");
		jpgToGif(filePaths, "D:/auxiliary/tmp/01/output1.gif");
	}
}
