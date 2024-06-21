package demo.finance.cryptoCoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolCheck3 {

	public static void main(String[] args) {
		String binanceFull = "BTC, ETH, BNB, SOL, USDC, XRP, DOGE, ADA, AVAX, SHIB, TRX, WBTC, LINK, DOT, BCH, UNI, NEAR, MATIC, LTC, DAI, PEPE, ICP, ETC, WBETH, APT, RNDR, HBAR, XLM, ATOM, ARB, FIL, STX, IMX, FDUSD, MKR, SUI, VET, GRT, TAO, OP, LDO, INJ, WIF, AR, FLOKI, FTM, THETA, NOT, RUNE, BONK, JASMY, FET, AAVE, TIA, PYTH, ALGO, SEI, JUP, FLOW, STRK, QNT, ENA, PENDLE, GALA, BEAMX, AXS, EOS, BTTC, ORDI, GNO, ZRO, ENS, AGIX, NEO, EGLD, XTZ, RONIN, SAND, WLD, ZK, CHZ, NEXO, SNX, MINA, MANA, DEXE, ROSE, XEC, W, CFX, BOME, LPT, CAKE, KLAY, IOTA, OM, APE, 1INCH, RPL, KAVA, LUNC, CKB, FTT, TUSD, TFUEL, CRV, PAXG, TWT, BLUR, AXL, IOTX, WOO, COMP, PEOPLE, RAY, SSV, ASTR, ETHFI, MEME, AEVO, BNX, OSMO, GLM, IO, ARKM, OCEAN, KSM, 1000SATS, GMT, DYDX, SUPER, SFP, LUNA, MANTA, ZEC, ZIL, HOT, CELO, CVX, ANKR, ILV, ZRX, POLYX, SKL, JTO, ELF, METIS, RSR, DYM, BICO, BAT, RVN, ENJ, DASH, FXS, JST, QTUM, GMX, GAL, SC, DCR, MASK, TRB, ID, GAS, LRC, YGG, FLUX, T, PIXEL, ALT, ONE, UMA, SUSHI, CHR, VANRY, AMP, API3, YFI, ONT, VTHO, LISTA, GLMR, ACH, AUDIO, NTRN, BAL, EDU, BB, TRU, KDA, BAND, MAGIC, RLC, SXP, LSK, ICX, PROM, COTI, STORJ, C98, IOST, WAXP, POND, CTSI, DGB, ONG, XAI, USDP, SLP, CREAM, AI, NMR, SAGA, CELR, DUSK, CYBER, JOE, XNO, ACE, REZ, SUN, IQ, OMNI, BLZ, REQ, CVC, AUCTION, POWR, XVS, USTC, HIVE, SNT, KNC, PORTAL, MOVR, PYR, CTK, SPELL, SYN, PHA, HIGH, ARK, ZEN, PUNDIX, SYS, DENT, RIF, BAKE, GNS, HOOK, PHB, STEEM, WIN, STRAX, MTL, HFT, DODO, NFP, BNT, STG, AGLD, LQTY, SCRT, ALICE, FRONT, MAV, STPT, TNSR, ACA, MBOX, ALPHA, RDNT, LEVER, ARPA, OXT, XVG, QI, DAR, LOOM, GHST, RARE, GTC, BADGER, ARDR, HIFI, NKN, OGN, ORN, AEUR, RAD, TKO, POLS, WRX, LTO, ATA, TLM, QKC, ERN, CLV, COMBO, CTXC, REI, PERP, MBL, DATA, KMD, REN, FORTH, DIA, STMX, MLN, LIT, COS, FUN, NULS, ALCX, FIS, FLM, UNFI, WAN, DEGO, AERGO, BEL, PSG, LINA, MDX, FIDA, VIC, IDEX, LOKA, FARM, AVA, BETA, PDA, REEF, BSW, MDT, QUICK, IRIS, KEY, VOXEL, UTK, BURGER, KP3R, UFT, BIFI, ADX, CHESS, PIVX, WING, BAR, AKRO, FIO, AMB, HARD, VITE, DOCK, VIDT, CITY, ALPACA, GFT, AST, SANTOS, FIRO, VIB, ALPINE, BOND, OG, TROY, VGX, DF, LAZIO, JUV, PORTO, OAX, FOR, PROS, ATM, IDRT, ACM, CVP, EPX, OOKI, ASR";
		String exceptFull = "USDT, FDUSD, USDC, TUSD, USDD, CRVUSD, USDP, PYUSD, OUSD, DAI";
		String b10Symbol = "WBTC";
		String b1Symbol = "FLOW";
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
