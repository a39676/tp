package education.chinese;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import toolPack.ioHandle.FileUtilCustom;

public class ChineseCharacterCollect {

	public static void main(String[] args) {
		FileUtilCustom io = new FileUtilCustom();
		String str = io.getStringFromFile(
				"D:\\myMavenPacks\\tp\\src\\main\\java\\education\\chinese\\ChineseCharacterCollect.json");
		JSONObject json = JSONObject.fromObject(str);

		List<String> result = new ArrayList<>();
		JSONArray jsonArray = null;
		
		jsonArray = json.getJSONArray("g1_1");
		for (Object s : jsonArray.toArray()) {
			result.add(String.valueOf(s));
		}

		jsonArray = json.getJSONArray("g1_2");
		for (Object s : jsonArray.toArray()) {
			result.add(String.valueOf(s));
		}
		
		jsonArray = json.getJSONArray("g2_1");
		for (Object s : jsonArray.toArray()) {
			result.add(String.valueOf(s));
		}
		
		jsonArray = json.getJSONArray("g2_2");
		for (Object s : jsonArray.toArray()) {
			result.add(String.valueOf(s));
		}

		jsonArray = json.getJSONArray("other");
		for (Object s : jsonArray.toArray()) {
			result.add(String.valueOf(s));
		}

		Collections.shuffle(result);
		for (int i = 0; i < result.size() && i < 50; i++) {
			System.out.print(result.get(i) + ",");
		}
	}

}
