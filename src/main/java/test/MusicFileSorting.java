package test;

import java.io.File;

public class MusicFileSorting {

	private final static String MAIN_PATH = "D:/mu";

	public void deleteLongKeyFile(String path) {
		if (path == null) {
			path = MAIN_PATH;
		}
		File folder = new File(path);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				deleteLongKeyFile(file.getAbsolutePath());
			}
			if (file.getName().endsWith("longkey")) {
				System.out.println(file.getAbsolutePath());
//				file.delete();
			}
		}
	}
	
	public void deleteEmptyFolder(String path) {
//		TODO
		if (path == null) {
			path = MAIN_PATH;
		}
		File folder = new File(path);
		File[] files = folder.listFiles();
		
		for (File file : files) {
			if (file.isDirectory()) {
				deleteEmptyFolder(file.getAbsolutePath());
			}
			if (file.getName().endsWith("longkey")) {
				System.out.println(file.getAbsolutePath());
//				file.delete();
			}
		}
	}

	public static void main(String[] args) {
		MusicFileSorting m = new MusicFileSorting();
		m.deleteLongKeyFile(MAIN_PATH);
	}

}
