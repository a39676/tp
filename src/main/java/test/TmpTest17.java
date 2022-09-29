package test;

public class TmpTest17 {

	public static void main(String[] args) throws Exception {
		String str = "abcABC_123";
		str = str.replaceAll("[^a-zA-Z_]", "");
		System.out.println(str);
	}
}
