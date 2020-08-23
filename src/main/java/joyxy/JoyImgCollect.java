package joyxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * collect img resource
 *
 */
public class JoyImgCollect {

	static String sourceMainPath = "D:/joyxy/joyxyImgSource";
	static String sourceMonsterMainPath = sourceMainPath + "/MotionData_Monster";

	static String targetMainPath = "d:/home/u2/joy";
	static String targetMonsterMainPath = targetMainPath + "/monster";

	public static void main(String[] args) {
		JoyImgCollect t = new JoyImgCollect();
		t.collectMonsterImg();
	}

	public void collectMonsterImg() {
		File mainFolder = new File(sourceMonsterMainPath);
		File[] monsterFolders = mainFolder.listFiles();
		for (File subMonsterFolder : monsterFolders) {
			System.out.println(subMonsterFolder.getName() + ": " + subMonsterFolder.isDirectory());
			File[] monsterFiles = subMonsterFolder.listFiles();
			for (File f : monsterFiles) {
				if (f.isFile() && !f.getName().contains("txt")) {
					if (f.getName().contains("2-1.png") || f.getName().contains("3-1.png")
							|| f.getName().contains("4-1.png") || f.getName().contains("5-1.png")) {
						File target = new File(targetMonsterMainPath + "/" + f.getName());
						if (!target.getParentFile().exists()) {
							target.getParentFile().mkdirs();
						}
						try {
							copyFileUsingStream(f, target);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	private static void copyFileUsingStream(File source, File target) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(target);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}

}
