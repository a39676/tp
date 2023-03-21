package test;

import java.util.Date;

import toolPack.encryptHandle.EncryptUtil;

public class Tmp18 {

	public static void main(String[] args) throws Exception {
		String str = "jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s";
		EncryptUtil eu = new EncryptUtil();
		str = eu.sha1(str);
		System.out.println(str);
		
		Date d = new Date();
		System.out.println(d.getTime() / 1000);
	}

}
