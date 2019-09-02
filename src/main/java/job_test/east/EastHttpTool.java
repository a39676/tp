package job_test.east;

import java.util.HashMap;

import httpHandel.HttpUtil;

/**
 * 东方星空,url测试工具
 * 网络通讯模块
 */
public class EastHttpTool {
	
	private HttpUtil httpUtil = new HttpUtil();
	
	public String sendGet(String url, HashMap<String, String> keyValues) {
		try {
			return httpUtil.sendGet(url, keyValues);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String sendGet(String url) {
		try {
			return httpUtil.sendGet(url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String sendPost(String url, String jsonDatas ) {
		try {
			return httpUtil.sendPost(url, jsonDatas);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
