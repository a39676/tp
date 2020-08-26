package test;

import java.io.File;

public class TmpTest9 {

	private static String sourcePath = "D:/home/u2/joy/icon/item";

	public static void main(String[] args) throws Exception {
		TmpTest9 t = new TmpTest9();
		t.handleAll(sourcePath);
	}
	
	public void handleAll(String sourcePath) {
		File mainFolder = new File(sourcePath);
		File[] fileList = mainFolder.listFiles();
		for(File f : fileList) {
			if(f.isDirectory()) {
				System.out.println(f.getName());
			} else {
			}
		}
	}
	
	
}
