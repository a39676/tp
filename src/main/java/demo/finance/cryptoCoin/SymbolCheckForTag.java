package demo.finance.cryptoCoin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import demo.finance.cryptoCoin.pojo.type.CryptoCoinTagType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import toolPack.httpHandel.HttpUtil;
import toolPack.ioHandle.FileUtilCustom;

public class SymbolCheckForTag {

	private static Map<CryptoCoinTagType, List<String>> tagMap = new HashMap<>();
	private static List<String> symbols = new ArrayList<>();
	private static String symbolTagFilePath = System.getProperty("user.home")
			+ "/optionFile/cryptoCoinMonitor/symbolTag.json";

	static {
//		String symbolsStr = "BTC,ETH,BNB,SOL";
		String symbolsStr = "BTC,ETH,BNB,SOL,XRP,DOGE,ADA,TRX,AVAX,SHIB,WBTC,DOT,LINK,BCH,NEAR,MATIC,LTC,UNI,ICP,PEPE,ETC,XLM,WBETH,APT,FET,MKR,STX,HBAR,FIL,ATOM,RNDR,VET,ARB,IMX,SUI,OP,INJ,GRT,TAO,WIF,AR,NOT,BONK,AAVE,LDO,THETA,FLOKI,FTM,RUNE,TIA,ALGO,JASMY,PYTH,JUP,SEI,EGLD,OM,FLOW,QNT,STRK,AXS,EOS,ENS,BTTC,XTZ,BEAMX,NEO,SAND,RONIN,GALA,ORDI,GNO,ENA,NEXO,MANA,CFX,CHZ,XEC,PENDLE,MINA,ZK,DEXE,KLAY,SNX,ROSE,BOME,IOTA,ASTR,1INCH,CAKE,AXL,CKB,WLD,W,RAY,TFUEL,APE,FTT,LPT,BNX,PAXG,ZEC,KAVA,TWT,1000SATS,ZRO,LUNC,COMP,AEVO,WOO,IOTX,SFP,PEOPLE,CRV,GMT,KSM,SSV,RPL,DYDX,OSMO,MEME,GLM,LUNA,DASH,ZIL,HOT,JST,CELO,BLUR,ILV,ARKM,MANTA,ANKR,ENJ,SUPER,ELF,BAT,SC,ZRX,ID,JTO,RVN,DYM,GMX,QTUM,ETHFI,SKL,RSR,GAS,BICO,METIS,IO,MASK,DCR,POLYX,FLUX,CVX,BB,T,EDU,LRC,VTHO,CHR,TRB,YFI,ONE,FXS,ACH,GLMR,AMP,SUSHI,ZEN,UMA,ONT,YGG,VANRY,API3,KDA,AUDIO,BAND,TRU,BAL,STORJ,ICX,SXP,PIXEL,POND,ALT,LSK,NTRN,COTI,RLC,SAGA,WAXP,MAGIC,C98,DGB,IOST,LISTA,ONG,CTSI,PROM,DUSK,SUN,XNO,JOE,SLP,HIVE,AI,IQ,REQ,CELR,SNT,CVC,OMNI,POWR,GNS,NMR,HIGH,CYBER,XVS,KNC,PHA,PUNDIX,ACE,AUCTION,SPELL,USTC,STRAX,PYR,DENT,PORTAL,XAI,CTK,SYS,MOVR,WIN,STPT,STEEM,SYN,CREAM,RIF,BAKE,HOOK,HFT,PHB,BLZ,LEVER,LQTY,REZ,STG,BNT,DODO,SCRT,DAR,FRONT,MTL,QI,OXT,XVG,ARDR,NFP,ALICE,AGLD,MBOX,LOOM,ACA,RARE,AEUR,ALPHA,RAD,ARPA,HIFI,ARK,NKN,BADGER,RDNT,WRX,TNSR,MLN,GHST,GTC,OGN,TKO,MAV,MDX,ORN,STMX,QKC,LTO,CLV,BETA,REI,FORTH,TLM,ATA,ERN,REN,MBL,KMD,PERP,DIA,PDA,AERGO,FIS,COS,FLM,LIT,FUN,DATA,POLS,ALCX,WAN,CTXC,COMBO,NULS,AVA,DEGO,LINA,BEL,IDEX,PSG,UNFI,FIDA,VIC,MDT,BSW,REEF,FARM,LOKA,QUICK,UTK,KEY,VOXEL,BIFI,ADX,KP3R,UFT,AKRO,WING,IRIS,BURGER,BAR,PIVX,CHESS,FIO,ALPACA,AMB,CITY,GFT,VIDT,FIRO,VITE,VGX,AST,ALPINE,HARD,OG,BOND,TROY,SANTOS,VIB,ASR,OOKI,DF,JUV,LAZIO,PORTO,OAX,PROS,FOR,ATM,ACM,EPX,DOCK,CVP";
		symbols.addAll(Arrays.asList(symbolsStr.split(",")));
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

		String proxyHost = "127.0.0.1";
		String proxyPort = "10809";
		System.setProperty("http.proxyHost", proxyHost);
		System.setProperty("http.proxyPort", proxyPort);
		System.setProperty("https.proxyHost", proxyHost);
		System.setProperty("https.proxyPort", proxyPort);
	}

	public static void main(String[] args) {
		SymbolCheckForTag t = new SymbolCheckForTag();
//		t.saveDesc();
		JSONObject symbolTagJson = t.getSymbolTagJson();
		List<String> symbolList = t.filterByTags(symbolTagJson, tagMap.get(CryptoCoinTagType.ETH));
		System.out.println(symbolList);
	}

	public void saveDesc() {
		JSONObject outputJson = new JSONObject();
		for (String symbol : symbols) {
			JSONObject metaData = getMetaDataBySymbolFromAPI(symbol);
			String desc = getDescFromInfo(metaData);
			outputJson.put(symbol, desc);
		}

		FileUtilCustom ioUtil = new FileUtilCustom();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(outputJson);
		ioUtil.byteToFile(jsonString.toString().getBytes(StandardCharsets.UTF_8), symbolTagFilePath);
	}

	public JSONObject getMetaDataBySymbolFromAPI(String symbol) {
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
		String content = f.getStringFromFile(symbolTagFilePath);
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
}
