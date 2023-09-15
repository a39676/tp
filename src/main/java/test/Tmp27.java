package test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Tmp27 {

	public static void main(String[] args) throws Exception {
		Tmp27 t = new Tmp27();
		for (int i = 1; i < 46; i++) {
			try {
				t.d(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void d(int i) throws Exception {
		String imageUrl = "https://www.storyberries.com/wp-content/uploads/2023/03/Bedtime-Stories-I-Can-Try-I-Can-Fly-by-Prarthana-Gururaj-short-stories-for-kids-page_"
				+ i + ".jpg";

		URL url = new URL(imageUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		InputStream input = connection.getInputStream();
		OutputStream output = new FileOutputStream("d:/tmp/tmp1/" + i + ".jpg");

		int b;
		byte[] buffer = new byte[2048];
		while ((b = input.read(buffer)) != -1) {
			output.write(buffer, 0, b);
		}

		input.close();
		output.close();

		System.out.println(i + "Image downloaded successfully!");
	}
}
