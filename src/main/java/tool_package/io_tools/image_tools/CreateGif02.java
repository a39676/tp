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
	
	private String outputPathStr = "D:/tmp/output";
	private String mainSourceFolderPathStr = "D:/joyxy/joyxyImgSource/MotionData_Monster";

	/**
	 * 把多张jpg图片合成一张
	 * 
	 * @param pic
	 *            String[] 多个jpg文件名 包含路径
	 * @param newPic
	 *            String 生成的gif文件名 包含路径
	 */
	public void jpgToGif(List<String> filePaths, String newPic, Integer xStart, Integer yStart, Integer width, Integer height) {
		try {
			AnimatedGifEncoder e = new AnimatedGifEncoder();
			e.setRepeat(0);
			e.start(newPic);
			e.setQuality(1);
			e.setTransparent(Color.WHITE);
			BufferedImage src[] = new BufferedImage[filePaths.size()];
			BufferedImage tmpBufferedImage = null;
			for (int i = 0; i < src.length; i++) {
				e.setDelay(100);
				tmpBufferedImage = ImageIO.read(new File(filePaths.get(i)));
				if(xStart != null) {
					tmpBufferedImage = tmpBufferedImage.getSubimage(xStart, yStart, width, height);
				}
				src[i] = tmpBufferedImage;
				e.addFrame(src[i]); 
			}
			e.finish();
		} catch (Exception e) {
			System.out.println("jpgToGif Failed:");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		CreateGif02 t = new CreateGif02();
		String monsterFolderName = "BODY_monster_158-2";
		int exceptNumber = 1;
		t.createMonsterGif(monsterFolderName, exceptNumber,176, 119, 50, 80);
	}
	
	public void createMonsterGif(String monsterFolderName, Integer exceptNumber, Integer xStart, Integer yStart, Integer width, Integer height) {
		String monsterName = monsterFolderName;
		String mainFolderPathStr = mainSourceFolderPathStr + "/" + monsterName;
		
		File monsterFolder = new File(mainFolderPathStr);
		File[] files = monsterFolder.listFiles();
		List<String> filePaths = new ArrayList<String>();
		for(File file : files) {
			String filename = file.getName();
			if(exceptNumber != null) {
				try {
					Integer num = Integer.parseInt(String.valueOf(filename.charAt(monsterName.length() + 1)));
					if(num != exceptNumber) {
						System.out.println(filename);
						filePaths.add(file.getAbsolutePath());
					}
				} catch (Exception e) {
				}
			} else {
				filePaths.add(file.getAbsolutePath());
			}
		}
		
		jpgToGif(filePaths, outputPathStr + "/" + monsterName + ".gif", xStart, yStart, width, height);
		
	}
	
	public void createNpcGif(String monsterFolderName, Integer xStart, Integer yStart, Integer width, Integer height) {
		createMonsterGif(monsterFolderName, null, xStart, yStart, width, height);
	}

}
