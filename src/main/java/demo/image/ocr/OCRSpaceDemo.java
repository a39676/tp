package demo.image.ocr;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import httpHandel.HttpUtil;
import ioHandle.FileUtilCustom;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import numericHandel.NumericUtilCustom;

public class OCRSpaceDemo {

	String apiKey = "4f7a4effe988957";
	String language = "chs";
	String baseUrl = "https://api.ocr.space/parse/imageurl";
	String imageBaseUrl = "https://i.imgur.com/";
	String resultFilePath = "d:/auxiliary/tmp/nameCardResult02.txt";

	static List<String> imageHashList = new ArrayList<String>();

	static {
		imageHashList.add("6mI56GZ");
		imageHashList.add("yNZeoK2");
		imageHashList.add("d6Lcy6v");
		imageHashList.add("HlRa3pR");
		imageHashList.add("UdWYuoO");
		imageHashList.add("R6rVvBB");
		imageHashList.add("tLdwObF");
		imageHashList.add("f6KB3ft");
		imageHashList.add("iHgD9l0");
		imageHashList.add("5pcU2yJ");
		imageHashList.add("MJxtwrc");
		imageHashList.add("bYdL3bc");
		imageHashList.add("7vxtuhL");
		imageHashList.add("VVmsthZ");
		imageHashList.add("Bu86GA5");
		imageHashList.add("czpgLIl");
		imageHashList.add("6A2cXkj");
		imageHashList.add("zecbOaq");
		imageHashList.add("JEBFoIp");
		imageHashList.add("4plDypD");
		imageHashList.add("bhkSpcL");
		imageHashList.add("7EAbyRp");
		imageHashList.add("O2l8Dim");
		imageHashList.add("oKapH1z");
		imageHashList.add("vlVslyy");
		imageHashList.add("qjEjoOn");
		imageHashList.add("SbgwdWC");
		imageHashList.add("XAteqIX");
		imageHashList.add("w5t6rak");
		imageHashList.add("Ww7xTBF");
		imageHashList.add("cWiM1MW");
		imageHashList.add("bGBygsD");
		imageHashList.add("mszSJJb");
		imageHashList.add("3XGT3vi");
		imageHashList.add("kwSJIUr");
		imageHashList.add("HEc7PYF");
		imageHashList.add("xQEPNRj");
		imageHashList.add("bKVJlbF");
		imageHashList.add("V0nM2Me");
		imageHashList.add("G4YmVLu");
		imageHashList.add("NtyMHNm");
		imageHashList.add("yZpy7vY");
		imageHashList.add("FfbqA76");
		imageHashList.add("mQiWfLJ");
		imageHashList.add("tncf4AA");
		imageHashList.add("4oaFk7t");
		imageHashList.add("pdEdRXV");
		imageHashList.add("6543tPU");
		imageHashList.add("AJhs8kS");
		imageHashList.add("nsKH40T");
		imageHashList.add("MqMGigm");
		imageHashList.add("lIAYrgi");
		imageHashList.add("Q1KbNk3");
		imageHashList.add("JjZbpbU");
		imageHashList.add("ZLywvqj");
		imageHashList.add("MEMIlci");
		imageHashList.add("vME2J79");
		imageHashList.add("n2KgTTO");
		imageHashList.add("qY1uC8e");
		imageHashList.add("xZTRmJZ");
		imageHashList.add("mMr0HLr");
		imageHashList.add("PelhKaX");
		imageHashList.add("3DnyAZ3");
		imageHashList.add("Ov6gRmj");
		imageHashList.add("9cCNoSM");
		imageHashList.add("JUuTPFf");
		imageHashList.add("LgXnRbu");
		imageHashList.add("KMkWoTz");
		imageHashList.add("GE3bWKQ");
		imageHashList.add("rQKT0t1");
		imageHashList.add("G2uKwn9");
		imageHashList.add("J7acj2x");
		imageHashList.add("oowOIB3");
		imageHashList.add("WRjXmVI");
		imageHashList.add("1sxsW5m");
		imageHashList.add("2PlLdJJ");
		imageHashList.add("8Z6A9IN");
		imageHashList.add("BMwPCWv");
		imageHashList.add("bpzUq4a");
		imageHashList.add("zuTWg0Z");
		imageHashList.add("g8PcuXN");
		imageHashList.add("4T4WIFC");
		imageHashList.add("xKHj7OU");
		imageHashList.add("h6I89sz");
		imageHashList.add("EeAqHzi");
		imageHashList.add("fBV620t");
		imageHashList.add("dlsxObC");
		imageHashList.add("VUnXeFL");
		imageHashList.add("KxxWfeK");
		imageHashList.add("nb8JNKh");
		imageHashList.add("h7RlL4G");
		imageHashList.add("ZNzeedZ");
		imageHashList.add("qI0Ie9g");
		imageHashList.add("u9L1pxQ");
		imageHashList.add("JL7mnlW");
		imageHashList.add("B7jKd5p");
		imageHashList.add("UyqCnE6");
		imageHashList.add("mZevuwX");
		imageHashList.add("xS0S8jI");
		imageHashList.add("pJyc2Xd");
		imageHashList.add("LnK6Qfk");
		imageHashList.add("JlQVU0V");
		imageHashList.add("sOaTEvS");
		imageHashList.add("BZMKzb8");
		imageHashList.add("FFROYDo");
		imageHashList.add("lqH5KOC");
		imageHashList.add("UxZ1Xld");
		imageHashList.add("ZUmMNVT");
		imageHashList.add("zl8CD9E");
		imageHashList.add("92Byza1");
		imageHashList.add("1UB5mvf");
		imageHashList.add("RNmEySv");
		imageHashList.add("JpahxBh");
		imageHashList.add("OyPMaey");
		imageHashList.add("c18Gf6r");
		imageHashList.add("mCKaUAB");
		imageHashList.add("vzOSSQz");
		imageHashList.add("JOnSzEc");
		imageHashList.add("ks3VYje");
		imageHashList.add("Y6wb26m");
		imageHashList.add("1rtG8Ub");
		imageHashList.add("gZ0iUYJ");
		imageHashList.add("nHNmRjC");
		imageHashList.add("ZLDgU9V");
		imageHashList.add("ATFN4C6");
		imageHashList.add("6pK9YVm");
		imageHashList.add("12i9f5i");
		imageHashList.add("zuiPJ1E");
		imageHashList.add("gYgkMhM");
		imageHashList.add("FbBZBLP");
		imageHashList.add("KwPvfDf");
		imageHashList.add("HhvqSRm");
		imageHashList.add("YA3p17D");
		imageHashList.add("3IY2XIF");
		imageHashList.add("pcuNU1C");
		imageHashList.add("k76PBQw");
		imageHashList.add("RNS0vDG");
		imageHashList.add("zEpDRyW");
		imageHashList.add("tyBzFLn");
	}

	public static void main(String[] args) {
		OCRSpaceDemo d = new OCRSpaceDemo();
		FileUtilCustom io = new FileUtilCustom();

		for (String imageHash : imageHashList) {
			String url = d.buildUrl(imageHash);
			String result = d.sendGet(url);
			System.out.println(result);
			NameCard nc = d.handleResult(result);
			io.byteToFileAppendAtEnd((nc.toString() + "\n").getBytes(StandardCharsets.UTF_8), d.resultFilePath);
		}

	}

	public String buildUrl(String imageHash) {
		String url = baseUrl + "?apikey=" + apiKey + "&language=" + language + "&url=" + imageBaseUrl + imageHash
				+ ".jpg";
		return url;
	}

	public String sendGet(String url) {
		HttpUtil h = new HttpUtil();
		String result = null;
		try {
			System.out.println(url);
			result = h.sendGet(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public NameCard handleResult(String result) {
		JSONObject json = null;
		NameCard c = null;
		try {
			json = JSONObject.fromObject(result);
			JSONArray ja = json.getJSONArray("ParsedResults");
			String parsedText = ja.getJSONObject(0).getString("ParsedText");
			c = new NameCard();
			List<String> attrList = Arrays.asList(parsedText.split("\\r\\n"));
			String name = null;
			for (String attr : attrList) {
				if (NumericUtilCustom.matchMobile(attr)) {
					c.setPhone(attr);
				} else if (StringUtils.isNotBlank(attr)) {
					name += attr;
				}
				c.setName(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

}