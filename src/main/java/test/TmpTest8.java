package test;

import toolPack.httpHandel.HttpUtil;

public class TmpTest8 {
	
	public static void main(String[] args) throws Exception {
		
		HttpUtil h = new HttpUtil();
		String s = h.sendGet("http://wttr.in/Guangzhou?lang=zh-cn");
		System.out.println(s);
	}

}
