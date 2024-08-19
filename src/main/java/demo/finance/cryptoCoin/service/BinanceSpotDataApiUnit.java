package demo.finance.cryptoCoin.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import finance.common.pojo.type.IntervalType;
import finance.cryptoCoin.pojo.bo.CryptoCoinPriceCommonDataBO;
import net.sf.json.JSONArray;
import test.aiArt.CommonService;
import toolPack.httpHandel.HttpUtil;

public class BinanceSpotDataApiUnit extends CommonService {

	private static final int BINANCE_DATA_FIELD_COUNT = 12;

	public List<CryptoCoinPriceCommonDataBO> getKLineDailyDataFromApi(String symbol, Long startTime, Long endTime) {
		if (startTime == null || endTime == null) {
			Date now = new Date();
			long defaultTimeGap = 360;
			endTime = now.getTime();
			startTime = endTime - defaultTimeGap;
		}

		String response = getKLineDataFromApi(symbol, IntervalType.DAY_1, startTime, endTime);
		if (response == null) {
			return new ArrayList<>();
		}
		return buildDataListDTO(response, symbol);
	}

	public List<CryptoCoinPriceCommonDataBO> getKLineHourlyDataFromApi(String symbol) {
		String response = getKLineDataFromApi(symbol, IntervalType.HOUR_1, null);
		if (response == null) {
			return new ArrayList<>();
		}
		return buildDataListDTO(response, symbol);
	}

	public List<CryptoCoinPriceCommonDataBO> getKLine1MinuteDataFromApi(String symbol) {
		String response = getKLineDataFromApi(symbol, IntervalType.MINUTE_1, null);
		if (response == null) {
			return new ArrayList<>();
		}
		return buildDataListDTO(response, symbol);
	}

	private String getKLineDataFromApi(String symbol, IntervalType intervalType, long startTime, long endTime) {
		String url = "https://api.binance.com/api/v3/klines?symbol=%s&interval=%s&startTime=%d&endTime=%d";
		url = String.format(url, symbol.toUpperCase(), intervalType.getName(), startTime, endTime);

		return getKLineDataFromApiByUrl(url);
	}

	private String getKLineDataFromApi(String symbol, IntervalType intervalType, Integer limit) {
		if (limit == null) {
			limit = 360;
		}
		String url = "https://api.binance.com/api/v3/klines?symbol=%s&interval=%s&limit=%d";
		url = String.format(url, symbol.toUpperCase(), intervalType.getName(), limit);

		return getKLineDataFromApiByUrl(url);
	}

	private String getKLineDataFromApiByUrl(String url) {
		HttpUtil h = new HttpUtil();
		String response = null;
		try {
			response = h.sendGet(url);
			return response;
		} catch (IOException e) {
			log.error("Get hour data from binance IOExcepiton, url: " + url + ", error: " + e.getLocalizedMessage());
			return null;
		} catch (URISyntaxException e) {
			log.error("Get hour data from binance URISyntaxException, url: " + url + ", error: "
					+ e.getLocalizedMessage());
			return null;
		}
	}

	private List<CryptoCoinPriceCommonDataBO> buildDataListDTO(String responseStr, String symbol) {
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
			tmpBO.setStartPrice(new BigDecimal(tmpDataEle.getString(1)));
			tmpBO.setHighPrice(new BigDecimal(tmpDataEle.getString(2)));
			tmpBO.setLowPrice(new BigDecimal(tmpDataEle.getString(3)));
			tmpBO.setEndPrice(new BigDecimal(tmpDataEle.getString(4)));
			tmpBO.setVolume(new BigDecimal(tmpDataEle.getString(5)));
			try {
				tmpBO.setStartTime(localDateTimeHandler.dateToLocalDateTime(new Date(tmpDataEle.getLong(0))));
				tmpBO.setEndTime(localDateTimeHandler.dateToLocalDateTime(new Date(tmpDataEle.getLong(6))));
			} catch (Exception e) {
			}
			tmpBO.setVolume(new BigDecimal(tmpDataEle.getString(7)));
			tmpBO.setSymbol(symbol);
//			tmpBO.setOpenTime(localDateTimeHandler.dateToLocalDateTime(new Date(tmpDataEle.getLong(0))));
//			tmpBO.setOpen(new BigDecimal(tmpDataEle.getString(1)));
//			tmpBO.setHigh(new BigDecimal(tmpDataEle.getString(2)));
//			tmpBO.setLow(new BigDecimal(tmpDataEle.getString(3)));
//			tmpBO.setClose(new BigDecimal(tmpDataEle.getString(4)));
//			tmpBO.setVolume(new BigDecimal(tmpDataEle.getString(5)));
//			tmpBO.setCloseTime(localDateTimeHandler.dateToLocalDateTime(new Date(tmpDataEle.getLong(6))));
//			tmpBO.setBaseAssetVolume(new BigDecimal(tmpDataEle.getString(7)));
//			tmpBO.setNumberOfTrades(new BigDecimal(tmpDataEle.getString(8)));
//			tmpBO.setTakerBuyVolume(new BigDecimal(tmpDataEle.getString(9)));
//			tmpBO.setTakerBuyBaseAssetVolume(new BigDecimal(tmpDataEle.getString(10)));
//			tmpBO.setUnknown(tmpDataEle.getString(11));
			list.add(tmpBO);
		}

		return list;
	}

	// 2024-05-02 停止使用, 作为参考文档保留
//	private List<CryptoCoinPriceCommonDataBO> binanceDataConvertToCommonData(List<BinanceKLineBO> binanceDataList,
//			String symbol, IntervalType intervalType) {
//		List<CryptoCoinPriceCommonDataBO> list = new ArrayList<>();
//		for (BinanceKLineBO binanceData : binanceDataList) {
//			list.add(binanceDataConvertToCommonData__(binanceData, symbol, intervalType));
//		}
//		return list;
//	}
//	private CryptoCoinPriceCommonDataBO binanceDataConvertToCommonData__(BinanceKLineBO binanceData, String symbol,
//			IntervalType intervalType) {
//		// 未处理 coinType, currencyType
//		CryptoCoinPriceCommonDataBO bo = new CryptoCoinPriceCommonDataBO();
//		bo.setStartTime(binanceData.getOpenTime());
//		bo.setEndTime(binanceData.getCloseTime());
//		bo.setStartPrice(binanceData.getOpen());
//		bo.setEndPrice(binanceData.getClose());
//		bo.setHighPrice(binanceData.getHigh());
//		bo.setLowPrice(binanceData.getLow());
//		bo.setVolume(binanceData.getVolume());
//		bo.setInterval(intervalType);
//		bo.setSymbol(symbol);
//		return bo;
//	}
}
