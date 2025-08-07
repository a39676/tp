package test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Tmp35 {

	public static void main(String[] args) {
		File f = new File(System.getProperty("user.home") + "/tmp/test1");
		System.out.println(f.getAbsolutePath());
		System.out.println(f.exists());
		File f2 = new File(System.getProperty("user.home") + "/tmp/test2");
		System.out.println(f2.getAbsolutePath());
		
		try {
			FileUtils.moveDirectory(f, f2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(f2.exists());
	}
}
