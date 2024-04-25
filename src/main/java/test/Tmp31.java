package test;

import java.io.IOException;
import java.net.URISyntaxException;

import toolPack.httpHandel.HttpUtil;

public class Tmp31 {

	public static void main(String[] args) {

		HttpUtil h = new HttpUtil();
		String url = "https://api.ipify.org/";
		try {
			String response = h.sendGet(url);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
