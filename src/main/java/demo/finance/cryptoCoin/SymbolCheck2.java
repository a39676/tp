package demo.finance.cryptoCoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SymbolCheck2 {

	public static void main(String[] args) {
		String binanceFull = "BTC, ETH, USDT, BNB, SOL, USDC, XRP, DOGE, ADA, SHIB, AVAX, TRX, WBTC, DOT, BCH, LINK, NEAR, MATIC, LTC, ICP, DAI, PEPE, UNI, RNDR, FDUSD, ETC, HBAR, APT, ATOM, FIL, XLM, WIF, IMX, STX, WBETH, AR, GRT, MKR, ARB, VET, OP, TAO, SUI, FLOKI, INJ, THETA, RUNE, FTM, FET, BONK, TIA, LDO, ALGO, JUP, SEI, FLOW, AAVE, GALA, BTTC, QNT, AGIX, BEAMX, ENA, WLD, NEO, EGLD, CHZ, AXS, W, SAND, XEC, JASMY, XTZ, EOS, STRK, MINA, RONIN, SNX, CFX, MANA, ORDI, BOME, GNO, NEXO, APE, KAVA, IOTA, CKB, CAKE, AXL, PENDLE, TFUEL, LPT, FTT, 1000SATS, ROSE, OM, BLUR, OSMO, TUSD, GLM, WOO, CRV, OCEAN, ASTR, JTO, DYDX, ARKM, MEME, TWT, SUPER, DEXE, PAXG, COMP, IOTX, ENS, ANKR, LUNA, GMT, 1INCH, RPL, CELO, RAY, MANTA, SC, ENJ, ETHFI, ZIL, RVN, SFP, ZRX, HOT, DYM, GAL, ELF, ZEC, ID, SKL, QTUM, BNX, SSV, BAT, T, LRC, ALT, DASH, XEM, FXS, JST, GAS, BICO, METIS, POLYX, ILV, MASK, TRB, RSR, UMA, DCR, FLUX, AMP, WAVES, YGG, GMX, PIXEL, ONE, ONT, SUSHI, LSK, KSM, VTHO, CVX, HIGH, YFI, CHR, GLMR, KDA, VANRY, RLC, ICX, WAXP, STORJ, SAGA, ACH, PEOPLE, BAND, DGB, BAL, SXP, CELR, API3, AUDIO, C98, IOST, ONG, PROM, CYBER, IQ, COTI, NTRN, MAGIC, BLZ, XAI, CVC, POWR, JOE, POND, NMR, HIVE, RIF, PUNDIX, CTSI, USDP, SLP, STRAX, SYS, USTC, BB, XVS, XNO, ARK, EDU, AI, OMNI, PORTAL, SNT, SYN, SUN, MOVR, REZ, ZEN, DENT, STEEM, DUSK, PHA, HOOK, MTL, REQ, LEVER, TRU, PYR, HFT, WIN, NFP, PHB, FRONT, GNS, KNC, STPT, DODO, SCRT, MBOX, ARDR, TNSR, ACE, ACA, AUCTION, LOOM, XVG, AEVO, HIFI, OXT, LQTY, STG, BNT, ALPHA, ARPA, GHST, RDNT, RAD, CTK, OMG, RARE, QI, SPELL, MAV, ERN, CREAM, WNXM, MBL, NKN, BADGER, WRX, ATA, QKC, DAR, OGN, ALICE, LTO, BAKE, AGLD, GTC, STMX, REI, CTXC, TKO, POLS, TLM, PERP, AEUR, NULS, FORTH, FUN, MDX, REN, AERGO, DATA, CLV, MLN, BEL, ALCX, COS, KMD, BETA, FLM, DIA, ORN, FIS, WAN, IDEX, LINA, COMBO, DEGO, FARM, LOKA, REEF, PDA, VIC, MDT, IRIS, UTK, LIT, QUICK, VOXEL, FIDA, KP3R, BSW, KEY, SANTOS, AVA, UFT, UNFI, AKRO, PSG, BIFI, ADX, CHESS, BAR, FIO, VIDT, PIVX, WING, AMB, VITE, DOCK, ALPACA, CITY, AST, BURGER, HARD, BOND, LAZIO, FIRO, OG, ALPINE, TROY, DF, PORTO, VIB, GFT, VGX, OAX, JUV, BIDR, ACM, FOR, ATM, EPX, IDRT, PROS, CVP, OOKI, ASR, EUR";
		String customFullList = "BTC, ETH, DOGE, SOL, AI, XRP, GALA, STRK, FIL, ETC, AR, NFP, SANTOS, PYTH, BONK, OP, CFX, 1000SATS, CRV, WLD, ORDI, TIA, SAND, BLUR, ACE, JUP, SHIB, ARB, SLP, BCH, LTC, AEVO, BOME";
		String exceptFull = "USDT, FDUSD, USDC, TUSD, USDD, CRVUSD, USDP, PYUSD, OUSD";
		List<String> mainList = new ArrayList<>();
		List<String> secondList = new ArrayList<>();
		int mainListMaxSize = 60;

		List<String> binanceFullList = new ArrayList<>();
		binanceFullList.addAll(Arrays.asList(binanceFull.replaceAll(" ", "").split(",")));
		System.out.println("binance size: " + binanceFullList.size());
		System.out.println(binanceFullList);

		List<String> custom = new ArrayList<>();
		custom.addAll(Arrays.asList(customFullList.replaceAll(" ", "").split(",")));
		
		List<String> except = new ArrayList<>();
		except.addAll(Arrays.asList(exceptFull.replaceAll(" ", "").split(",")));
		
		mainList.addAll(custom);

		for (int i = 0; i < binanceFullList.size(); i++) {
			if (except.contains(binanceFullList.get(i))) {
				continue;
			}
			if (mainList.size() < mainListMaxSize) {
				mainList.add(binanceFullList.get(i));
			} else {
				secondList.add(binanceFullList.get(i));
			}
		}

		System.out.println(mainList);
		System.out.println(secondList);
	}
}
