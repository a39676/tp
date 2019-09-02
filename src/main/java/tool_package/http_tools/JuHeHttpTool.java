package tool_package.http_tools;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import ioHandle.FileUtilCustom;

/**
 * 聚合数据http请求工具
 */
public class JuHeHttpTool {
	
	private FileUtilCustom t02 = new FileUtilCustom();
	
	private String targetUrl = "http://apis.juhe.cn/mobile/get?phone=13800138000&dtype=json&key=6f93fcb77088aa0f1d888945f23b4a4c";
	
	@SuppressWarnings("deprecation")
	public void post() {
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

		try {

		    HttpPost request = new HttpPost(targetUrl);
		    StringEntity params = new StringEntity(t02.getStringFromFile("d:/auxiliary/tmp/tmp.txt"));
//		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    
		    HttpResponse response = httpClient.execute(request);
		    System.out.println(response);


		}catch (Exception ex) {

		    ex.printStackTrace();

		} finally {
//		    Deprecated
		    httpClient.getConnectionManager().shutdown(); 
		}
	}
	
	public String get(String targetUrl) {
			
		try {
			URL url = new URL(targetUrl);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			String body = IOUtils.toString(in, encoding);
//			System.out.println(body);
			return body;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
			
		} finally {

		}
	}
	
	public static void main(String[] args) {
			
		JuHeHttpTool c = new JuHeHttpTool();	
		c.get(c.targetUrl);
			
	}
	
}
