package demo.education.chinese;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import toolPack.ioHandle.FileUtilCustom;

public class ChineseCharacterCollect {
	
	private static final String SOURCE_FILE_PATH = "D:\\myMavenPacks\\tp\\src\\main\\java\\demo\\education\\chinese\\ChineseCharacterCollect.json";
	private static final List<String> BASE_GROUP_NAME_LIST = Arrays.asList("g1_1","g1_2","g2_1","g2_2","g3_1","g3_2","g4_1","g4_2","g5_1","g5_2","g6_1","g6_2");
	private static final String GROUP_OTHER = "other";
	private static final String GROUP_UNABLE = "unable";
	
	public static void main(String[] args) {
		printRandomCharacter(180);
	}
	
	private static void printRandomCharacter(int limit) {
		List<String> summary = getAllCharacter();
		
		Collections.shuffle(summary);
		for (int i = 0; i < summary.size() && i < limit; i++) {
			System.out.print(summary.get(i));
		}
	}
	
	private static List<String> getAllCharacter() {
		List<String> groupNameList = new ArrayList<>();
		groupNameList.addAll(BASE_GROUP_NAME_LIST);
		groupNameList.add(GROUP_OTHER);
		groupNameList.add(GROUP_UNABLE);
		return getCharacterByGroupNames(groupNameList);
	}
	
	private static List<String> getCharacterByGroupNames(List<String> groupNames) {
		FileUtilCustom io = new FileUtilCustom();
		String str = io.getStringFromFile(SOURCE_FILE_PATH);
		JSONObject json = JSONObject.fromObject(str);

		List<String> summary = new ArrayList<>();
		JSONArray jsonArray = null;
		
		for(String groupName : groupNames) {
			jsonArray = json.getJSONArray(groupName);
			for (Object s : jsonArray.toArray()) {
				summary.add(String.valueOf(s));
			}
		}
		
		return summary;
	}

}
