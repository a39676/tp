package test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Tmp23 {

	public static void main(String[] args) {
		String str = "/q7w5/g96dtgPKoCGgWygTZ1ZYdBerl+QdPuQ8d1A5A=";
		System.out.println(URLEncoder.encode(str, StandardCharsets.UTF_8));
	}
}
