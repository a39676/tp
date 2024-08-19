package demo.finance.cryptoCoin.service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

import finance.common.pojo.type.IntervalType;
import finance.cryptoCoin.binance.future.um.pojo.dto.CryptoCoinBinanceFutureUmGetLongShortPositionRatioDTO;
import finance.cryptoCoin.binance.future.um.pojo.dto.CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO;
import net.sf.json.JSONArray;
import toolPack.ioHandle.FileUtilCustom;

public class BinanceFutureUmLongShortRatio {

	private static final String FILE_SAVE_PATH = "/tmp/cryptoCoin";

	public static void globalLongShortAccountRatio(String symbol) {
		BinanceFutureUmDataApiUnit apiUnit = new BinanceFutureUmDataApiUnit();
		CryptoCoinBinanceFutureUmGetLongShortPositionRatioDTO dto = new CryptoCoinBinanceFutureUmGetLongShortPositionRatioDTO();
		dto.setSymbol(symbol);
		dto.setIntervalType(IntervalType.DAY_1);
		dto.setLimit(30);
		List<CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO> dataList = apiUnit
				.globalLongShortAccountRatio(dto);
		if (dataList == null || dataList.isEmpty()) {
			System.err.println("Get long short account ratio failed. dto: " + dto.toString());
		}

		JSONArray jsonArray = JSONArray.fromObject(dataList);

		FileUtilCustom fuc = new FileUtilCustom();
		String filePath = getFileSavePath() + "/longShortAccountRatio/" + symbol + ".json";
		fuc.byteToFile(jsonArray.toString().getBytes(StandardCharsets.UTF_8), filePath);
	}

	public static void topLongShortPositionRatio(String symbol) {
		BinanceFutureUmDataApiUnit apiUnit = new BinanceFutureUmDataApiUnit();
		CryptoCoinBinanceFutureUmGetLongShortPositionRatioDTO dto = new CryptoCoinBinanceFutureUmGetLongShortPositionRatioDTO();
		dto.setSymbol(symbol);
		dto.setIntervalType(IntervalType.DAY_1);
		dto.setLimit(30);
		List<CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO> dataList = apiUnit
				.topLongShortPositionRatio(dto);
		if (dataList == null || dataList.isEmpty()) {
			System.err.println("Get top long short position ratio failed. dto: " + dto.toString());
		}

		JSONArray jsonArray = JSONArray.fromObject(dataList);

		FileUtilCustom fuc = new FileUtilCustom();
		String filePath = getFileSavePath() + "/topLongShortPositionRatio/" + symbol + ".json";
		fuc.byteToFile(jsonArray.toString().getBytes(StandardCharsets.UTF_8), filePath);
	}

	private static String getFileSavePath() {
		File z2 = new File("D:/z2");
		if (z2.exists()) {
			return "D:" + FILE_SAVE_PATH;
		} else {
			return System.getProperty("user.home") + FILE_SAVE_PATH;
		}
	}

}
