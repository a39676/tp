package test;

import java.io.IOException;
import java.net.URISyntaxException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Tmp33 {

	public static void main(String[] args) throws IOException, URISyntaxException {
		String str = "[{\"examQuesId\":\"c890f54d-9ec6-4877-91d2-ec6032a6c7cd\",\"quesType\":2,\"answers\":null,\"keywords\":null,\"fillinAnswers\":null,\"fillinCorrectedInfo\":null,\"judgeAnswer\":1,\"explainText\":\"\",\"answerPlay\":null,\"explainPlay\":null,\"corrected\":1,\"allowViewSingleQuesResult\":1,\"allowViewSingleQuesAnswer\":1,\"allowViewSingleQuesExplain\":1}]";
		JSONArray ja = JSONArray.fromObject(str);
		System.out.println(ja);
		System.out.println(str.substring(1, str.length() - 1));
		JSONObject json = JSONObject.fromObject(str);
		System.out.println(json);
	}
}
