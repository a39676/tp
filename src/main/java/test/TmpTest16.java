package test;

import net.sf.json.JSONObject;
import toolPack.httpHandel.HttpUtil;

public class TmpTest16 {

	public static void main(String[] args) throws Exception {
		HttpUtil h = new HttpUtil();

		String url = "http://search.gd.gov.cn/api/search/all";
		String paramStr = "{\n"
				+ "  \"keywords\": \"招生\",\n"
				+ "  \"sort\": \"smart\",\n"
				+ "  \"site_id\": \"200016\",\n"
				+ "  \"range\": \"site\",\n"
				+ "  \"position\": \"title\",\n"
				+ "  \"page\": 1,\n"
				+ "  \"recommand\": 1,\n"
				+ "  \"gdbsDivision\": \"440100\",\n"
				+ "  \"gdbsOrgNum\": \"007482620\",\n"
				+ "  \"service_area\": 200\n"
				+ "}";
		String result = h.sendPost(url, paramStr);
		JSONObject j = JSONObject.fromObject(result);
		System.out.println("result: ");
		System.out.println(j.toString());
		
	}
}
