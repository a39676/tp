package demo.finance.cryptoCoin.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import demo.finance.cryptoCoin.pojo.bo.CryptoCoinPriceRatioDTO;
import finance.common.pojo.type.IntervalType;
import finance.cryptoCoin.pojo.bo.CryptoCoinPriceCommonDataBO;
import net.sf.json.JSONArray;
import toolPack.ioHandle.FileUtilCustom;

public class BinanceFutureUmKLine extends BinanceDataCommonService {

	public static void main(String[] args) {
		setProxy();
		refreshSymbolListFromAPI();
//		for (String symbol : symbols) {
//			getKLineFromApiAndSave(symbol);
//		}
		checkAllGaps(30);
	}

	public static void getKLineFromApiAndSave(String symbol) {
		System.out.println("Get K line from API, symbol: " + symbol);
		BinanceFutureUmDataApiUnit apiUnit = new BinanceFutureUmDataApiUnit();
		List<CryptoCoinPriceCommonDataBO> kLineData = apiUnit.getKLineDataFromApi(symbol, IntervalType.DAY_1, 499);
		if (kLineData == null || kLineData.isEmpty()) {
			System.err.println("Get K line failed. symbol: " + symbol);
		}

		JSONArray jsonArray = JSONArray.fromObject(kLineData);

		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, localDateTimeAdapter).setPrettyPrinting()
				.create();
		String str = gson.toJson(jsonArray);

		FileUtilCustom fuc = new FileUtilCustom();
		String filePath = getFileSavePath() + "/kLine/" + symbol + ".json";
		fuc.byteToFile(str.getBytes(StandardCharsets.UTF_8), filePath);
		System.out.println("Get K line from API DONE, symbol: " + symbol);
	}

	public static List<CryptoCoinPriceCommonDataBO> getKLineFromLocal(String symbol) {
		FileUtilCustom fuc = new FileUtilCustom();
		String filePath = getFileSavePath() + "/kLine/" + symbol + ".json";
		String content = fuc.getStringFromFile(filePath);

		List<CryptoCoinPriceCommonDataBO> kLineData = new ArrayList<>();
		CryptoCoinPriceCommonDataBO bo = null;
		JSONArray jsonArray = JSONArray.fromObject(content);
		for (int i = 0; i < jsonArray.size(); i++) {
			bo = buildObjFromJsonCustomization(jsonArray.getString(i), CryptoCoinPriceCommonDataBO.class);
			kLineData.add(bo);
		}

		return kLineData;
	}

	public static CryptoCoinPriceRatioDTO checkGap(String symbol, Integer dayGap) {
		List<CryptoCoinPriceCommonDataBO> dataList = getKLineFromLocal(symbol);
		if (dataList == null || dataList.isEmpty()) {
			System.err.println("Symbol: " + symbol + ", data list empty");
			return null;
		}
		if (dataList.size() < dayGap + 1) {
			System.err.println("Symbol: " + symbol + ", data list too short for: " + dayGap);
			return null;
		}
		CryptoCoinPriceCommonDataBO startData = dataList.get(dataList.size() - 1 - dayGap);
		CryptoCoinPriceCommonDataBO lastData = dataList.get(dataList.size() - 1);
		CryptoCoinPriceRatioDTO dto = new CryptoCoinPriceRatioDTO();
		BigDecimal gapRatio = lastData.getEndPrice().divide(startData.getStartPrice(), SCALE_FOR_RATE_CALCULATE,
				RoundingMode.HALF_UP);
		dto.setSymbol(symbol);
		dto.setRatio(gapRatio.doubleValue());
		System.out.println("Symbol: " + symbol + ", ratio: " + gapRatio);
		return dto;
	}

	public static void checkAllGaps(Integer dayGap) {
		List<CryptoCoinPriceRatioDTO> list = new ArrayList<>();
		CryptoCoinPriceRatioDTO dto = null;
		for (String symbol : symbols) {
			dto = checkGap(symbol, dayGap);
			if (dto != null) {
				list.add(dto);
			}
		}
		Collections.sort(list);
		System.out.println(list);
	}

}
