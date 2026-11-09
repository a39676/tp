package taobao.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import toolPack.httpHandel.HttpUtil;

public class SourceIdList {

	private static List<String> idList = new ArrayList<>();
//	private static FileUtilCustom ioUtil = new FileUtilCustom();
	private static HttpUtil http = new HttpUtil();

	public static void main(String[] args) {

	}

	static {
//		String content = ioUtil.getStringFromFile(TaobaoOption.ID_LIST.toString());
//		idList.addAll(Arrays.asList(content.split(System.lineSeparator())));
		try {
			String content = http
					.sendGet("https://" + TaobaoOption.getHostname() + TaobaoOption.getSellingSourceProductUrl());
			JSONObject json = JSONObject.fromObject(content);
			String idListStr = json.getString("message");
			idListStr = idListStr.replaceAll("\\[", "").replaceAll("\\]", "");
			String[] idStrArray = idListStr.split(",");
			for (int i = 0; i < idStrArray.length; i++) {
				idList.add(idStrArray[i].trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(idList);
	}

	public static List<String> getIdList() {
		return idList;
	}

	public static void setIdList(List<String> idList) {
		SourceIdList.idList = idList;
	}

}
