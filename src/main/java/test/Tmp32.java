package test;

import java.io.IOException;
import java.util.List;

import demo.finance.cryptoCoin.BinanceDataApiUnit;
import finance.cryptoCoin.pojo.bo.CryptoCoinPriceCommonDataBO;

public class Tmp32 {

	public static void main(String[] args) throws IOException {
		String proxyHost = "127.0.0.1";
		String proxyPort = "10809";
		System.setProperty("http.proxyHost", proxyHost);
		System.setProperty("http.proxyPort", proxyPort);
		System.setProperty("https.proxyHost", proxyHost);
		System.setProperty("https.proxyPort", proxyPort);
		System.out.println("Had set proxy");

		BinanceDataApiUnit binanceDataApiUnit = new BinanceDataApiUnit();
		List<CryptoCoinPriceCommonDataBO> tmpDataList = binanceDataApiUnit.getKLine1MinuteDataFromApi("TON" + "USDT");
		System.out.println(tmpDataList);
	}
}
