package demo.finance.cryptoCoin.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import finance.common.pojo.type.IntervalType;
import finance.cryptoCoin.pojo.bo.CryptoCoinPriceCommonDataBO;
import net.sf.json.JSONArray;
import toolPack.ioHandle.FileUtilCustom;

public class BinanceFutureUmKLine extends BinanceDataCommonService {

	public static void getKLineFromApiAndSave(String symbol) {
		BinanceFutureUmDataApiUnit apiUnit = new BinanceFutureUmDataApiUnit();
		List<CryptoCoinPriceCommonDataBO> kLineData = apiUnit.getKLineDataFromApi(symbol, IntervalType.DAY_1, 100);
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

}
