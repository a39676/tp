package demo.finance.cryptoCoin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LowFrequencySymbol {

	public static void main(String[] args) {
		String str = "T_USDT,MNT_USDT,ONT_USDT,ANKR_USDT,PYTH_USDT,QNT_USDT,ASR_USDT,ZRX_USDT,XCH_USDT,STSOL_USDT,TRX_USDT,LEVER_USDT,OG_USDT,POOLX_USDT,TWT_USDT,VGX_USDT,ARPA_USDT,QUICK_USDT,ATM_USDT,MANTA_USDT,TRB_USDT,THETA_USDT,SNT_USDT,BICO_USDT,ACM_USDT,AMP_USDT,DEXE_USDT,HXD_USDT,BIFI_USDT,OMG_USDT,VET_USDT,TROY_USDT,BCH_USDT,KLAY_USDT,AEVO_USDT,STG_USDT,ZIL_USDT,SUPER_USDT,DENT_USDT,RPL_USDT,PUNDIX_USDT,ZEN_USDT,BLZ_USDT,ASTR_USDT,SC_USDT,DGB_USDT,RIF_USDT,ONG_USDT,FOR_USDT,FLOW_USDT,ETHFI_USDT,CELO_USDT,SYN_USDT,DYDX_USDT,XVG_USDT,PRO_USDT,DUSK_USDT,TIA_USDT,PROM_USDT,XEC_USDT,NFP_USDT,POLYX_USDT,BAT_USDT,GRT_USDT,SFP_USDT,RDNT_USDT,GNO_USDT,EPX_USDT,DAI_USDT,EGLD_USDT,LOKA_USDT,SKL_USDT,CVC_USDT,ARKM_USDT,COTI_USDT,JUV_USDT,ROSE_USDT,PORTO_USDT,FRAX_USDT,WOO_USDT,LPT_USDT,BLUR_USDT,VTHO_USDT,CHZ_USDT,WBTC_USDT,POND_USDT,SPELL_USDT,ATOM_USDT,UNFI_USDT,NEO_USDT,FTT_USDT,NMR_USDT,HIFI_USDT,UMA_USDT,MOVR_USDT,RLC_USDT,FXS_USDT,FTN_USDT,ENS_USDT,PORTAL_USDT,AXL_USDT,USTC_USDT,POWR_USDT,MOBILE_USDT,SNX_USDT,RSR_USDT,USDP_USDT,ALPINE_USDT,WAXP_USDT,CRV_USDT,YFI_USDT,DASH_USDT,REQ_USDT,HNT_USDT,AI_USDT,OOKI_USDT,SUN_USDT,SYS_USDT,GLM_USDT,CRO_USDT,RAY_USDT,PIXEL_USDT,AAVE_USDT,LDO_USDT,IQ_USDT,JST_USDT,BNX_USDT,ID_USDT,AXS_USDT,TRU_USDT,FTM_USDT,W_USDT,COMP_USDT,BAL_USDT,OSMO_USDT,OCEAN_USDT,IMX_USDT,XVS_USDT,WAVES_USDT,BSV_USDT,GNS_USDT,WEETH_USDT,PEOPLE_USDT,RUNE_USDT,CELR_USDT,SAND_USDT,MAGIC_USDT,ILV_USDT,APE_USDT,FIDA_USDT,ADA_USDT,METIS_USDT,MTL_USDT,APT_USDT,TUSD_USDT,KAVA_USDT,PROS_USDT,TFUEL_USDT,GFT_USDT,API3_USDT,PYR_USDT,LRC_USDT,IOTA_USDT,XTZ_USDT,ELF_USDT,DMAIL_USDT,AUDIO_USDT,MANA_USDT,WGRT_USDT,SLP_USDT,DYM_USDT,KNC_USDT,GAS_USDT,LUNA_USDT,HOT_USDT,CYBER_USDT,MINA_USDT,ETHW_USDT,JOE_USDT,CORGIAI_USDT,KSM_USDT,XEM_USDT,QTUM_USDT,FDUSD_USDT,NTRN_USDT,LSK_USDT,RVN_USDT,NOS_USDT,QI_USDT,IOST_USDT,RAD_USDT,IOTX_USDT,CVX_USDT,JTO_USDT,LUNC_USDT,NEXO_USDT,PERP_USDT,ONE_USDT,WNXM_USDT,FLUX_USDT,ICX_USDT,STX_USDT,MAV_USDT,LINK_USDT,AMB_USDT,BTT_USDT,ARK_USDT,DCR_USDT,GMX_USDT,XAI_USDT,1INCH_USDT,CHR_USDT,GAL_USDT,GLMR_USDT,GTC_USDT,STRAX_USDT,SUSHI_USDT,MKR_USDT,MASK_USDT,KEY_USDT,OMNI_USDT,KDA_USDT,VANRY_USDT,ENJ_USDT,SXP_USDT,HIVE_USDT,WIN_USDT,OAX_USDT,ZEC_USDT,MEME_USDT,CAKE_USDT,RNDR_USDT,BEAMX_USDT,";
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
