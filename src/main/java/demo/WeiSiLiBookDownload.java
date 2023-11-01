package demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import toolPack.ioHandle.FileUtilCustom;

public class WeiSiLiBookDownload {
	
	private static String urlModel = "https://www.xuges.com/kh/nk/%s/%s.htm";
	private static 	FileUtilCustom f = new FileUtilCustom();
	private static String targetSavingFolderPath = "D:/nextG/niKuang";
	private static Map<String, String> missionMap = new HashMap<>();

	public static void main(String[] args) throws Exception {
		collect();
	}
	
	public static void collect() throws Exception {
		for(Entry<String, String> entry : missionMap.entrySet()) {
			try {
				String targetSavingPath = targetSavingFolderPath + "/" + entry.getValue();
				for (int i = 1; i < 999; i++) {
					String numStr = null;
					if (i < 10) {
						numStr = "0" + i;
					} else {
						numStr = String.valueOf(i);
					}
					String url = String.format(urlModel, entry.getKey(), numStr);
					System.out.println(url);
					String content = getContent(url);
					content = content.replaceAll("<td>", "").replaceAll("</td>", "").replaceAll("<br>", "\n");
					f.byteToFile(content.getBytes(StandardCharsets.UTF_8), targetSavingPath, true);
				}
			} catch (Exception e) {
				continue;
			}
		}
	}

	public static String getContent(String url) throws Exception {
		URL obj = new URI(url).toURL();
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "gb2312"));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
//		response.append(con.getResponseMessage());
//		response.append(con.getHeaderFields());
		in.close();

		Document html = Jsoup.parse(response.toString());

		Elements td = html.select("body").select("table").select("tbody").select("tr").get(3).select("td");
		String content = td.toString();
		return content;
	}
	
	static {
		
		missionMap.put("lzqm",  "294六指琴魔.txt");
		missionMap.put("zqsj",  "295紫青双剑录.txt");
		missionMap.put("lhs",  "296龙虎双剑侠.txt");
		missionMap.put("btxn",  "297冰天侠女.txt");
	}
}
