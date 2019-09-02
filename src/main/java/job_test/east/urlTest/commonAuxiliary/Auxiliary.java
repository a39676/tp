package job_test.east.urlTest.commonAuxiliary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ioHandle.FileUtilCustom;
import job_test.east.urlTest.ttjk.TtjkAuxiliary;
import job_test.east.urlTest.ttqd.TtqdAuxiliary;
import job_test.east.urlTest.xkMall.XkMallAuxiliary;
import net.sf.json.JSONObject;

/**
 * 东方星空,url测试工具
 * 基础数据, 基础逻辑部分, 通用变量部分
 */
public class Auxiliary {

	protected static final String USER_AGENT = "Fiddler";
	protected static String mainHost = "";
	protected static String idName = "";
	
	protected static HashMap<String, String> userInfoMap = new HashMap<String, String>();
	protected static List<UrlTestDomain> testUrlDomains = new ArrayList<UrlTestDomain>();
	protected static HashMap<String, String> requestHeaderKeys;
	
	protected String mainFolder = "d:/auxiliary/east/";
	protected String tmpUserFile = "tmpUser.txt";
	
	public static void setProject(int projectNum) {
		if(projectNum == UrlTestConstant.ttqd) {
			mainHost = "http://localhost:9003/";
//			mainHost = "https://apits.ttqiandai.com/";
			idName = UrlTestConstant.userId;
			testUrlDomains = TtqdAuxiliary.loadTtqdUrlDomains();
			
		} else if(projectNum == UrlTestConstant.xkMall) {
			mainHost = "http://localhost:8080/admin/";
			idName = UrlTestConstant.managerId;
			testUrlDomains = XkMallAuxiliary.loadXkMallUrlDomains();
			
		} else if(projectNum == UrlTestConstant.ttjk) {
			mainHost = "http://localhost:8080/admin/";
			idName = UrlTestConstant.operatorId;
			testUrlDomains = TtjkAuxiliary.loadTtjkUrlDomains();
			
		}
	}
	
	static {{
		requestHeaderKeys = new HashMap<String, String>();
		requestHeaderKeys.put("qqm-client", "system=Android/6.0;app=android-native/1.0.2;device=manufacturer:genymotion,model:samsung galaxy s6 - 6.0.0 - api 23 - 1440x2560,uuid:67d33568-340a-4798-b705-300774342b09,model:,imei/meid:;sim=number:,serial:,mcc:mnc:,mac:null;source=qita");
		userInfoMap.put("mobile", UrlTestConstant.DEFAULTUSERMOBILE);
		// 测试服务器默认短信验证码
		userInfoMap.put("vcode", UrlTestConstant.DEFAULTVCODE);
	}}
	
	public List<UrlTestDomain> getTestDomains() {
		return testUrlDomains;
	}
	
	public UrlTestDomain getTestUrl(String domainName) {
		for(UrlTestDomain td : testUrlDomains) {
			if(domainName.equals(td.getDomainName())) {
				return td;
			}
		}
		return null;
	}
	
	public boolean isStatus0(String result) {
		JSONObject json = JSONObject.fromObject(result);
		if(json.getString("status").equals("0")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean saveIdToken(String result) {
		JSONObject json;
		if(isStatus0(result)) {
			json = JSONObject.fromObject(result);
			FileUtilCustom fc = new FileUtilCustom();
			StringBuffer sb = new StringBuffer();
			sb.append(idName + ":" + json.getString(idName) + "\n");
			sb.append("token" + ":" + json.getString("token") + "\n");
			try {
				fc.byteToFile(sb.toString().getBytes("utf-8"), mainFolder + tmpUserFile);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	
	public String getToken() {
		if(!userInfoMap.containsKey("token") ) {
			getUserInfo();
		}
		return userInfoMap.get("token");
	}
	
	protected HashMap<String, String> getUserInfo() {
		if(!userInfoMap.containsKey(idName) ) {
			String userInfo = new FileUtilCustom().getStringFromFile(mainFolder + tmpUserFile);
			String[] lines = userInfo.split("\n");
			for(String line : lines) {
				userInfoMap.put(line.split(":")[0], line.split(":")[1]);
			}
		}
		return userInfoMap;
	}
	
	public String getId() {
		if (!userInfoMap.containsKey(idName)) {
			getUserInfo();
		}
		return userInfoMap.get(idName);
	}
}
