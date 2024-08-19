package demo.finance.cryptoCoin.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import finance.common.pojo.type.IntervalType;
import finance.cryptoCoin.binance.future.um.pojo.dto.CryptoCoinBinanceFutureUmGetLongShortPositionRatioDTO;
import finance.cryptoCoin.binance.future.um.pojo.dto.CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO;
import net.sf.json.JSONArray;
import toolPack.ioHandle.FileUtilCustom;

public class BinanceFutureUmLongShortRatio extends BinanceDataCommonService {

	public static void globalLongShortAccountRatioFromApiAndSave(String symbol) {
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

	public static List<CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO> globalLongShortAccountRatioFromLocal(
			String symbol) {
		FileUtilCustom fuc = new FileUtilCustom();
		String filePath = getFileSavePath() + "/longShortAccountRatio/" + symbol + ".json";
		String content = fuc.getStringFromFile(filePath);
		JSONArray jsonArray = JSONArray.fromObject(content);

		List<CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO> dataList = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO dto = buildObjFromJsonCustomization(
					jsonArray.getString(i), CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO.class);
			dataList.add(dto);
		}
		return dataList;
	}

	public static void topLongShortPositionRatioFromApiAndSave(String symbol) {
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

	public static List<CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO> topLongShortPositionRatioFromLocal(
			String symbol) {
		FileUtilCustom fuc = new FileUtilCustom();
		String filePath = getFileSavePath() + "/topLongShortPositionRatio/" + symbol + ".json";
		String content = fuc.getStringFromFile(filePath);
		JSONArray jsonArray = JSONArray.fromObject(content);

		List<CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO> dataList = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO dto = buildObjFromJsonCustomization(
					jsonArray.getString(i), CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO.class);
			dataList.add(dto);
		}
		return dataList;
	}

}
