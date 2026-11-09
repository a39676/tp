package taobao.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import net.sf.json.JSONObject;
import taobao.pojo.dto.LocationDTO;
import toolPack.ioHandle.FileUtilCustom;

public class TaobaoOption {

	private static String hostname;
	private static String sellingSourceProductUrl;
	public static int start = 0;
	public static int end = 0;
	public static LocationDTO copySkuBtn = new LocationDTO();
	public static LocationDTO allSkuCheckBox = new LocationDTO();
	public static LocationDTO exportBtn = new LocationDTO();
	public static LocationDTO exportAsTextBtn = new LocationDTO();
	public static LocationDTO blankPlace = new LocationDTO();
	public static LocationDTO bottomRecomendBar = new LocationDTO();
	public static LocationDTO closeTagBtn = new LocationDTO();

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
		start = json.getInt("start");
		end = json.getInt("end");
		hostname = json.getString("hostname");
		sellingSourceProductUrl = json.getString("sellingSourceProductUrl");
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
		tmpLocation = locations.getJSONObject("closeTagBtn");
		closeTagBtn.setX(tmpLocation.getInt("x"));
		closeTagBtn.setY(tmpLocation.getInt("y"));
	}


	public static String getSellingSourceProductUrl() {
		return sellingSourceProductUrl;
	}

	public static String getHostname() {
		return hostname;
	}

}
