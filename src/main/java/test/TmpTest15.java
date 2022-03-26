package test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class TmpTest15 {

	public static void main(String[] args) throws IOException {

		String proxyHost = "127.0.0.1";
		String proxyPort = "10809";

		System.setProperty("http.proxyHost", proxyHost);
		System.setProperty("http.proxyPort", proxyPort);

		System.setProperty("https.proxyHost", proxyHost);
		System.setProperty("https.proxyPort", proxyPort);

		URL url = new URL("https://google.com");
		URLConnection connection = url.openConnection();
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		byte[] bytes = new byte[1024];
		while (inputStream.read(bytes) >= 0) {
			System.out.println(new String(bytes));
		}
	}
}
