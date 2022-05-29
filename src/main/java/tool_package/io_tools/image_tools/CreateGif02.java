package tool_package.io_tools.image_tools;

import java.awt.Color;
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
			e.setTransparent(Color.WHITE);
			BufferedImage src[] = new BufferedImage[filePaths.size()];
			for (int i = 0; i < src.length; i++) {
				e.setDelay(100);
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
		String mainFolderPathStr = "D:/joyxy/joyxyImgSourceBackup/MotionData_Monster/BODY_monster_000004a-1";
		filePaths.add(mainFolderPathStr + "/BODY_monster_000004a-1_6-1.png");
		filePaths.add(mainFolderPathStr + "/BODY_monster_000004a-1_6-2.png");
		filePaths.add(mainFolderPathStr + "/BODY_monster_000004a-1_6-3.png");
		filePaths.add(mainFolderPathStr + "/BODY_monster_000004a-1_6-4.png");
		filePaths.add(mainFolderPathStr + "/BODY_monster_000004a-1_6-5.png");
		filePaths.add(mainFolderPathStr + "/BODY_monster_000004a-1_6-6.png");
		filePaths.add(mainFolderPathStr + "/BODY_monster_000004a-1_6-7.png");
		filePaths.add(mainFolderPathStr + "/BODY_monster_000004a-1_6-8.png");
		jpgToGif(filePaths, "D:/tmp/output/output1.gif");
	}
}
