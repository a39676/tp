package test;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Tmp26 {

	public static void main(String[] args) throws IOException {

		System.out.println(
				URLEncoder.encode("width:100%; object-fit: contain;", StandardCharsets.UTF_8));
	}
}
