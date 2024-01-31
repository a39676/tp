package demo.storyberries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class StoryBerriesDownloader {

	private static String host = "https://www.storyberries.com";
	private static String imageUrl = host
			+ "/wp-content/uploads/2022/07/Bedtime-stories-Ollie-the-Octopus-and-Sukey-the-Spider-short-stories-for-kids-page_%d.jpg";
	private static Integer start = 1;
	private static Integer end = 44;
	private static String title = "OllieTheOctopusAndSukeyTheSpider";
	private static String mainFolder = "D:/tmp/englishBook";

	public static void main(String[] args) throws Exception {
		StoryBerriesDownloader t = new StoryBerriesDownloader();
		for (int i = start; i <= end; i++) {
			try {
				t.d(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void d(int i) throws Exception {
		String targetImageUrl = String.format(imageUrl, i);
		URL url = new URI(targetImageUrl).toURL();
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		File folder = new File(mainFolder + "/" + title);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		InputStream input = connection.getInputStream();
		OutputStream output = new FileOutputStream(folder + "/" + i + ".jpg");

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
