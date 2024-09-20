package demo.finance.cryptoCoin.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import finance.cryptoCoin.binance.future.um.pojo.dto.CryptoCoinBinanceFutureUmPriceCacheSubBO;
import finance.cryptoCoin.binance.future.um.pojo.result.CryptoCoinBinanceFutureUmPriceResult;
import test.aiArt.CommonService;

public class BinanceDataCommonService extends CommonService {

	protected static final int SCALE_FOR_PRICE_DISPLAY = 8;
	protected static final int SCALE_FOR_PRICE_CALCULATE = 12;
	protected static final int SCALE_FOR_RATE_DISPLAY = 2;
	protected static final int SCALE_FOR_RATE_CALCULATE = 4;

	private static final String FILE_SAVE_PATH = "/tmp/cryptoCoin";

	protected static List<String> symbols = new ArrayList<>();
	protected static List<String> kSymbols = new ArrayList<>();

	protected static String getFileSavePath() {
		File z2 = new File("D:/z2");
		if (z2.exists()) {
			return "D:" + FILE_SAVE_PATH;
		} else {
			return System.getProperty("user.home") + FILE_SAVE_PATH;
		}
	}

	protected static void refreshSymbolListFromAPI() {
		CryptoCoinBinanceFutureUmPriceResult lastPriceResult = BinanceFutureUmDataApiUnit.getLastPrice();
		Map<String, CryptoCoinBinanceFutureUmPriceCacheSubBO> priceMap = lastPriceResult.getPriceMap();
		for (Entry<String, CryptoCoinBinanceFutureUmPriceCacheSubBO> price : priceMap.entrySet()) {
			if (StringUtils.isNotBlank(price.getKey())) {
				symbols.add(price.getKey());
			}
		}
	}

	public static void setProxy() {
		String proxyHost = "127.0.0.1";
		String proxyPort = "10809";
		System.setProperty("http.proxyHost", proxyHost);
		System.setProperty("http.proxyPort", proxyPort);
		System.setProperty("https.proxyHost", proxyHost);
		System.setProperty("https.proxyPort", proxyPort);
	}

}
