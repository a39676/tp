package demo.finance.cryptoCoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolCheckComplex {

	private String allListStr = "BTC, ETH, BNB, SOL, USDC, XRP, DOGE, ADA, TRX, AVAX, SHIB, WBTC, DOT, LINK, BCH, NEAR, DAI, MATIC, LTC, UNI, ICP, PEPE, ETC, XLM, WBETH, APT, FET, MKR, STX, HBAR, FIL, ATOM, RNDR, VET, ARB, IMX, SUI, FDUSD, OP, INJ, GRT, TAO, WIF, AR, NOT, BONK, AAVE, LDO, THETA, FLOKI, FTM, RUNE, TIA, ALGO, JASMY, PYTH, JUP, SEI, EGLD, OM, FLOW, QNT, STRK, AXS, EOS, ENS, BTTC, XTZ, BEAMX, NEO, SAND, RONIN, GALA, ORDI, GNO, ENA, NEXO, MANA, CFX, CHZ, XEC, PENDLE, MINA, ZK, DEXE, KLAY, SNX, ROSE, BOME, IOTA, ASTR, 1INCH, CAKE, AXL, CKB, WLD, TUSD, W, RAY, TFUEL, APE, FTT, LPT, BNX, PAXG, ZEC, KAVA, TWT, 1000SATS, ZRO, LUNC, COMP, AEVO, WOO, IOTX, SFP, PEOPLE, CRV, GMT, KSM, SSV, RPL, DYDX, OSMO, MEME, GLM, LUNA, DASH, ZIL, HOT, JST, CELO, BLUR, ILV, ARKM, MANTA, ANKR, GAL, ENJ, SUPER, ELF, BAT, SC, ZRX, ID, JTO, RVN, DYM, GMX, QTUM, ETHFI, SKL, RSR, GAS, BICO, METIS, IO, MASK, DCR, POLYX, FLUX, CVX, BB, T, EDU, LRC, VTHO, CHR, TRB, YFI, ONE, FXS, ACH, GLMR, AMP, SUSHI, ZEN, UMA, ONT, YGG, VANRY, API3, KDA, AUDIO, BAND, TRU, BAL, STORJ, ICX, SXP, PIXEL, POND, ALT, LSK, NTRN, COTI, RLC, SAGA, WAXP, MAGIC, C98, DGB, IOST, LISTA, ONG, CTSI, PROM, USDP, DUSK, SUN, XNO, JOE, SLP, HIVE, AI, IQ, REQ, CELR, SNT, CVC, OMNI, POWR, GNS, NMR, HIGH, CYBER, XVS, KNC, PHA, PUNDIX, ACE, AUCTION, SPELL, USTC, STRAX, PYR, DENT, PORTAL, XAI, CTK, SYS, MOVR, WIN, STPT, STEEM, SYN, CREAM, RIF, BAKE, HOOK, HFT, PHB, BLZ, LEVER, LQTY, REZ, STG, BNT, DODO, SCRT, DAR, FRONT, MTL, QI, OXT, XVG, ARDR, NFP, ALICE, AGLD, MBOX, LOOM, ACA, RARE, AEUR, ALPHA, RAD, ARPA, HIFI, ARK, NKN, BADGER, RDNT, WRX, TNSR, MLN, GHST, GTC, OGN, TKO, MAV, MDX, ORN, STMX, QKC, LTO, CLV, BETA, REI, FORTH, TLM, ATA, ERN, REN, MBL, KMD, PERP, DIA, PDA, AERGO, FIS, COS, FLM, LIT, FUN, DATA, POLS, ALCX, WAN, CTXC, COMBO, NULS, AVA, DEGO, LINA, BEL, IDEX, PSG, UNFI, FIDA, VIC, MDT, BSW, REEF, FARM, LOKA, QUICK, UTK, KEY, VOXEL, BIFI, ADX, KP3R, UFT, AKRO, WING, IRIS, BURGER, BAR, PIVX, CHESS, FIO, ALPACA, AMB, CITY, GFT, VIDT, FIRO, VITE, VGX, AST, ALPINE, HARD, OG, BOND, TROY, SANTOS, VIB, ASR, OOKI, DF, JUV, LAZIO, PORTO, OAX, PROS, IDRT, FOR, ATM, ACM, EPX, DOCK, CVP";
	private String exceptListStr = "USDT, FDUSD, USDC, TUSD, USDP, DAI, IDRT, WBETH,WBTC,USTC";

	public static void main(String[] args) {
		SymbolCheckComplex c = new SymbolCheckComplex();
//		c.splitIntoGroup();
		c.checkForFuture();
	}

	public void splitIntoGroup() {
		String b10Symbol = "AVAX";
		String b1Symbol = "SEI";
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
		String futureStr = "SUSHI,INJ,BNT,SAGA,RDNT,ZRX,HIGH,XAI,SPELL,XTZ,BNB,DAR,MEW,JOE,PENDLE,XMR,ETC,ALICE,HOOK,ACE,REZ,BNBUSDC,REEF,BAT,DOGE,1000BONK,TRX,STORJ,SNX,1000BONKUSDC,DOGEUSDC,XLM,MATICUSDC,IOTX,ARK,OMNI,UMA,DASH,KAVA,OXT,RUNE,APE,BNX,LTCUSDC,OP,KEY,SKL,GLM,ALT,MTL,LTC,ETHBTC,KSM,MANA,TRB,FLOW,CHR,W,ONDO,AEVO,GAL,USDC,RNDR,OGN,ENA,STMX,BLUR,KNC,ENJ,XRPUSDC,ATOM,NMR,ENS,GAS,ATA,IOST,HBAR,ZEC,POLYX,ETHUSDT_240927,GALA,EDU,GTC,ALGO,MANTA,LISTA,BSV,LRC,PYTH,ENAUSDC,ORBS,STG,STX,ARPA,CELO,QNT,AI,1INCH,T,IO,PIXEL,SOLUSDC,TAO,LINA,AR,FIL,STRK,ZETA,DODOX,SOL,AXL,LOOM,RONIN,BLZ,1000SATS,COMBO,FILUSDC,GMT,XVS,GMX,BAND,LDO,XRP,PORTAL,CRV,BEL,BOND,DOT,ONE,MAVIA,APT,FRONT,SEI,CRVUSDC,ANKR,MAV,MEME,API3,ASTR,HOT,QTUM,IOTA,ETHUSDC,ADA,JTO,LSK,LIT,ZRO,STEEM,BIGTIME,ETHFI,BTCUSDT_240927,YFI,USTC,ETH,MYRO,ORDI,ALPHA,WOO,SFP,ETHFIUSDC,RLC,ORDIUSDC,1000XEC,CFX,FXS,BADGER,ID,HFT,ETHUSDT_241227,UNFI,NEO,POWR,SAND,WAXP,LINK,MINA,RIF,CELR,AGLD,RSR,REN,LPT,JASMY,NOT,PHB,ARBUSDC,YGG,BTCUSDT_241227,EGLD,LUNA2,DYM,ONT,IMX,VET,NFP,LQTY,NEOUSDC,COTI,VANRY,ARB,LINKUSDC,BAKE,GRT,AUCTION,FLM,MASK,EOS,WIFUSDC,ZK,NEARUSDC,BAL,BB,SUI,METIS,BCHUSDC,DENT,TRU,CKB,SSV,TOKEN,CYBER,C98,ZEN,NEAR,TWT,BEAMX,SUIUSDC,BCH,BOME,1000SHIB,TLM,AVAXUSDC,ETHW,HIFI,BICO,AAVE,TON,ICP,1000SHIBUSDC,WIF,BOMEUSDC,1000LUNC,TURBO,AVAX,CAKE,NTRN,JUP,MAGIC,ROSE,MOVR,MATIC,OM,ONG,XVG,TIA,PEOPLE,MKR,BTCUSDC,THETA,1000PEPEUSDC,UNI,PERP,RVN,ARKM,NKN,KLAY,DEFI,TIAUSDC,TNSR,COMP,BTCDOM,BTC,OMG,ICX,1000PEPE,SUPER,FET,1000RATS,LEVER,1000FLOKI,FTM,AMB,SXP,XEM,WLD,ZIL,DYDX,AXS,WLDUSDC,CHZ,ILV,DUSK,ACH,CTSI,KAS";
		String exceptStr = exceptListStr + ",BNX,NOT,BTC,ETH,SOL,BNB,BOND,GLM";
		String inputStr = "EDU,SAGA,ETHFI,AEVO,ARB,FTM,ENS,SSV,LDO,HOOK,OMNI,TIA,RONIN,ALT,W,PENDLE,BLUR,BAL,METIS,GMX,OP,STRK,C98,SUI,RDNT,ACE,1INCH,AVAX,PIXEL,ONE,ETC,PORTAL,BNT,BICO,STORJ,ATA,HFT,CRV,LINK,RSR,ZRX,REN,ALICE,NEAR,IOTA,FRONT,MEME,BEAMX,PEPE,LOOM,VANRY,APT,SNX,CTSI,FXS,RUNE,PERP,MOVR,ONG,LRC,SKL,CFX,LPT,LQTY,SEI,ALPHA,MATIC,YGG,BOME,FET,WAXP,DYM,CELO,MAGIC,XAI,MANTA,GALA,FLOKI,RLC,NKN,RIF,NMR,SXP,DAR,ONT,FIL,ANKR,FLM,RVN,KAVA,HIGH,APE,DYDX,UMA,IMX,OGN,T,SFP,UNI,HOT,ARKM,CYBER,QTUM,TRB,ARK,TNSR,DUSK,LSK,ID,NFP,EGLD,ALGO,AMB,VET,MASK,AXS,ENA,ATOM,GRT,MINA,LTC,ORDI,SHIB,MKR";

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
		String str1 = "DUSKUSDT,FETUSDT,ARKMUSDT,DYMUSDT,CHRUSDT,PORTALUSDT,RLCUSDT,TIAUSDT,AMBUSDT,LDOUSDT,PIXELUSDT,PENDLEUSDT,BICOUSDT,AXLUSDT,TNSRUSDT,WUSDT,PHBUSDT,AIUSDT,CTSIUSDT,RIFUSDT,WLDUSDT,MAVUSDT,LEVERUSDT,TRUUSDT,LITUSDT,NTRNUSDT,1000BONKUSDT,NMRUSDT,IOTXUSDT,PERPUSDT,HIGHUSDT,FTMUSDT,BAKEUSDT,OXTUSDT,CYBERUSDT,AVAXUSDT,SKLUSDT,KSMUSDT,MAGICUSDT,KEYUSDT";
		String str2 = "EDUUSDT,SAGAUSDT,ETHFIUSDT,AEVOUSDT,ARBUSDT,FTMUSDT,ENSUSDT,SSVUSDT,LDOUSDT,HOOKUSDT,OMNIUSDT,TIAUSDT,RONINUSDT,ALTUSDT,WUSDT,PENDLEUSDT,BLURUSDT,BALUSDT,METISUSDT,GMXUSDT,OPUSDT,STRKUSDT,C98USDT,SUIUSDT,RDNTUSDT,ACEUSDT,1INCHUSDT,AVAXUSDT,PIXELUSDT,ONEUSDT,ETCUSDT,PORTALUSDT,BNTUSDT,BICOUSDT,STORJUSDT,ATAUSDT,HFTUSDT,CRVUSDT,LINKUSDT,RSRUSDT";
		
		List<String> l1 = Arrays.asList(str1.split(","));
		List<String> l2 = Arrays.asList(str2.split(","));
		
		
	}
}
