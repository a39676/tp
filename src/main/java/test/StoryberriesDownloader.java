package test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class StoryberriesDownloader {

	public static void main(String[] args) throws Exception {
		StoryberriesDownloader t = new StoryberriesDownloader();
		for (int i = 1; i < 48; i++) {
			try {
				t.d(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void d(int i) throws Exception {
		String host = "https://www.storyberries.com";
		String imageUrl = host + "/wp-content/uploads/2023/10/Bedtime-stories-The-Witchs-Notebook-Halloween-stories-for-kids-page_"
				+ i + ".jpg";

		URL url = new URI(imageUrl).toURL();
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

		System.out.println(i + ", Image downloaded successfully!");
	}
}
