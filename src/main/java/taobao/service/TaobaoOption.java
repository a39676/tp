package taobao.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import net.sf.json.JSONObject;
import toolPack.ioHandle.FileUtilCustom;

public class TaobaoOption {

	private static String secret;
	private static String hostname;
	private static String msgUrl;

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
