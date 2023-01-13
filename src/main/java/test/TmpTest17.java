package test;

import java.io.IOException;

public class TmpTest17 {

	public static void main(String[] args) throws IOException {
		
		
		t("3");
		
	}
	
	public static String t(String key) {
		switch (key) {
		case "1": {
			System.out.println("1");
			return "1";
		}
		case "2": {
			System.out.println("2");
			return "2";
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}
	}
}
