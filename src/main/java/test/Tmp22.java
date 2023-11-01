package test;

import java.io.IOException;
import java.net.URISyntaxException;

import net.sf.json.JSONObject;
import toolPack.httpHandel.HttpUtil;
import toolPack.ioHandle.FileUtilCustom;

public class Tmp22 {

	private static String mainUrl = "http://localhost:10001";

	public static void main(String[] args) throws IOException, URISyntaxException {

//		System.out.println(Byte.MAX_VALUE);
//		System.out.println(Byte.MIN_VALUE);
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(Long.MAX_VALUE);
//
//		String s = "u2raLOYFJa+=";
//		System.out.println(URLEncoder.encode(s, StandardCharsets.UTF_8));

		String jobPk = sendRequest();
		System.out.println(jobPk);

//		for (int i = 0; i < 10; i++) {
////			sendRequest(i);
//			sendRequest(i);
//		}
//		String jobPk = "PbteyqZh+=";
//		queryJob(jobPk);
	}

	public static String sendRequest() throws IOException, URISyntaxException {
		return sendRequest(null);
	}
	
	public static String sendRequest(Integer i) throws IOException, URISyntaxException {
		FileUtilCustom f = new FileUtilCustom();
		String content = f.getStringFromFile("d:/tmp/tmp.json");

		JSONObject json = JSONObject.fromObject(content);
		if(i != null) {
			json.put("prompts", String.valueOf(i));
		}

		System.out.println(json.toString());

		HttpUtil h = new HttpUtil();
		String response = h.sendPostRestful(mainUrl + "/aiArtApi/textToImage", json.toString());
		System.out.println(response);

		json = JSONObject.fromObject(response);
		return json.getString("jobPk");
	}

	public static void queryJob(String jobPk) throws IOException, URISyntaxException {
		HttpUtil h = new HttpUtil();
		JSONObject json = new JSONObject();
		json.put("pk", jobPk);
		String response = h.sendPostRestful(mainUrl + "/aiArtApi/getJobResultByPk", json.toString());
		System.out.println(response);
	}

//	{"success":true,"code":"0","message":"","jobPk":"=","result":"0","fail":false}

}
