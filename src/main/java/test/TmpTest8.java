package test;

import java.io.File;

public class TmpTest8 {

	private static String sourcePath = "D:\\home\\u2\\joy";

	public static void main(String[] args) throws Exception {
		TmpTest8 t = new TmpTest8();
		t.renameAll(sourcePath);
	}
	
	public void renameAll(String sourcePath) {
		File mainFolder = new File(sourcePath);
		File[] fileList = mainFolder.listFiles();
		for(File f : fileList) {
			if(f.isDirectory()) {
				renameAll(f.getAbsolutePath());
			} else {
				renameRemove(f);
			}
		}
	}
	
	public void renameRemove(File f) {
		String sourcePath = f.getAbsolutePath();
		String targetPath = sourcePath.replaceAll("_1-1.png", ".png");
		
		File targetFile = new File(targetPath);
		
		f.renameTo(targetFile);
	}

	
}
