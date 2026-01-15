package test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import toolPack.httpHandel.HttpUtil;

public class Tmp39 {

	public static void main(String[] args) throws IOException, URISyntaxException {
		
		String proxyHost = "127.0.0.1";
		String proxyPort = "10809";

		System.setProperty("http.proxyHost", proxyHost);
		System.setProperty("http.proxyPort", proxyPort);

		System.setProperty("https.proxyHost", proxyHost);
		System.setProperty("https.proxyPort", proxyPort);
		
		HttpUtil h = new HttpUtil();
		String url = "https://prod.ave-api.com/v2/klines/token/0xcae117ca6bc8a341d2e7207f30e180f0e5618b9d-bsc?interval=5&size=5&limit=10";
		Map<String, String> kvMap = new HashMap<>();
		kvMap.put("X-API-KEY", "");
		String result = h.sendGet(url, new HashMap<>(), kvMap);
		System.out.println(result);
	}
}
