package test.imageHostUploadDemo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

public class ImageHostUpload {
	
	private static String targetFloderPath = "D:\\imageCache\\20180828\\test";
	private static String uploadUrl = "https://sm.ms/api/upload";
	
	private static String uploadImage(String filePath) throws MalformedURLException, IOException {
		String charset = "UTF-8";
		String param = "value";
		File imageFile = new File(filePath);
		String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
		String CRLF = "\r\n"; // Line separator required by multipart/form-data.

		URLConnection connection = new URL(uploadUrl).openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

		try (
		    OutputStream output = connection.getOutputStream();
		    PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
		) {
		    // Send normal param.
		    writer.append("--" + boundary).append(CRLF);
		    writer.append("Content-Disposition: form-data; name=\"param\"").append(CRLF);
		    writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
		    writer.append(CRLF).append(param).append(CRLF).flush();

		    // Send binary file.
		    writer.append("--" + boundary).append(CRLF);
		    writer.append("Content-Disposition: form-data; name=\"smfile\"; filename=\"" + imageFile.getName() + "\"").append(CRLF);
		    writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(imageFile.getName())).append(CRLF);
//		    writer.append("Content-Transfer-Encoding: binary").append(CRLF);
		    writer.append(CRLF).flush();
		    Files.copy(imageFile.toPath(), output);
		    output.flush(); // Important before continuing with writer!
		    writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

		    // End of multipart/form-data.
		    writer.append("--" + boundary + "--").append(CRLF).flush();
		}

		// Request is lazily fired whenever you need to obtain information about response.
		HttpURLConnection urlConnection = ((HttpURLConnection) connection);
		int responseCode = ((HttpURLConnection) connection).getResponseCode();
		System.out.println(responseCode); // Should be 200
		InputStream in = urlConnection.getInputStream();
		String encoding = urlConnection.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		String body = IOUtils.toString(in, encoding);
		System.out.println(body);
		return body;
		
	}
	
	public static void main(String[] args) throws MalformedURLException, IOException  {
		File targetFolder = new File(targetFloderPath);
		File[] files = targetFolder.listFiles();
		
		List<String> resultList = new ArrayList<String>();
		String tmpResult = null;
		for(File f : files) {
			tmpResult = uploadImage(f.getAbsolutePath());
			if(!StringUtils.isBlank(tmpResult)) {
				resultList.add(tmpResult);
			}
		}
		
		JSONObject tmpJson = null;
		for(String r : resultList) {
			tmpJson = JSONObject.fromObject(r);
			System.out.println(tmpJson);
		}
	}

}
