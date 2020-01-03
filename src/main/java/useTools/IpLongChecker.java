package useTools;

import java.util.HashSet;
import java.util.Set;

import toolPack.httpHandel.HttpUtil;

public class IpLongChecker {

	private static String tmpKey = "ee656d8923ebc4a32df5993754dbd019";
	private static String baseUrl = "http://api.ipstack.com/";
	
	
	public static void main(String[] args) throws Exception {
		HttpUtil hu = new HttpUtil();
		
		Set<String> ipSet = new HashSet<String>();
		ipSet.add("");
		
		for(String ip : ipSet) {
			System.out.println(hu.sendGet(baseUrl + ip + "?access_key=" + tmpKey, null));
		}
		
	}
}
