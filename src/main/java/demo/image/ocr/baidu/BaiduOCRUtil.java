package demo.image.ocr.baidu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class BaiduOCRUtil {

	// 设置APPID/AK/SK
	private static final String APP_ID = "15933733";
	private static final String API_KEY = "agfe0prCBcIT8IXxZBOlyNlq";
	private static final String SECRET_KEY = "P6AGj0wjROgXMtGekgUt4xtXP6rPn5Z1";

	public static String ocr(String filePath, HashMap<String, String> options) {
		// 初始化一个AipOcr
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

		// 可选：设置log4j日志输出格式，若不设置，则使用默认配置
		// 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

		// 参数为本地路径
//		String filePath = "D:\\auxiliary\\platenogroup\\窝趣通讯录截图\\QQ截图20190404183428.png";
		JSONObject res = client.basicGeneral(filePath, options);
		System.out.println(res.toString(2));
		return res.toString(2);
	}

	public static String ocr(String filePath) {

		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("language_type", "CHN_ENG");
		options.put("detect_direction", "true");
		options.put("detect_language", "true");
		options.put("probability", "true");

		return ocr(filePath, options);
	}
	
	public static List<String> orc(List<String> filePathList) {
				
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("language_type", "CHN_ENG");
		options.put("detect_direction", "true");
		options.put("detect_language", "true");
		options.put("probability", "true");
		
		return orc(filePathList, options);
	}
	
	public static List<String> orc(List<String> filePathList, HashMap<String, String> options) {
		// 初始化一个AipOcr
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
				
		JSONObject res = null;
		List<String> resultList = new ArrayList<String>();
		for(String filePath : filePathList) {
			res = client.basicGeneral(filePath, options);
			System.out.println(res.toString(2));
			resultList.add(res.toString(2));
		}
		
		return resultList;
	}
}
