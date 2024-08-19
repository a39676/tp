package test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Tmp33 {

	public static void main(String[] args) throws IOException, URISyntaxException {
		File f = new File("D:\\tmp\\cryptoCoin\\kLine");
		File[] fs = f.listFiles();
		for (File ele : fs) {
			System.out.println(ele.getName());
		}
	}
}
