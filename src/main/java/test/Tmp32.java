package test;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import toolPack.ioHandle.FileUtilCustom;

public class Tmp32 {

	public static void main(String[] args) throws MalformedURLException, IOException {
		String urlModel = "https://bdhlsvodhls02.vhallyun.com/vhallyun/vhallcoop"
				+ "/1a6a8f6e8e45f953fa7c5f043cf340b5/02e6e834/1a6a8f6e8e45f953fa7c5f043cf340b5_raw" + "/index_%s.ts";
		String intStr = null;
		String urlStr = null;
		String filePathStr = null;
		StringBuilder sb = new StringBuilder();
		try {
			for (int i = 0; i < 99999; i++) {
				intStr = String.format("%06d", i);
				urlStr = String.format(urlModel, intStr);
//				System.out.println(urlStr);
				BufferedInputStream in = new BufferedInputStream(URL.of(URI.create(urlStr), null).openStream());
				filePathStr = "C:/Users/daven/tmp/tmpLession/" + intStr + ".ts";
//				System.out.println(filePathStr);
				System.out.println("file " + "'" + filePathStr + "'");
				sb.append("file " + "'" + filePathStr + "' \r\n");
				FileOutputStream fileOutputStream = new FileOutputStream(filePathStr);
				byte dataBuffer[] = new byte[1024];
				int bytesRead;
				while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
					fileOutputStream.write(dataBuffer, 0, bytesRead);
				}
				fileOutputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		FileUtilCustom iou = new FileUtilCustom();
		String listTxtPathStr = "C:\\Users\\daven\\tmp\\tmpLession/list.txt";
		iou.byteToFile(sb.toString().getBytes(), listTxtPathStr);

//		Process p = Runtime.getRuntime().exec(
//				"ffmpeg -f concat -safe 0 -i \"C:/Users/daven/tmp/tmpLession/list.txt\" -c copy \"C:/Users/daven/tmp/tmpLessionOutput/15.mp4\"");
//		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
//		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//		String s = null;
//
//		// read the output from the command
//		System.out.println("Here is the standard output of the command:\n");
//		while ((s = stdInput.readLine()) != null) {
//			System.out.println(s);
//		}
//
//		// read any errors from the attempted command
//		System.out.println("Here is the standard error of the command (if any):\n");
//		while ((s = stdError.readLine()) != null) {
//			System.out.println(s);
//		}
	}
}
