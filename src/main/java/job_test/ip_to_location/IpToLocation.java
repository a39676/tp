package job_test.ip_to_location;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import ioHandle.FileUtilCustom;
import tool_package.http_tools.JuHeHttpTool;

@Service
public class IpToLocation{
	
//	http://restapi.amap.com/v3/ip?parameters
	private FileUtilCustom ioTool = new FileUtilCustom();
	private String opNumberPart = "178";
//	private Integer numberStart = 0;
//	private Integer numberEnd = 9999;
	private JuHeHttpTool hc = new JuHeHttpTool();
	private String outputResultFilePath = "d:/auxiliary/ipProject/" + opNumberPart + ".txt";
	
	public void IpToLocation01() throws InterruptedException {
		String mainUrl = "http://restapi.amap.com/v3/ip";
		
		List<String> keys = new ArrayList<String>();
		keys.add("e8d4b812a4c5c57f75d8488007d62c8b");
		
		ArrayList<String> ipList = new ArrayList<String>();
		ipList.add("114.247.50.2");
		String urlUse = null;
		
		int count = 0;
		StringBuilder sb = new StringBuilder();
		while(!ipList.isEmpty()) {
//			createUrls(String mainUrl, String key, String ip, String output, String sig)
			urlUse = createUrls(mainUrl, keys.get(0), ipList.get(0), "json");
			System.out.println(urlUse);
			sb.append(ipList.get(0) + " : " + hc.get(urlUse) + "\n");
			TimeUnit.MICROSECONDS.sleep(ThreadLocalRandom.current().nextInt(80, 120));
			ipList.remove(0);
			count += 1;
			if(count % 300 == 0 || ipList.isEmpty()) {
				System.out.println(opNumberPart + "-" + count + " : " + new Date());
				ioTool.byteToFileAppendAtEnd(sb.toString().getBytes(), outputResultFilePath);
				sb.setLength(0);
			}
		}
		
		
		
	}
	
	public void writeToFile(String str) {
		ioTool.byteToFileAppendAtEnd(str.getBytes(), outputResultFilePath);
	}
	
	public String createUrls(String mainUrl, String key, String ip, String output) {
		return createUrls(mainUrl, key, ip, output, null);
	}
	
	public String createUrls(String mainUrl, String key, String ip, String output, String sig) {
		String urlOutput = mainUrl + "?key=" + key + "&ip=" + ip;
		if(sig != null && sig.length() > 0) {
			urlOutput = urlOutput + "&sig=" + sig;
		}
		if(output.equals("xml")) {
			urlOutput = urlOutput + "&output=" + output;
		} else {
			urlOutput = urlOutput + "&output=json";
		}
		return urlOutput;
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		IpToLocation tool01 = new IpToLocation();
		
		tool01.IpToLocation01();
	
	}
}
