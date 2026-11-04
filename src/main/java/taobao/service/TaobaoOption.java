package taobao.service;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TaobaoOption {

	public static final String FILE_NAME_PREFIX = "1688批量导出SKU_";
	// 1. 获取系统的 User Home 路径
	private static String userHome = System.getProperty("user.home");

	public static final Path DOWNLOAD_DIR = Paths.get(userHome, "tmp");
	public static final Path NEW_INFO = Paths.get(userHome, "optionFile", "tp", "taobao", "newSkuInfo");
	public static final Path OLD_INFO = Paths.get(userHome, "optionFile", "tp", "taobao", "oldSkuInfo");

	public static final Path ID_LIST = Paths.get(userHome, "optionFile", "tp", "taobao", "sourceIdList.txt");
}
