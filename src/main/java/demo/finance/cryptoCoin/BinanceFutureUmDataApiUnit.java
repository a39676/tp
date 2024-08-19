package demo.finance.cryptoCoin;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import finance.common.pojo.type.IntervalType;
import finance.cryptoCoin.binance.future.um.pojo.dto.CryptoCoinBinanceFutureUmGetLongShortPositionRatioDTO;
import finance.cryptoCoin.binance.future.um.pojo.dto.CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO;
import finance.cryptoCoin.binance.future.um.pojo.dto.CryptoCoinBinanceFutureUmPriceCacheSubBO;
import finance.cryptoCoin.binance.future.um.pojo.result.CryptoCoinBinanceFutureUmPriceResult;
import finance.cryptoCoin.pojo.bo.CryptoCoinPriceCommonDataBO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import test.aiArt.CommonService;
import toolPack.dateTimeHandle.LocalDateTimeHandler;
import toolPack.httpHandel.HttpUtil;

public class BinanceFutureUmDataApiUnit extends CommonService {

	private static final String BASE_URL = "https://fapi.binance.com";
	private static final int BINANCE_DATA_FIELD_COUNT = 12;

	public CryptoCoinBinanceFutureUmPriceResult getLastPrice() {
		CryptoCoinBinanceFutureUmPriceResult r = new CryptoCoinBinanceFutureUmPriceResult();

		String response = null;
		String url = "https://fapi.binance.com/fapi/v2/ticker/price";
		HttpUtil h = new HttpUtil();
		try {
			response = h.sendGet(url);
			JSONArray ja = JSONArray.fromObject(response);
			for (int i = 0; i < ja.size(); i++) {
				JSONObject json = ja.getJSONObject(i);
				CryptoCoinBinanceFutureUmPriceCacheSubBO bo = new CryptoCoinBinanceFutureUmPriceCacheSubBO();
				bo.setSymbol(json.getString("symbol"));
				String priceStr = json.getString("price");
				bo.setPrice(new BigDecimal(priceStr));
				bo.setTime(localDateTimeHandler.dateToLocalDateTime(new Date(json.getLong("time"))));
				r.getPriceMap().put(bo.getSymbol(), bo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return r;
		}

		r.setIsSuccess();
		return r;
	}
	
	public List<CryptoCoinPriceCommonDataBO> getKLineDataFromApi(String symbol, IntervalType intervalType,
			Integer limit) {

		if (limit == null) {
			limit = 30;
		}
		String response = getKLineDataFromApiWithLimit(symbol, intervalType, limit);
		if (response == null) {
			return new ArrayList<>();
		}
		return buildDataListDTO(response, symbol, intervalType);
	}

	public List<CryptoCoinPriceCommonDataBO> getKLineDataFromApi(String symbol, IntervalType intervalType,
			Long startTime, Long endTime) {
		if (startTime == null || endTime == null) {
			return new ArrayList<>();
		}
		String response = getKLineDataFromApiWithTimeRange(symbol, intervalType, startTime, endTime);
		if (response == null) {
			return new ArrayList<>();
		}
		return buildDataListDTO(response, symbol, intervalType);
	}

	private List<CryptoCoinPriceCommonDataBO> buildDataListDTO(String responseStr, String symbol,
			IntervalType intervalType) {
		LocalDateTimeHandler localDateTimeHandler = new LocalDateTimeHandler();
		List<CryptoCoinPriceCommonDataBO> list = new ArrayList<>();
		if (StringUtils.isBlank(responseStr)) {
			return list;
		}
		JSONArray mainJsonArray = null;
		try {
			mainJsonArray = JSONArray.fromObject(responseStr);
		} catch (Exception e) {
			return list;
		}
		JSONArray tmpDataEle = null;
		CryptoCoinPriceCommonDataBO tmpBO = null;
		for (int i = 0; i < mainJsonArray.size(); i++) {
			tmpDataEle = mainJsonArray.getJSONArray(i);
			if (tmpDataEle.size() != BINANCE_DATA_FIELD_COUNT) {
				continue;
			}
			tmpBO = new CryptoCoinPriceCommonDataBO();
			tmpBO.setStartTime(localDateTimeHandler.dateToLocalDateTime(new Date(tmpDataEle.getLong(0))));
			tmpBO.setStartPrice(new BigDecimal(tmpDataEle.getString(1)));
			tmpBO.setHighPrice(new BigDecimal(tmpDataEle.getString(2)));
			tmpBO.setLowPrice(new BigDecimal(tmpDataEle.getString(3)));
			tmpBO.setEndPrice(new BigDecimal(tmpDataEle.getString(4)));
			tmpBO.setVolume(new BigDecimal(tmpDataEle.getString(5)));
			tmpBO.setEndTime(localDateTimeHandler.dateToLocalDateTime(new Date(tmpDataEle.getLong(6))));
			tmpBO.setInterval(intervalType);
			tmpBO.setSymbol(symbol);
			list.add(tmpBO);
		}

		return list;
	}

	private String getKLineDataFromApiWithLimit(String symbol, IntervalType intervalType, Integer limit) {
		if (limit == null) {
			limit = 30;
		}
		String url = BASE_URL + "/fapi/v1/klines?symbol=%s&interval=%s&limit=%d";
		url = String.format(url, symbol.toUpperCase(), intervalType.getName(), limit);

		return getKLineDataFromApiByUrl(url);
	}

	private String getKLineDataFromApiWithTimeRange(String symbol, IntervalType intervalType, Long startTime,
			Long endTime) {
		if (startTime == null || endTime == null) {
			return null;
		}
		String url = BASE_URL + "/fapi/v1/klines?symbol=%s&interval=%s&startTime=%d&endTime=%d";
		url = String.format(url, symbol.toUpperCase(), intervalType.getName(), startTime, endTime);

		return getKLineDataFromApiByUrl(url);
	}

	private String getKLineDataFromApiByUrl(String url) {
		HttpUtil h = new HttpUtil();
		String response = null;
		try {
			response = h.sendGet(url);
			return response;
		} catch (IOException e) {
			e.getLocalizedMessage();
			return null;
		} catch (URISyntaxException e) {
			e.getLocalizedMessage();
			return null;
		}
	}

	public List<CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO> topLongShortPositionRatio(
			CryptoCoinBinanceFutureUmGetLongShortPositionRatioDTO dto) {
		List<CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO> result = new ArrayList<>();
		String url = BASE_URL + "/futures/data/topLongShortPositionRatio?symbol=%s&period=%s";
		url = String.format(url, dto.getSymbol(), dto.getIntervalType().getName());
		if (dto.getLimit() != null) {
			url += "&limit=" + dto.getLimit();
		}

		HttpUtil h = new HttpUtil();

		try {
			String response = h.sendGet(url);
			JSONArray jsonArray = JSONArray.fromObject(response);
			CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO detail = null;
			for (int i = 0; i < jsonArray.size(); i++) {
				detail = buildObjFromJsonCustomization(jsonArray.getString(i),
						CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO.class);
				result.add(detail);
			}
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO> globalLongShortAccountRatio(
			CryptoCoinBinanceFutureUmGetLongShortPositionRatioDTO dto) {
		List<CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO> result = new ArrayList<>();
		String url = BASE_URL + "/futures/data/globalLongShortAccountRatio?symbol=%s&period=%s";
		url = String.format(url, dto.getSymbol(), dto.getIntervalType().getName());
		if (dto.getLimit() != null) {
			url += "&limit=" + dto.getLimit();
		}

		HttpUtil h = new HttpUtil();

		try {
			String response = h.sendGet(url);
			JSONArray jsonArray = JSONArray.fromObject(response);
			CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO detail = null;
			for (int i = 0; i < jsonArray.size(); i++) {
				detail = buildObjFromJsonCustomization(jsonArray.getString(i),
						CryptoCoinBinanceFutureUmGetLongShortPositionRatioDetailDTO.class);
				result.add(detail);
			}
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

		return result;
	}
}
