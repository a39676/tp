package test;

import java.io.File;

public class Tmp33 {

	public static void main(String[] args) {
		String path = System.getProperty("user.home") ;
		File f = new File(path);
		System.out.println(f.exists());
		System.out.println(f.getAbsolutePath());
	}

}
