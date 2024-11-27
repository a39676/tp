package demo.finance.cryptoCoin.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cloudinary.utils.StringUtils;

import demo.finance.cryptoCoin.pojo.type.CryptoCoinTagType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import toolPack.httpHandel.HttpUtil;
import toolPack.ioHandle.FileUtilCustom;

public class SymbolMetaData extends BinanceDataCommonService {

	private static Map<CryptoCoinTagType, List<String>> tagMap = new HashMap<>();
	private static String symbolMetaFilePathPrefix = System.getProperty("user.home") + "/programOutput/tp";

	static {
		List<String> l = null;
		String keywordsStr = null;

		l = new ArrayList<>();
		keywordsStr = "btc,比特";
		l.addAll(Arrays.asList(keywordsStr.split(",")));
		tagMap.put(CryptoCoinTagType.BTC, l);

		l = new ArrayList<>();
		keywordsStr = "Ethereum,eth,以太";
		l.addAll(Arrays.asList(keywordsStr.split(",")));
		tagMap.put(CryptoCoinTagType.ETH, l);

		l = new ArrayList<>();
		keywordsStr = "sol";
		l.addAll(Arrays.asList(keywordsStr.split(",")));
		tagMap.put(CryptoCoinTagType.SOL, l);

		l = new ArrayList<>();
		keywordsStr = "nft";
		l.addAll(Arrays.asList(keywordsStr.split(",")));
		tagMap.put(CryptoCoinTagType.NFT, l);

		l = new ArrayList<>();
		keywordsStr = "exchange,pay";
		l.addAll(Arrays.asList(keywordsStr.split(",")));
		tagMap.put(CryptoCoinTagType.EXCHANGE, l);

		l = new ArrayList<>();
		keywordsStr = "game,football,";
		l.addAll(Arrays.asList(keywordsStr.split(",")));
		tagMap.put(CryptoCoinTagType.FUN, l);

	}

	public static void main(String[] args) {
		SymbolMetaData t = new SymbolMetaData();
//		loadAllSymbolListFromAPI();
		setProxy();
		refreshSymbolListFromAPI();
//		saveAllMetaData();
		findAllBigTradeStep();
		for (String symbol : symbols) {
			JSONObject symbolTagJson = t.getSymbolMetaDataJson(symbol);
			if (symbolTagJson == null) {
				System.out.println(symbol + ", Can NOT find meta data");
				continue;
			}
		}

//		List<String> symbolList = t.filterByTags( tagMap.get(CryptoCoinTagType.ETH));
//		List<String> symbolList = t.filterByTags( Arrays.asList("sport","game"));
//		List<String> symbolList = t
//				.filterByTags(Arrays.asList("Layer 2", "L2", "Layer2", "2 層", "2層", "二層", "2层", "2 层", "二层"));
//		System.out.println(symbolList);
	}

	public static void saveAllMetaData() {
		FileUtilCustom ioUtil = new FileUtilCustom();
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Set<String> symbolSet = new HashSet<>();
		for (String symbol : symbols) {
			symbol = symbol.replaceAll("USDT", "").replaceAll("USDC", "").replaceAll("1000", "");
			if (StringUtils.isNotBlank(symbol)) {
				symbolSet.add(symbol);
			}
		}
		for (String symbol : symbolSet) {
			JSONObject metaData = getMetaDataBySymbolFromAPI(symbol);
			ioUtil.byteToFile(metaData.toString().getBytes(StandardCharsets.UTF_8),
					symbolMetaFilePathPrefix + "/" + symbol + ".json");
		}
	}

	public static JSONObject getMetaDataBySymbolFromAPI(String symbol) {
		System.out.println("Get meta data for symbol:" + symbol);
		HttpUtil h = new HttpUtil();
		String mainUrl = "https://www.binance.com/bapi/apex/v1/public/apex/marketing/tardingPair/detail?symbol="
				+ symbol;
		try {
			String response = h.sendGet(mainUrl);
			JSONObject json = JSONObject.fromObject(response);
			return json;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getDescFromInfo(JSONObject json) {
		try {
			JSONArray data = json.getJSONArray("data");
			JSONArray details = data.getJSONObject(0).getJSONArray("details");
			String description = "";
			for (int i = 0; i < details.size(); i++) {
				description += details.getJSONObject(i).getString("description");
			}
			return description;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<CryptoCoinTagType> findTagsFromDescription(String desc) {
		desc = desc.toLowerCase();
		List<CryptoCoinTagType> tagList = new ArrayList<>();
		for (Entry<CryptoCoinTagType, List<String>> entry : tagMap.entrySet()) {
			List<String> keyWordList = entry.getValue();
			keywordLoop: for (String keyword : keyWordList) {
				if (desc.contains(keyword)) {
					tagList.add(entry.getKey());
					break keywordLoop;
				}
			}
		}
		return tagList;
	}

	public JSONObject getSymbolMetaDataJson(String symbol) {
		FileUtilCustom f = new FileUtilCustom();
		try {
			String content = f.getStringFromFile(symbolMetaFilePathPrefix + "/" + symbol + ".json");
			JSONObject json = JSONObject.fromObject(content);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> filterByTags(List<String> tags) {
		List<String> symbolList = new ArrayList<>();
		for (String symbol : symbols) {
			symbol = symbol.replaceAll("USDT", "").replaceAll("USDC", "").replaceAll("1000", "");
			JSONObject contentJson = getSymbolMetaDataJson(symbol);
			if (contentJson == null) {
				continue;
			}
			try {
				JSONArray data = contentJson.getJSONArray("data");
				if (data == null || data.isEmpty()) {
					System.out.println(symbol + ", has NOT data");
					continue;
				}
				JSONObject metaData = data.getJSONObject(0);
				JSONArray details = metaData.getJSONArray("details");
				String desc = details.getJSONObject(0).getString("description");
				desc = desc + details.getJSONObject(1).getString("description");
				desc = desc.toUpperCase();
				tagLoop: for (String tag : tags) {
					if (desc.contains(tag.toUpperCase())) {
						symbolList.add(String.valueOf(symbol));
						break tagLoop;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

		}
		return symbolList;
	}

	public static void findAllBigTradeStep() {
		FileUtilCustom ioUtil = new FileUtilCustom();
		BigDecimal rate = new BigDecimal(1000000);
		for (String symbol : symbols) {
			try {
				String content = ioUtil
						.getStringFromFile(symbolMetaFilePathPrefix + "/" + symbol.replaceAll("USDT", "") + ".json");
				JSONObject json = JSONObject.fromObject(content);
				JSONObject data = json.getJSONArray("data").getJSONObject(0);
				BigDecimal marketCap = new BigDecimal(data.getString("marketCap"));
				BigDecimal step = marketCap.divide(rate);
				System.out.println(symbol + ", " + step);
			} catch (Exception e) {
//				System.err.println(symbol);
//				e.printStackTrace();
			}
		}
	}
}
