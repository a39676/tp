package test;

import net.sf.json.JSONObject;
import toolPack.ioHandle.FileUtilCustom;

public class TmpTest11 {

	public static void main(String[] args) {

		FileUtilCustom io = new FileUtilCustom();
		
		String str = io.getStringFromFile("d:/home/u2/bbt/autoTestParameterFiles/cryptoCompare/socketConfig.json");
		
		JSONObject j = JSONObject.fromObject(str);
		
		System.out.println(j);
	}
}