package demo.finance.cryptoCoin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LowFrequencySymbol {

	public static void main(String[] args) {
		String str = "YFI_USDT,COMP_USDT,DAI_USDT,POND_USDT,HOT_USDT,CHZ_USDT,NTRN_USDT,KNC_USDT,LUNC_USDT,NFP_USDT,CYBER_USDT,BAT_USDT,MINA_USDT,WNXM_USDT,OSMO_USDT,CRV_USDT,SFP_USDT,KSM_USDT,BICO_USDT,IQ_USDT,ETHFI_USDT,ROSE_USDT,ARPA_USDT,DENT_USDT,METIS_USDT,AUDIO_USDT,USTC_USDT,RPL_USDT,SKL_USDT,PORTAL_USDT,SUSHI_USDT,PUNDIX_USDT,NEXO_USDT,RIF_USDT,BEAMX_USDT,DCR_USDT,SLP_USDT,RDNT_USDT,IOTA_USDT,GNO_USDT,COTI_USDT,FTT_USDT,SPELL_USDT,AXS_USDT,DUSK_USDT,WIN_USDT,ZEN_USDT,VTHO_USDT,BCH_USDT,ZEC_USDT,ENS_USDT,AEVO_USDT,IOST_USDT,MAV_USDT,LPT_USDT,PEOPLE_USDT,STX_USDT,RAD_USDT,RUNE_USDT,T_USDT,VANRY_USDT,FLOW_USDT,NEO_USDT,KDA_USDT,JST_USDT,MANTA_USDT,ARK_USDT,DYM_USDT,XEM_USDT,AMP_USDT,API3_USDT,FDUSD_USDT,TUSD_USDT,XAI_USDT,ANKR_USDT,TWT_USDT,CELR_USDT,SAND_USDT,GMX_USDT,ONE_USDT,DASH_USDT,CELO_USDT,UMA_USDT,SUN_USDT,DYDX_USDT,ICX_USDT,KLAY_USDT,KAVA_USDT,ENJ_USDT,RAY_USDT,GLM_USDT,LRC_USDT,ZRX_USDT,PIXEL_USDT,XVG_USDT,FLUX_USDT,SXP_USDT,QNT_USDT,TRB_USDT,SC_USDT,MAGIC_USDT,ZIL_USDT,PYR_USDT,QTUM_USDT,NMR_USDT,MOVR_USDT,TFUEL_USDT,DEXE_USDT,MTL_USDT,VET_USDT,GAL_USDT,MKR_USDT,BLZ_USDT,REQ_USDT,ILV_USDT,APE_USDT,RLC_USDT,PERP_USDT,RSR_USDT,ADA_USDT,STG_USDT,BAL_USDT,IOTX_USDT,FXS_USDT,WOO_USDT,AI_USDT,EGLD_USDT,ATOM_USDT,AAVE_USDT,GNS_USDT,XEC_USDT,CHR_USDT,WAXP_USDT,WAVES_USDT,AXS_USDT,DGB_USDT,OMNI_USDT,1INCH_USDT,ONG_USDT,JOE_USDT,ELF_USDT,RVN_USDT,HIFI_USDT,CAKE_USDT,LSK_USDT,SNX_USDT,MANA_USDT,TRU_USDT,SUPER_USDT,MEME_USDT,IMX_USDT,CVX_USDT,JTO_USDT,XVS_USDT,TRX_USDT,BNX_USDT,GAS_USDT,OMG_USDT,GLMR_USDT,MASK_USDT,WBTC_USDT,PYTH_USDT,USDP_USDT,LDO_USDT,CVC_USDT,XTZ_USDT,ATOM_USDT,GRT_USDT,RNDR_USDT,GFT_USDT,STRAX_USDT,LINK_USDT,KEY_USDT,UNFI_USDT,HIVE_USDT,POWR_USDT,AMB_USDT,OAX_USDT,PROM_USDT,OCEAN_USDT,VGX_USDT,FIDA_USDT,ALPINE_USDT,ACM_USDT,";
		Set<String> ts = new HashSet<>();
		ts.addAll(Arrays.asList(str.split(",")));
		for (String s : ts) {
			System.out.println("\"" + s + "\",");
		}
		System.out.println(ts.size());
		for (String s : ts) {
			System.out.print(s + ",");
		}
	}
}