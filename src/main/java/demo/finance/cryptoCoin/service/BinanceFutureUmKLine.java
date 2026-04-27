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
import finance.common.pojo.bo.FilterPriceResult;
import finance.common.pojo.type.IntervalType;
import finance.cryptoCoin.pojo.bo.CryptoCoinPriceCommonDataBO;
import net.sf.json.JSONArray;
import toolPack.ioHandle.FileUtilCustom;

public class BinanceFutureUmKLine extends BinanceDataCommonService {

	private static int lastDataSize = 30;
	private static IntervalType intervalType = IntervalType.DAY_1;
	private static boolean needRefresh = true;

	public static void main(String[] args) {
		setProxy();
		refreshSymbolListFromAPI();
		System.out.println("symbol list: " + symbols);
		if (needRefresh) {
			for (String symbol : symbols) {
				getKLineFromApiAndSave(symbol);
			}
		}
		checkAllGaps(lastDataSize);
	}

	public static void getKLineFromApiAndSave(String symbol) {
		System.out.println("Get K line from API, symbol: " + symbol);
		BinanceFutureUmDataApiUnit apiUnit = new BinanceFutureUmDataApiUnit();
		List<CryptoCoinPriceCommonDataBO> kLineData = apiUnit.getKLineDataFromApi(symbol, intervalType, lastDataSize);
		if (kLineData == null || kLineData.isEmpty()) {
			System.err.println("Get K line failed. symbol: " + symbol);
		}

		JSONArray jsonArray = JSONArray.fromObject(kLineData);

		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, localDateTimeAdapter).setPrettyPrinting()
				.create();
		String str = gson.toJson(jsonArray);

		FileUtilCustom fuc = new FileUtilCustom();
		String filePath = FILE_SAVE_PATH + "/kLine/" + symbol + ".json";
		fuc.byteToFile(str.getBytes(StandardCharsets.UTF_8), filePath);
		System.out.println("Get K line from API DONE, symbol: " + symbol);
	}

	public static List<CryptoCoinPriceCommonDataBO> getKLineFromLocal(String symbol) {
		FileUtilCustom fuc = new FileUtilCustom();
		String filePath = FILE_SAVE_PATH + "/kLine/" + symbol + ".json";
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

	public static CryptoCoinPriceRatioDTO checkGap(String symbol, Integer dayGapMinSize) {
		List<CryptoCoinPriceCommonDataBO> dataList = getKLineFromLocal(symbol);
		if (dataList == null || dataList.isEmpty()) {
			System.err.println("Symbol: " + symbol + ", data list empty");
			return null;
		}
		if (dataList.size() < dayGapMinSize) {
			System.err.println("Symbol: " + symbol + ", data list too short for: " + dayGapMinSize);
			return null;
		}
		FilterPriceResult filterResult = kLineToolUnit
				.filterData(dataList.subList(dataList.size() - lastDataSize, dataList.size()));
		BigDecimal gapRatio = filterResult.getMaxPrice().divide(filterResult.getMinPrice(), SCALE_FOR_RATE_CALCULATE,
				RoundingMode.HALF_UP);
//		BigDecimal gapRatio = filterResult.getEndPrice().divide(filterResult.getMinPrice(), SCALE_FOR_RATE_CALCULATE,
//				RoundingMode.HALF_UP);
		CryptoCoinPriceRatioDTO dto = new CryptoCoinPriceRatioDTO();
		dto.setSymbol(symbol);
		dto.setRatio(gapRatio.doubleValue());
		System.out.println("Symbol: " + symbol + ", ratio: " + gapRatio);
		return dto;
	}

	/** 查最大升幅 */
	public static void checkAllGaps(Integer dayGapMinSize) {
		List<CryptoCoinPriceRatioDTO> list = new ArrayList<>();
		CryptoCoinPriceRatioDTO dto = null;
		for (String symbol : symbols) {
			dto = checkGap(symbol, dayGapMinSize);
			if (dto != null) {
				list.add(dto);
			}
		}
		Collections.sort(list);
		System.out.println(list);
	}

}
