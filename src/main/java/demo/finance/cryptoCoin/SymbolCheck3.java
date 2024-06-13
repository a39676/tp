package demo.finance.cryptoCoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolCheck3 {

	public static void main(String[] args) {
		String binanceFull = "BTC, ETH, BNB, SOL, USDC, XRP, DOGE, ADA, SHIB, AVAX, WBTC, DOT, LINK, TRX, BCH, NEAR, MATIC, UNI, LTC, PEPE, ICP, DAI, ETC, RNDR, APT, HBAR, WBETH, STX, WIF, ATOM, FIL, ARB, XLM, FLOKI, GRT, TAO, AR, FDUSD, OP, SUI, VET, MKR, INJ, FTM, BONK, NOT, THETA, RUNE, LDO, TIA, JASMY, FET, PYTH, STRK, JUP, SEI, AAVE, ALGO, ENA, GALA, FLOW, BEAMX, CHZ, AXS, ORDI, AGIX, BTTC, W, WLD, QNT, EGLD, NEO, SAND, RONIN, MINA, PENDLE, XTZ, XEC, BOME, SNX, EOS, CFX, MANA, GNO, CAKE, AEVO, NEXO, APE, CKB, ROSE, ENS, KLAY, DEXE, IOTA, KAVA, OM, LPT, 1000SATS, LUNC, AXL, BLUR, WOO, TFUEL, PEOPLE, IOTX, MANTA, MEME, CRV, 1INCH, OSMO, ASTR, TWT, ETHFI, ARKM, FTT, SUPER, OCEAN, DYDX, COMP, GLM, LUNA, TUSD, HOT, GMT, RAY, DYM, ZRX, JTO, ANKR, SSV, METIS, RPL, ENJ, ZIL, CELO, RSR, PAXG, GAL, ZEC, SKL, ID, BICO, ALT, RVN, GMX, BNX, ILV, PIXEL, POLYX, YGG, QTUM, FXS, LRC, SFP, BAT, DASH, ELF, MASK, T, FLUX, BB, DCR, GAS, HIGH, ONE, VANRY, SUSHI, JST, AMP, API3, GLMR, CHR, ONT, KSM, TRB, UMA, CVX, SAGA, ACH, BAND, EDU, XAI, VTHO, RLC, AUDIO, YFI, KDA, BAL, AI, MAGIC, WAXP, TRU, OMNI, COTI, ICX, NTRN, C98, SXP, PORTAL, REZ, STORJ, PROM, POND, CTSI, CYBER, XEM, SLP, LSK, DGB, NMR, CELR, DUSK, IOST, RIF, XVS, JOE, XNO, WAVES, TNSR, IQ, ONG, USTC, PYR, POWR, HIVE, SYS, DENT, FRONT, ALICE, CVC, HOOK, REQ, CREAM, USDP, ARK, AUCTION, STRAX, MOVR, NFP, PUNDIX, PHB, PHA, SNT, SYN, STG, DODO, SPELL, ACE, AGLD, HFT, KNC, SUN, STEEM, RDNT, WIN, LQTY, CTK, MBOX, LEVER, MAV, ALPHA, GTC, ARPA, GNS, ACA, OXT, DAR, SCRT, BNT, BLZ, MTL, HIFI, QI, BAKE, LOOM, XVG, STPT, NKN, BADGER, ATA, OGN, RAD, ARDR, GHST, RARE, TLM, LTO, WRX, QKC, ERN, REN, WNXM, TKO, PERP, STMX, MBL, CTXC, REI, POLS, LINA, LIT, BEL, DATA, NULS, COMBO, FORTH, OMG, AEUR, ORN, FLM, CLV, MLN, DIA, KMD, MDX, FUN, ALCX, PDA, COS, FIS, BETA, DEGO, WAN, REEF, LOKA, IDEX, AERGO, FIDA, VIC, FARM, BSW, IRIS, MDT, VOXEL, UTK, BURGER, KEY, AVA, QUICK, UNFI, SANTOS, UFT, AMB, KP3R, CHESS, BIFI, PIVX, AKRO, ADX, PSG, VIDT, VITE, BAR, WING, ALPACA, DOCK, FIO, BOND, AST, HARD, FIRO, LAZIO, GFT, CITY, PORTO, OG, ALPINE, TROY, VGX, VIB, DF, OAX, FOR, JUV, ATM, ACM, EPX, CVP, PROS, IDRT, OOKI, ASR, EUR";
		String exceptFull = "USDT, FDUSD, USDC, TUSD, USDD, CRVUSD, USDP, PYUSD, OUSD, DAI";
		Map<String, Integer> mainMap = new HashMap<>();

		List<String> binanceFullList = new ArrayList<>();
		binanceFullList.addAll(Arrays.asList(binanceFull.replaceAll(" ", "").split(",")));
		System.out.println("binance size: " + binanceFullList.size());
		System.out.println(binanceFullList);

		List<String> except = new ArrayList<>();
		except.addAll(Arrays.asList(exceptFull.replaceAll(" ", "").split(",")));

		for (int i = 0; i < binanceFullList.size(); i++) {
			String tmpSymbol = binanceFullList.get(i);
			if (except.contains(tmpSymbol)) {
				continue;
			}
			mainMap.put(tmpSymbol, i / 50);
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
