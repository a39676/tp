package taobao.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import net.sf.json.JSONObject;
import taobao.pojo.dto.LocationDTO;
import toolPack.ioHandle.FileUtilCustom;

public class TaobaoOption {

	private static String secret;
	private static String hostname;
	private static String msgUrl;
	public static LocationDTO copySkuBtn;
	public static LocationDTO allSkuCheckBox;
	public static LocationDTO exportBtn;
	public static LocationDTO exportAsTextBtn;
	public static LocationDTO blankPlace;
	public static LocationDTO bottomRecomendBar;

	public static final String FILE_NAME_PREFIX = "1688批量导出SKU_";
	// 1. 获取系统的 User Home 路径
	private static String userHome = System.getProperty("user.home");

	public static final Path DOWNLOAD_DIR = Paths.get(userHome, "tmp");
	public static final Path NEW_INFO = Paths.get(userHome, "optionFile", "tp", "taobao", "newSkuInfo");
	public static final Path OLD_INFO = Paths.get(userHome, "optionFile", "tp", "taobao", "oldSkuInfo");
	public static final Path UPDATE_INFO = Paths.get(userHome, "optionFile", "tp", "taobao", "updateInfo");

	public static final Path ID_LIST = Paths.get(userHome, "optionFile", "tp", "taobao", "sourceIdList.txt");

	public static final Path OPTION_FILE = Paths.get(userHome, "optionFile", "tp", "taobao", "option.json");

	static {
		JSONObject json = JSONObject.fromObject(new FileUtilCustom().getStringFromFile(OPTION_FILE.toString()));
		secret = json.getString("secret");
		hostname = json.getString("hostname");
		msgUrl = json.getString("msgUrl");
		copySkuBtn = new LocationDTO();
		allSkuCheckBox = new LocationDTO();
		exportBtn = new LocationDTO();
		exportAsTextBtn = new LocationDTO();
		blankPlace = new LocationDTO();
		JSONObject locations = json.getJSONObject("locations");
		JSONObject tmpLocation = locations.getJSONObject("copySkuBtn");
		copySkuBtn.setX(tmpLocation.getInt("x"));
		copySkuBtn.setY(tmpLocation.getInt("y"));
		tmpLocation = locations.getJSONObject("allSkuCheckBox");
		allSkuCheckBox.setX(tmpLocation.getInt("x"));
		allSkuCheckBox.setY(tmpLocation.getInt("y"));
		tmpLocation = locations.getJSONObject("exportBtn");
		exportBtn.setX(tmpLocation.getInt("x"));
		exportBtn.setY(tmpLocation.getInt("y"));
		tmpLocation = locations.getJSONObject("exportAsTextBtn");
		exportAsTextBtn.setX(tmpLocation.getInt("x"));
		exportAsTextBtn.setY(tmpLocation.getInt("y"));
		tmpLocation = locations.getJSONObject("blankPlace");
		blankPlace.setX(tmpLocation.getInt("x"));
		blankPlace.setY(tmpLocation.getInt("y"));
		tmpLocation = locations.getJSONObject("bottomRecomendBar");
		bottomRecomendBar.setX(tmpLocation.getInt("x"));
		bottomRecomendBar.setY(tmpLocation.getInt("y"));
	}

	public static String getSecret() {
		return secret;
	}

	public static String getMsgUrl() {
		return msgUrl;
	}

	public static String getHostname() {
		return hostname;
	}

}
