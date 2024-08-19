package demo.finance.cryptoCoin.service;

import java.io.File;

import test.aiArt.CommonService;

public class BinanceDataCommonService extends CommonService {

	private static final String FILE_SAVE_PATH = "/tmp/cryptoCoin";

	protected static String getFileSavePath() {
		File z2 = new File("D:/z2");
		if (z2.exists()) {
			return "D:" + FILE_SAVE_PATH;
		} else {
			return System.getProperty("user.home") + FILE_SAVE_PATH;
		}
	}

}
