package demo.image.ocr.baidu;

import demo.image.ocr.NameCard;
import ioHandle.FileUtilCustom;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TmpResultHandle {
	
	public static void main(String[] args) {
		String filePath = OCRDemo.resultFilePath;
		
		FileUtilCustom io = new FileUtilCustom();
		String str = io.getStringFromFile(filePath);
		
		JSONArray ja = JSONArray.fromObject(str);
		System.out.println(ja);
		
		for(int i = 0; i < ja.size(); i++) {
			if(ja.get(0) instanceof JSONObject) {
				buildNameCard(ja.getJSONObject(i));
			}
		}
	}

	public static void buildNameCard(JSONObject j) {
		int wordsResultNum = j.getInt("words_result_num");
		JSONArray wordResult = j.getJSONArray("words_result");
		JSONObject nameJ = wordResult.getJSONObject(0);
		JSONObject jobJ = null;
		JSONObject mobileJ = null;
		if(wordsResultNum == 2) {
			mobileJ = wordResult.getJSONObject(wordsResultNum - 1);
		} else {
			jobJ = wordResult.getJSONObject(wordsResultNum - 2);
			mobileJ = wordResult.getJSONObject(wordsResultNum - 1);
		}
		
		NameCard n = new NameCard();
		n.setName(nameJ.getString("words"));
		if(jobJ != null) {
			n.setJob(jobJ.getString("words"));
		}
		n.setPhone(mobileJ.getString("words").replaceAll("[^\\d]", ""));
		
		System.out.println(n);
	}
}
