package demo.finance.cryptoCoin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import finance.cryptoCoin.binance.future.um.pojo.dto.CryptoCoinBinanceFutureUmPriceCacheSubBO;
import finance.cryptoCoin.binance.future.um.pojo.result.CryptoCoinBinanceFutureUmPriceResult;

public class SymbolCheckComplex {

	private String allListStr = "BTC, ETH, USDT, BNB, SOL, USDC, XRP, DOGE, ADA, TRX, WBTC, AVAX, SHIB, DOT, BCH, LINK, DAI, NEAR, LTC, MATIC, UNI, PEPE, ICP, ETC, WBETH, APT, XLM, FET, STX, MKR, HBAR, ATOM, ARB, FDUSD, RENDER, VET, IMX, INJ, TAO, OP, WIF, SUI, AAVE, AR, BONK, GRT, FLOKI, LDO, RUNE, THETA, JASMY, JUP, NOT, PYTH, FTM, TIA, ALGO, OM, SEI, QNT, EOS, BTTC, EGLD, AXS, BEAMX, ENS, NEO, XTZ, STRK, GALA, SAND, XEC, ORDI, ENA, CFX, NEXO, WLD, RONIN, BOME, MANA, ZEC, CHZ, 1000SATS, BNX, MINA, KLAY, SNX, TUSD, RAY, DEXE, ROSE, PENDLE, GNO, IOTA, PAXG, ZK, FTT, ASTR, CKB, COMP, LPT, CAKE, AXL, APE, LUNC, W, ZRO, 1INCH, TFUEL, TWT, KAVA, IOTX, SFP, AEVO, CRV, PEOPLE, JTO, ELF, GLM, WOO, MANTA, G, DASH, GMT, CVX, KSM, ZIL, MEME, BLUR, LUNA, OSMO, SUPER, ENJ, RPL, ZRX, CELO, JST, ANKR, SC, SKL, ID, DYM, BAT, HOT, DYDX, QTUM, SSV, ILV, RVN, GMX, METIS, ARKM, RSR, GAS, BICO, T, MASK, ETHFI, POLYX, IO, FLUX, DCR, FXS, YFI, LRC, BAND, UMA, ONE, TRB, VTHO, EDU, CHR, SUSHI, ACH, ONT, AMP, GLMR, BB, VANRY, AUDIO, BANANA, STORJ, COTI, ZEN, YGG, ICX, API3, SXP, XAI, KDA, NTRN, BAL, LSK, SAGA, WAXP, PIXEL, IOST, ONG, DGB, JOE, TRU, USDP, POWR, MAGIC, CTSI, ACE, XVS, C98, ALT, RLC, POND, XNO, IQ, PROM, AUCTION, SLP, PUNDIX, GNS, LISTA, SUN, HIVE, USTC, CVC, STMX, DUSK, CELR, NMR, HIGH, RIF, KNC, RDNT, DAR, STRAX, SNT, SPELL, CTK, AI, WIN, STEEM, CYBER, DENT, PHA, MOVR, HOOK, STPT, OMNI, PYR, DODO, PORTAL, SYN, BAKE, REQ, HFT, OXT, STG, PHB, ALICE, LQTY, HIFI, LOOM, BNT, SYS, MTL, BLZ, QI, LEVER, SCRT, ARK, AGLD, ARDR, AEUR, ACA, NFP, FRONT, XVG, RAD, CREAM, REZ, BADGER, QKC, MBOX, ARPA, NKN, GTC, ALPHA, OGN, GHST, TNSR, TKO, RARE, WRX, LTO, MLN, TLM, REI, BETA, FORTH, MBL, CLV, MAV, ATA, ERN, REN, ORN, CTXC, AERGO, DIA, LIT, COS, PERP, FUN, KMD, FLM, VIC, ALCX, DATA, FIS, LINA, PDA, WAN, FIDA, BEL, COMBO, PSG, AMB, UNFI, AVA, DEGO, IDEX, LOKA, GFT, KEY, REEF, NULS, FARM, MDT, BSW, QUICK, VOXEL, AKRO, IRIS, BIFI, VGX, ADX, KP3R, BAR, UTK, DF, CHESS, BURGER, WING, UFT, PIVX, CITY, FIO, FIRO, ALPACA, SANTOS, VIDT, VITE, ASR, OG, ALPINE, VIB, AST, TROY, OOKI, CVP, HARD, FOR, JUV, IDRT, OAX, LAZIO, PROS, PORTO, ACM, ATM, EPX";
	private String exceptListStr = "USDT, FDUSD, USDC, TUSD, USDP, WBETH,WBTC,USTC";

	static {
		String proxyHost = "127.0.0.1";
		String proxyPort = "10809";

		System.setProperty("http.proxyHost", proxyHost);
		System.setProperty("http.proxyPort", proxyPort);

		System.setProperty("https.proxyHost", proxyHost);
		System.setProperty("https.proxyPort", proxyPort);
	}

	public static void main(String[] args) {
//		SymbolCheckComplex c = new SymbolCheckComplex();
//		c.splitIntoGroup();
//		c.checkForFuture();
//		c.findDifferent();
		test();
	}

	public static void test() {
		BinanceFutureUmDataApiUnit api = new BinanceFutureUmDataApiUnit();
		CryptoCoinBinanceFutureUmPriceResult lastPriceResult = api.getLastPrice();
		Map<String, CryptoCoinBinanceFutureUmPriceCacheSubBO> priceMap = lastPriceResult.getPriceMap();
		for (String symbol : priceMap.keySet()) {
//			BinanceFutureUmLongShortRatio.globalLongShortAccountRatio(symbol);
			BinanceFutureUmLongShortRatio.topLongShortPositionRatio(symbol);
		}
	}

	public void splitIntoGroup() {
		String b10Symbol = "TRX";
		String b1Symbol = "OM";
		Map<String, Integer> mainMap = new HashMap<>();

		List<String> binanceFullList = new ArrayList<>();
		binanceFullList.addAll(Arrays.asList(allListStr.replaceAll(" ", "").split(",")));
		System.out.println("binance size: " + binanceFullList.size());
		System.out.println(binanceFullList);

		List<String> except = new ArrayList<>();
		except.addAll(Arrays.asList(exceptListStr.replaceAll(" ", "").split(",")));

		binanceFullList.removeAll(except);
		System.out.println(binanceFullList.size());

		Integer b10Index = binanceFullList.indexOf(b10Symbol);
		Integer b1Index = binanceFullList.indexOf(b1Symbol);

		for (int i = 0; i < b10Index; i++) {
			String tmpSymbol = binanceFullList.get(i);
			mainMap.put(tmpSymbol, 0);
		}

		for (int i = b10Index; i <= b1Index; i++) {
			String tmpSymbol = binanceFullList.get(i);
			mainMap.put(tmpSymbol, 1);
		}

		for (int i = b1Index; i < binanceFullList.size(); i++) {
			String tmpSymbol = binanceFullList.get(i);
			mainMap.put(tmpSymbol, i / 50 + 1);
		}

		System.out.println("Main map: ");
		System.out.println(mainMap);
		for (int i = 0; i < binanceFullList.size(); i++) {
			String tmpSymbol = binanceFullList.get(i);
			if (except.contains(tmpSymbol)) {
				continue;
			}
			System.out.println(tmpSymbol + "USDT:" + mainMap.get(tmpSymbol));
		}
	}

	public void checkForFuture() {
		String spotStr = allListStr.replaceAll(" ", "");
		String futureStr = "SUSHI,INJ,BNT,SAGA,RDNT,ZRX,HIGH,XAI,SPELL,XTZ,BNB,DAR,MEW,JOE,PENDLE,XMR,ETC,ALICE,HOOK,ACE,REZ,BNBUSDC,REEF,BAT,DOGE,1000BONK,TRX,STORJ,SNX,1000BONKUSDC,DOGEUSDC,XLM,MATICUSDC,IOTX,ARK,OMNI,UMA,DASH,KAVA,OXT,RUNE,APE,BNX,LTCUSDC,OP,KEY,SKL,GLM,ALT,MTL,LTC,ETHBTC,KSM,MANA,TRB,FLOW,CHR,W,ONDO,AEVO,GAL,USDC,RNDR,OGN,ENA,STMX,BLUR,KNC,ENJ,XRPUSDC,ATOM,NMR,ENS,GAS,ATA,IOST,HBAR,ZEC,POLYX,ETHUSDT_240927,GALA,EDU,GTC,ALGO,MANTA,LISTA,BSV,LRC,PYTH,ENAUSDC,ORBS,STG,STX,ARPA,CELO,QNT,AI,1INCH,T,IO,PIXEL,SOLUSDC,TAO,LINA,AR,FIL,STRK,ZETA,DODOX,SOL,AXL,LOOM,RONIN,BLZ,1000SATS,COMBO,FILUSDC,GMT,XVS,GMX,BAND,LDO,XRP,PORTAL,CRV,BEL,BOND,DOT,ONE,MAVIA,APT,FRONT,SEI,CRVUSDC,ANKR,MAV,MEME,API3,ASTR,HOT,QTUM,IOTA,ETHUSDC,ADA,JTO,LSK,LIT,ZRO,STEEM,BIGTIME,ETHFI,BTCUSDT_240927,YFI,USTC,ETH,MYRO,ORDI,ALPHA,WOO,SFP,ETHFIUSDC,RLC,ORDIUSDC,1000XEC,CFX,FXS,BADGER,ID,HFT,ETHUSDT_241227,UNFI,NEO,POWR,SAND,WAXP,LINK,MINA,RIF,CELR,AGLD,RSR,REN,LPT,JASMY,NOT,PHB,ARBUSDC,YGG,BTCUSDT_241227,EGLD,LUNA2,DYM,ONT,IMX,VET,NFP,LQTY,NEOUSDC,COTI,VANRY,ARB,LINKUSDC,BAKE,GRT,AUCTION,FLM,MASK,EOS,WIFUSDC,ZK,NEARUSDC,BAL,BB,SUI,METIS,BCHUSDC,DENT,TRU,CKB,SSV,TOKEN,CYBER,C98,ZEN,NEAR,TWT,BEAMX,SUIUSDC,BCH,BOME,1000SHIB,TLM,AVAXUSDC,ETHW,HIFI,BICO,AAVE,TON,ICP,1000SHIBUSDC,WIF,BOMEUSDC,1000LUNC,TURBO,AVAX,CAKE,NTRN,JUP,MAGIC,ROSE,MOVR,MATIC,OM,ONG,XVG,TIA,PEOPLE,MKR,BTCUSDC,THETA,1000PEPEUSDC,UNI,PERP,RVN,ARKM,NKN,KLAY,DEFI,TIAUSDC,TNSR,COMP,BTCDOM,BTC,OMG,ICX,1000PEPE,SUPER,FET,1000RATS,LEVER,1000FLOKI,FTM,AMB,SXP,XEM,WLD,ZIL,DYDX,AXS,WLDUSDC,CHZ,ILV,DUSK,ACH,CTSI,KAS,ZRO";
		String exceptStr = exceptListStr + ",BNX,NOT,BTC,ETH,SOL,BNB,BOND,GLM";
		String inputStr = "CRV,AAVE,ALT,RUNE,AMP,SUN,FTM,SAGA,1000SATS,RSR,BNX,BICO,DGB,LTC,PIXEL,DYM,UNI,FRONT,SNX,AR,BLUR,KEY,ACE,DYDX,CVX,STX,HIGH,REI,PENDLE,EOS,GMX,FTT,QTUM,TRX,MKR,WOO,ORDI,KLAY,UNFI,TNSR,UMA,DASH,APT,COMP,BNB,ETHFI,THETA,BAKE,ARPA,NEAR,NFP,CAKE,ASTR,XEC,ICP,JST,BCH,MOVR,DCR,PAXG,AXS,IMX,RONIN,SNT,ZEC,TRB,COS,HOOK,BETA,XRP,ENA,INJ,MASK,ETH,FET,ETC,GALA,RLC,AUCTION,ZRX,BAT,TFUEL,XVS,ONT,MBL,UFT,GMT,QKC,STORJ,MINA,BEL,LINK,MAV,PERP,WIN,SYS,MANTA,STPT,BTC,HOT,AI,GNO,ALICE,ALCX,TRU,AEUR,TLM,QUICK,STRAX,XVG,METIS,SLP,BADGER,ADA,FARM,DOGE,SC,FLM,GRT,NEXO,W,CYBER,ALGO,BNT,RIF,ARKM,AVAX,FXS,MATIC,PUNDIX,KNC,OAX";

		int maxSize = 40;
		List<String> spot = new ArrayList<>();
		List<String> future = new ArrayList<>();
		List<String> except = new ArrayList<>();
		List<String> input = new ArrayList<>();

		spot.addAll(Arrays.asList(spotStr.replaceAll(" ", "").split(",")));
		future.addAll(Arrays.asList(futureStr.replaceAll(" ", "").split(",")));
		except.addAll(Arrays.asList(exceptStr.replaceAll(" ", "").split(",")));
		input.addAll(Arrays.asList(inputStr.replaceAll(" ", "").split(",")));

		List<String> cross = new ArrayList<>();
		List<String> spotOnly = new ArrayList<>();
		List<String> kList = new ArrayList<>();

		for (String symbol : spot) {
			if (except.contains(symbol)) {
				continue;
			}
			if (future.contains(symbol)) {
				cross.add(symbol);
			} else if (future.contains("1000" + symbol)) {
				cross.add("1000" + symbol);
				kList.add(symbol);
			} else {
				spotOnly.add(symbol);
			}
		}

		System.out.println(cross);
		System.out.println(spotOnly);
		System.out.println(kList);

		for (int i = 0; i < input.size(); i++) {
			String symbol = input.get(i);
			if (spotOnly.contains(symbol) || except.contains(symbol)) {
				input.remove(i);
				i--;
				continue;
			}
			if (kList.contains(symbol)) {
				input.set(i, "1000" + symbol + "USDT");
			} else {
				input.set(i, symbol + "USDT");
			}
		}
		System.out.println(input);
		for (int i = 0; i < input.size() && i < maxSize; i++) {
			System.out.print(input.get(i) + ",");
		}
	}

	public void findDifferent() {
		String str1 = "DYMUSDT,1000SATSUSDT,GMXUSDT,RUNEUSDT,APTUSDT,PIXELUSDT,HIGHUSDT,AAVEUSDT,TIAUSDT,FTMUSDT,PENDLEUSDT,KEYUSDT,NFPUSDT,ALTUSDT,HOOKUSDT,ETHFIUSDT,ACEUSDT,ENAUSDT,FRONTUSDT,AIUSDT,STRKUSDT,RIFUSDT,BAKEUSDT,QTUMUSDT,COMPUSDT,IMXUSDT,WOOUSDT,MAVUSDT,ENSUSDT,TRBUSDT,XVGUSDT,SNXUSDT,BLURUSDT,DYDXUSDT,FETUSDT,MKRUSDT,UNIUSDT,NTRNUSDT,NEARUSDT,LTCUSDT";
		String str2 = "CRVUSDT,AAVEUSDT,ALTUSDT,RUNEUSDT,FTMUSDT,SAGAUSDT,1000SATSUSDT,RSRUSDT,BICOUSDT,LTCUSDT,PIXELUSDT,DYMUSDT,UNIUSDT,FRONTUSDT,SNXUSDT,ARUSDT,BLURUSDT,KEYUSDT,ACEUSDT,DYDXUSDT,STXUSDT,HIGHUSDT,PENDLEUSDT,EOSUSDT,GMXUSDT,QTUMUSDT,TRXUSDT,MKRUSDT,WOOUSDT,ORDIUSDT,KLAYUSDT,UNFIUSDT,TNSRUSDT,UMAUSDT,DASHUSDT,APTUSDT,COMPUSDT,ETHFIUSDT,THETAUSDT,BAKEUSDT,";

		List<String> l1 = Arrays.asList(str1.replaceAll(" ", "").split(","));
		List<String> l2 = Arrays.asList(str2.replaceAll(" ", "").split(","));

		List<String> l1Only = new ArrayList<>();
		List<String> l2Only = new ArrayList<>();
		List<String> cross = new ArrayList<>();
		for (int i = 0; i < l1.size(); i++) {
			String tmp = l1.get(i);
			if (!l2.contains(tmp)) {
				l1Only.add(tmp);
			} else {
				cross.add(tmp);
			}
		}
		for (int i = 0; i < l2.size(); i++) {
			String tmp = l2.get(i);
			if (!l1.contains(tmp)) {
				l2Only.add(tmp);
			}
		}
		System.out.println("l1Only, size: " + l1Only.size());
		System.out.println(l1Only);
		System.out.println("l2Only, size: " + l2Only.size());
		System.out.println(l2Only);
		System.out.println("cross size: " + cross.size());
		System.out.println(cross);
	}
}
