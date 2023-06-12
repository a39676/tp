package test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;
import toolPack.ioHandle.FileUtilCustom;

public class Tmp24 {

	private static String np = "badArtist,badArtistAnime,badHands_v5,badHand_v4,badImage_v2,badPictureChill_v7_5,badPrompt,badPromptByNeg_v2,ngDeepNegative_v1_75t,veryBadImageNegative_v1_3,";
	private static Map<String, String> loraRenameMap = new HashMap<>();
	static {
		loraRenameMap.put("deerantlers-12", "deerantlers_v1_2");
		loraRenameMap.put("waitress_v10", "waitress");
		loraRenameMap.put("bettercuteasian_v10", "betterCuteAsian");
		loraRenameMap.put("sambaDanceDress_v10", "sambaDanceDress");
		loraRenameMap.put("miniatureV1", "miniature");
		loraRenameMap.put("urbansamuraiV03", "urbanSamurai_v0_3");
		loraRenameMap.put("sxzWowtextureV2", "sxzWowtexture_v2");
		loraRenameMap.put("twgreenfa", "flightAttendantTwGirls");
		loraRenameMap.put("styleNiuzai768", "styleNiuzai");
		loraRenameMap.put("breastinclassBetter_v141", "breastInClassBetterBodies_v1_41");
		loraRenameMap.put("chineseDollLikeness", "chinesedolllikeness2");
		loraRenameMap.put("safedv2", "safed_v2");
		loraRenameMap.put("japaneseDollLikeness_v15", "japaneseDollLikeness_v1_5");
		loraRenameMap.put("st20louis20epoch5", "stLouis");
		loraRenameMap.put("saikaV3", "saika_v3");
		loraRenameMap.put("blindboxV1Mix", "blindBoxMix");
		loraRenameMap.put("urbansamuraiV03", "urbanSamurai_v0_3");
		loraRenameMap.put("loraSoakingwetclothes2", "soakingwetclothes_v2");
		loraRenameMap.put("girlfriendmixV1V20", "girlFriendMix_v2");
		loraRenameMap.put("howlbgsv3", "howlbgs_v3");
		loraRenameMap.put("Miaozufushi-268", "miaoZuFuShi");
		loraRenameMap.put("realisticFishnet_v10", "realisticFishnet");
		loraRenameMap.put("bohoai", "boHoAi");
		loraRenameMap.put("zyd232_ChineseGirl_v4_0", "chineseGirlByZyd232_v4");
		loraRenameMap.put("sungAsianSexy_sungV10", "asianSexyBySung");
		loraRenameMap.put("skorpman15", "skorpman");
		loraRenameMap.put("off_shoulder_bandage_dress", "offShoulderBandageDress");
		loraRenameMap.put("yui_v3", "japaneseDollLikeness_v1_5");
		loraRenameMap.put("nijilorawolf", "nijiLoraWolf");
		loraRenameMap.put("miniatureV1", "miniature");
	}

	public static void main(String[] args) {
		String mainFolderPathStr = "D:/tmp/logs/aiGenResult";
		File folder = new File(mainFolderPathStr);

		File[] files = folder.listFiles();

		JSONObject json = null;
		for (File f : files) {
			String content = readFile(f.getAbsolutePath());
			json = JSONObject.fromObject(content);
			content = contentTrans(json);
			overWrite(f, content);
		}
	}

	public static String readFile(String filePath) {
		FileUtilCustom fu = new FileUtilCustom();
		String content = fu.getStringFromFile(filePath);
		return content;
	}

	public static String contentTrans(JSONObject json) {
		json.getJSONObject("parameter").put("negativePrompts", np);
		System.out.println(json);

		String prompts = json.getJSONObject("parameter").getString("prompts");
		for (Entry<String, String> entry : loraRenameMap.entrySet()) {
			prompts = prompts.replaceAll(entry.getKey(), entry.getValue());
		}
		json.getJSONObject("parameter").put("prompts", prompts);

		return json.toString();
	}

	public static boolean overWrite(File file, String content) {
		FileUtilCustom fu = new FileUtilCustom();
		fu.byteToFile(content.getBytes(StandardCharsets.UTF_8), file.getAbsolutePath());
		return true;
	}
}
