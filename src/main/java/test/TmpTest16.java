package test;

import java.io.File;

public class TmpTest16 {

	public static void main(String[] args) throws Exception {

		String mainFolderPathStr = "D:/tmp/test";
		
		File mainFolder = new File(mainFolderPathStr);
		
		for(File subFolder : mainFolder.listFiles()) {
			File[] subSubFiles = subFolder.listFiles();
			for(File file : subSubFiles) {
				if(file.getName().endsWith("txt")) {
					try {
						file.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
