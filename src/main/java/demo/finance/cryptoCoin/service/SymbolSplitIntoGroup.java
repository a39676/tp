package demo.finance.cryptoCoin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolSplitIntoGroup {

	public static void main(String[] args) {
		String binanceFull = "BTC, ETH, BNB, SOL, USDC, XRP, DOGE, ADA, TRX, AVAX, SHIB, WBTC, DOT, LINK, BCH, NEAR, DAI, MATIC, LTC, UNI, ICP, PEPE, ETC, XLM, WBETH, APT, FET, MKR, STX, HBAR, FIL, ATOM, RNDR, VET, ARB, IMX, SUI, FDUSD, OP, INJ, GRT, TAO, WIF, AR, NOT, BONK, AAVE, LDO, THETA, FLOKI, FTM, RUNE, TIA, ALGO, JASMY, PYTH, JUP, SEI, EGLD, OM, FLOW, QNT, STRK, AXS, EOS, ENS, BTTC, XTZ, BEAMX, NEO, SAND, RONIN, GALA, ORDI, GNO, ENA, NEXO, MANA, CFX, CHZ, XEC, PENDLE, MINA, ZK, DEXE, KLAY, SNX, ROSE, BOME, IOTA, ASTR, 1INCH, CAKE, AXL, CKB, WLD, TUSD, W, RAY, TFUEL, APE, FTT, LPT, BNX, PAXG, ZEC, KAVA, TWT, 1000SATS, ZRO, LUNC, COMP, AEVO, WOO, IOTX, SFP, PEOPLE, CRV, GMT, KSM, SSV, RPL, DYDX, OSMO, MEME, GLM, LUNA, DASH, ZIL, HOT, JST, CELO, BLUR, ILV, ARKM, MANTA, ANKR, GAL, ENJ, SUPER, ELF, BAT, SC, ZRX, ID, JTO, RVN, DYM, GMX, QTUM, ETHFI, SKL, RSR, GAS, BICO, METIS, IO, MASK, DCR, POLYX, FLUX, CVX, BB, T, EDU, LRC, VTHO, CHR, TRB, YFI, ONE, FXS, ACH, GLMR, AMP, SUSHI, ZEN, UMA, ONT, YGG, VANRY, API3, KDA, AUDIO, BAND, TRU, BAL, STORJ, ICX, SXP, PIXEL, POND, ALT, LSK, NTRN, COTI, RLC, SAGA, WAXP, MAGIC, C98, DGB, IOST, LISTA, ONG, CTSI, PROM, USDP, DUSK, SUN, XNO, JOE, SLP, HIVE, AI, IQ, REQ, CELR, SNT, CVC, OMNI, POWR, GNS, NMR, HIGH, CYBER, XVS, KNC, PHA, PUNDIX, ACE, AUCTION, SPELL, USTC, STRAX, PYR, DENT, PORTAL, XAI, CTK, SYS, MOVR, WIN, STPT, STEEM, SYN, CREAM, RIF, BAKE, HOOK, HFT, PHB, BLZ, LEVER, LQTY, REZ, STG, BNT, DODO, SCRT, DAR, FRONT, MTL, QI, OXT, XVG, ARDR, NFP, ALICE, AGLD, MBOX, LOOM, ACA, RARE, AEUR, ALPHA, RAD, ARPA, HIFI, ARK, NKN, BADGER, RDNT, WRX, TNSR, MLN, GHST, GTC, OGN, TKO, MAV, MDX, ORN, STMX, QKC, LTO, CLV, BETA, REI, FORTH, TLM, ATA, ERN, REN, MBL, KMD, PERP, DIA, PDA, AERGO, FIS, COS, FLM, LIT, FUN, DATA, POLS, ALCX, WAN, CTXC, COMBO, NULS, AVA, DEGO, LINA, BEL, IDEX, PSG, UNFI, FIDA, VIC, MDT, BSW, REEF, FARM, LOKA, QUICK, UTK, KEY, VOXEL, BIFI, ADX, KP3R, UFT, AKRO, WING, IRIS, BURGER, BAR, PIVX, CHESS, FIO, ALPACA, AMB, CITY, GFT, VIDT, FIRO, VITE, VGX, AST, ALPINE, HARD, OG, BOND, TROY, SANTOS, VIB, ASR, OOKI, DF, JUV, LAZIO, PORTO, OAX, PROS, IDRT, FOR, ATM, ACM, EPX, DOCK, CVP";
		String exceptFull = "USDT, FDUSD, USDC, TUSD, USDP, DAI, IDRT";
		String b10Symbol = "AVAX";
		String b1Symbol = "SEI";
		Map<String, Integer> mainMap = new HashMap<>();

		List<String> binanceFullList = new ArrayList<>();
		binanceFullList.addAll(Arrays.asList(binanceFull.replaceAll(" ", "").split(",")));
		System.out.println("binance size: " + binanceFullList.size());
		System.out.println(binanceFullList);

		List<String> except = new ArrayList<>();
		except.addAll(Arrays.asList(exceptFull.replaceAll(" ", "").split(",")));

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
}
