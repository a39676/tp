package test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TmpTest8 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String pk = "sMDE45RjV0Tqse2akvoF8zOAQeqgi+ZGUJitnOpgTwQ=";
		String urlEncodePK = URLEncoder.encode(pk, StandardCharsets.UTF_8.toString());
		System.out.println(urlEncodePK);
	}

}
