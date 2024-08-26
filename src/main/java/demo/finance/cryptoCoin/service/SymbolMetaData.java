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

import demo.finance.cryptoCoin.pojo.type.CryptoCoinTagType;
import finance.cryptoCoin.binance.future.um.pojo.dto.CryptoCoinBinanceFutureUmPriceCacheSubBO;
import finance.cryptoCoin.binance.future.um.pojo.result.CryptoCoinBinanceFutureUmPriceResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import toolPack.httpHandel.HttpUtil;
import toolPack.ioHandle.FileUtilCustom;

public class SymbolMetaData {

	private static Map<CryptoCoinTagType, List<String>> tagMap = new HashMap<>();
	private static List<String> symbols = new ArrayList<>();
	private static List<String> kSymbols = new ArrayList<>();
	private static String symbolMetaFilePathPrefix = System.getProperty("user.home") + "/programOutput/tp";

	static {
		symbols.addAll(Arrays.asList(
				"SUSHIUSDT,INJUSDT,BNTUSDT,SAGAUSDT,RDNTUSDT,ZRXUSDT,HIGHUSDT,XAIUSDT,SPELLUSDT,BNBUSDT,XTZUSDT,DARUSDT,MEWUSDT,JOEUSDT,ETCUSDT,PENDLEUSDT,XMRUSDT,ALICEUSDT,HOOKUSDT,REZUSDT,ACEUSDT,BNBUSDC,REEFUSDT,BATUSDT,1000BONKUSDT,DOGEUSDT,TRXUSDT,STORJUSDT,SNXUSDT,DOGEUSDC,1000BONKUSDC,XLMUSDT,MATICUSDC,IOTXUSDT,ARKUSDT,OMNIUSDT,UMAUSDT,DASHUSDT,KAVAUSDT,OXTUSDT,APEUSDT,RUNEUSDT,BNXUSDT,LTCUSDC,OPUSDT,KEYUSDT,SKLUSDT,GLMUSDT,ALTUSDT,MTLUSDT,LTCUSDT,RENDERUSDT,GUSDT,ETHBTC,KSMUSDT,FLOWUSDT,TRBUSDT,MANAUSDT,CHRUSDT,WUSDT,ONDOUSDT,AEVOUSDT,OGNUSDT,ENAUSDT,STMXUSDT,BLURUSDT,KNCUSDT,ENJUSDT,XRPUSDC,ATOMUSDT,NMRUSDT,GASUSDT,ENSUSDT,ATAUSDT,IOSTUSDT,HBARUSDT,ZECUSDT,SYNUSDT,POLYXUSDT,BANANAUSDT,EDUUSDT,GALAUSDT,VIDTUSDT,GTCUSDT,ALGOUSDT,MANTAUSDT,LISTAUSDT,BSVUSDT,ALPACAUSDT,LRCUSDT,PYTHUSDT,ENAUSDC,ORBSUSDT,STGUSDT,STXUSDT,ARPAUSDT,CELOUSDT,QNTUSDT,AIUSDT,1INCHUSDT,TUSDT,IOUSDT,PIXELUSDT,TAOUSDT,SOLUSDC,LINAUSDT,ARUSDT,STRKUSDT,FILUSDT,ZETAUSDT,DODOXUSDT,SOLUSDT,AXLUSDT,LOOMUSDT,RONINUSDT,1000SATSUSDT,BLZUSDT,COMBOUSDT,FILUSDC,GMTUSDT,XVSUSDT,GMXUSDT,LDOUSDT,BANDUSDT,XRPUSDT,SYSUSDT,PORTALUSDT,CRVUSDT,BELUSDT,VOXELUSDT,BONDUSDT,DOTUSDT,ONEUSDT,MAVIAUSDT,APTUSDT,SEIUSDT,CRVUSDC,ANKRUSDT,MAVUSDT,MEMEUSDT,API3USDT,ASTRUSDT,HOTUSDT,QTUMUSDT,IOTAUSDT,ETHUSDC,ADAUSDT,JTOUSDT,LSKUSDT,LITUSDT,ZROUSDT,BIGTIMEUSDT,STEEMUSDT,ETHFIUSDT,YFIUSDT,USTCUSDT,ETHUSDT,MYROUSDT,RAREUSDT,ORDIUSDT,ALPHAUSDT,WOOUSDT,SFPUSDT,ETHFIUSDC,RLCUSDT,ORDIUSDC,1000XECUSDT,CFXUSDT,FXSUSDT,BADGERUSDT,IDUSDT,HFTUSDT,UNFIUSDT,NEOUSDT,POWRUSDT,SANDUSDT,WAXPUSDT,MINAUSDT,LINKUSDT,NULSUSDT,RIFUSDT,CELRUSDT,AGLDUSDT,RSRUSDT,RENUSDT,LPTUSDT,JASMYUSDT,NOTUSDT,PHBUSDT,ARBUSDC,YGGUSDT,EGLDUSDT,DYMUSDT,LUNA2USDT,VETUSDT,ONTUSDT,NFPUSDT,IMXUSDT,LQTYUSDT,NEOUSDC,POPCATUSDT,COTIUSDT,ARBUSDT,VANRYUSDT,LINKUSDC,BAKEUSDT,GRTUSDT,AUCTIONUSDT,FLMUSDT,MASKUSDT,EOSUSDT,ZKUSDT,WIFUSDC,NEARUSDC,BALUSDT,BBUSDT,SUIUSDT,METISUSDT,BCHUSDC,DENTUSDT,TRUUSDT,CKBUSDT,SSVUSDT,CYBERUSDT,TOKENUSDT,BRETTUSDT,C98USDT,ZENUSDT,NEARUSDT,TWTUSDT,BEAMXUSDT,SUIUSDC,BCHUSDT,BOMEUSDT,1000SHIBUSDT,TLMUSDT,AVAXUSDC,ETHWUSDT,HIFIUSDT,AAVEUSDT,BICOUSDT,TONUSDT,ICPUSDT,WIFUSDT,1000SHIBUSDC,BOMEUSDC,1000LUNCUSDT,TURBOUSDT,CAKEUSDT,AVAXUSDT,NTRNUSDT,JUPUSDT,MAGICUSDT,ROSEUSDT,MOVRUSDT,OMUSDT,MATICUSDT,ONGUSDT,XVGUSDT,TIAUSDT,MKRUSDT,PEOPLEUSDT,BTCUSDC,THETAUSDT,1000PEPEUSDC,UNIUSDT,PERPUSDT,RVNUSDT,ARKMUSDT,NKNUSDT,KLAYUSDT,DEFIUSDT,TIAUSDC,TNSRUSDT,COMPUSDT,BTCDOMUSDT,BTCUSDT,OMGUSDT,ICXUSDT,1000PEPEUSDT,SUPERUSDT,FETUSDT,1000RATSUSDT,LEVERUSDT,1000FLOKIUSDT,FTMUSDT,AMBUSDT,SXPUSDT,XEMUSDT,WLDUSDT,ZILUSDT,DYDXUSDT,AXSUSDT,WLDUSDC,CHZUSDT,ILVUSDT,DUSKUSDT,CTSIUSDT,KASUSDT,ACHUSDT,SUNUSDT"
//				"MEW,XMR,BNB,REEF,BONK,DOGE,BONK,MATIC,LTC,ONDO,XRP,BSV,ENA,ORBS,SOL,ZETA,DODOX,LOOM,FIL,BOND,MAVIA,CRV,ETH,BIGTIME,MYRO,ETHFI,ORDI,XEC,ARB,LUNA2,NEO,POPCAT,LINK,WIF,NEAR,BCH,TOKEN,BRETT,SUI,SHIB,AVAX,ETHW,SHIB,BOME,LUNC,TURBO,BTC,PEPE,DEFI,TIA,BTCDOM,OMG,PEPE,RATS,FLOKI,XEM,WLD,KAS"
						.split(",")));
		for (String symbol : symbols) {
			if (symbol.startsWith("1000")) {
				kSymbols.add(symbol);
			}
		}
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
//		SymbolMetaData t = new SymbolMetaData();
//		loadAllSymbolListFromAPI();
//		setProxy();
//		saveAllMetaData();
		findAllBigTradeStep();
//		JSONObject symbolTagJson = t.getSymbolTagJson();
//		List<String> symbolList = t.filterByTags(symbolTagJson, tagMap.get(CryptoCoinTagType.ETH));
////		List<String> symbolList = t.filterByTags(symbolTagJson, Arrays.asList("sport","game"));
//		System.out.println(symbolList);
	}

	public static void loadAllSymbolListFromAPI() {
		CryptoCoinBinanceFutureUmPriceResult lastPriceResult = BinanceFutureUmDataApiUnit.getLastPrice();
		for (Entry<String, CryptoCoinBinanceFutureUmPriceCacheSubBO> entry : lastPriceResult.getPriceMap().entrySet()) {
			if (!symbols.contains(entry.getKey())) {
				symbols.add(entry.getKey());
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

	public static void saveAllMetaData() {
		FileUtilCustom ioUtil = new FileUtilCustom();
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Set<String> symbolSet = new HashSet<>();
		for (String symbol : symbols) {
			symbolSet.add(symbol.replaceAll("USDT", "").replaceAll("USDC", "").replaceAll("1000", ""));
		}
		for (String symbol : symbolSet) {
			JSONObject metaData = getMetaDataBySymbolFromAPI(symbol);
			String filename = symbol + "USDT";
			if (kSymbols.contains("1000" + symbol)) {
				filename = "1000" + filename;
			}
			ioUtil.byteToFile(metaData.toString().getBytes(StandardCharsets.UTF_8),
					symbolMetaFilePathPrefix + "/" + filename + ".json");
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

	public List<CryptoCoinTagType> matchTags(String symbol, String desc) {
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

	public JSONObject getSymbolTagJson() {
		FileUtilCustom f = new FileUtilCustom();
		String content = f.getStringFromFile(symbolMetaFilePathPrefix);
		JSONObject json = JSONObject.fromObject(content);
		return json;
	}

	public List<String> filterByTags(JSONObject symbolTagJson, List<String> tags) {
		@SuppressWarnings("unchecked")
		Set<Object> keys = symbolTagJson.keySet();
		List<String> symbolList = new ArrayList<>();
		for (Object key : keys) {
			String desc = String.valueOf(symbolTagJson.get(key));
			desc = desc.toUpperCase();
			tagLoop: for (String tag : tags) {
				if (desc.contains(tag.toUpperCase())) {
					symbolList.add(String.valueOf(key));
					break tagLoop;
				}
			}
		}
		return symbolList;
	}

	public static void findAllBigTradeStep() {
		FileUtilCustom ioUtil = new FileUtilCustom();
		BigDecimal rate = new BigDecimal(1000000);
		for (String symbol : symbols) {
			try {
				String content = ioUtil.getStringFromFile(symbolMetaFilePathPrefix + "/" + symbol + ".json");
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
