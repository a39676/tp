package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import toolPack.httpHandel.HttpUtil;

public class Tmp32 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String mainUrl = "https://www.binance.com/bapi/apex/v1/public/apex/marketing/tardingPair/detail?symbol=";
		String crossStr = "BTC,ETH,BNB,SOL,XRP,DOGE,ADA,AVAX,TRX,LINK,DOT,BCH,UNI,NEAR,MATIC,LTC,ICP,ETC,APT,RNDR,HBAR,XLM,ATOM,ARB,FIL,STX,IMX,MKR,SUI,VET,GRT,TAO,OP,LDO,INJ,WIF,AR,FTM,THETA,NOT,RUNE,JASMY,FET,AAVE,TIA,PYTH,ALGO,SEI,JUP,FLOW,STRK,QNT,ENA,PENDLE,GALA,BEAMX,AXS,EOS,ORDI,ENS,NEO,EGLD,XTZ,RONIN,SAND,WLD,ZK,CHZ,SNX,MINA,MANA,ROSE,W,CFX,BOME,LPT,CAKE,KLAY,IOTA,OM,APE,1INCH,KAVA,CKB,CRV,TWT,BLUR,AXL,IOTX,WOO,COMP,PEOPLE,SSV,ASTR,ETHFI,MEME,AEVO,BNX,GLM,IO,ARKM,KSM,1000SATS,GMT,DYDX,SUPER,SFP,MANTA,ZEC,ZIL,HOT,CELO,ANKR,ILV,ZRX,POLYX,SKL,JTO,METIS,RSR,DYM,BICO,BAT,RVN,ENJ,DASH,FXS,QTUM,GMX,GAL,MASK,TRB,ID,GAS,LRC,YGG,T,PIXEL,ALT,ONE,UMA,SUSHI,CHR,VANRY,API3,YFI,ONT,ACH,NTRN,BAL,EDU,BB,TRU,BAND,MAGIC,RLC,SXP,LSK,ICX,COTI,STORJ,C98,IOST,WAXP,CTSI,ONG,XAI,AI,NMR,SAGA,CELR,DUSK,CYBER,JOE,ACE,REZ,OMNI,BLZ,AUCTION,POWR,XVS,USTC,KNC,PORTAL,MOVR,SPELL,HIGH,ARK,ZEN,DENT,RIF,BAKE,HOOK,PHB,STEEM,MTL,HFT,NFP,BNT,STG,AGLD,LQTY,ALICE,FRONT,MAV,TNSR,ALPHA,RDNT,LEVER,ARPA,OXT,XVG,DAR,LOOM,GTC,BADGER,HIFI,NKN,OGN,ATA,TLM,COMBO,PERP,REN,STMX,LIT,FLM,UNFI,BEL,LINA,REEF,KEY,AMB,BOND,LISTA,ZRO";
		List<String> cross = new ArrayList<>();
		cross.addAll(Arrays.asList(crossStr.split(",")));
		HttpUtil h = new HttpUtil();
		for (String symbol : cross) {
			String url = mainUrl + symbol;
			try {
				String response = h.sendGet(url);
				JSONObject json = JSONObject.fromObject(response);
				JSONArray data = json.getJSONArray("data");
				JSONObject mainData = data.getJSONObject(0);
//				System.out.println(mainData.getString()); // TODO
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
