package test;

import java.io.File;

public class Tmp44 {

	public static void main(String[] args) {
		File f = new File("C:\\Users\\daven\\optionFile\\tp\\taobao\\updateInfo");
		for (File file : f.listFiles()) {
			System.out.println(file.getName());
		}
	}
}
