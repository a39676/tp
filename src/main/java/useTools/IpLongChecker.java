package useTools;

import httpHandel.HttpUtil;
import numericHandel.NumericUtilCustom;

public class IpLongChecker {

	private static String tmpKey = "ee656d8923ebc4a32df5993754dbd019";
	private static String baseUrl = "http://api.ipstack.com/";
	
	
	public static void main(String[] args) throws Exception {
		HttpUtil hu = new HttpUtil();
//		List<Long> checkList = new ArrayList<Long>();
//		checkList.add(1971855321L);
//		checkList.add(875940174L);
//		checkList.add(1297624868L);
//		checkList.add(2102924730L);
//		checkList.add(3683237453L);
//		checkList.add(1757432365L);
//		checkList.add(1709333958L);
//		checkList.add(3683237363L);
//		checkList.add(1903631460L);
//		checkList.add(1032648259L);
//		checkList.add(1033351857L);
//		checkList.add(1902988336L);
//		checkList.add(630262335L);
//		checkList.add(602288484L);
//		checkList.add(2028369185L);
		Long[] checkList = new Long[] {
//				236470937L
//				84413130L
//				720112220L
//				3420324120L,
//				3028867419L
//				2342679685L,
//				1503919497L,
//				1191634952L,
//				1019160141L,
//				1914409688L,
//				3638218309L
//				221899107L
//				1903632064L, 2366148242L, 1680562796L
				1297624843L
		};
		
		
		for(Long l : checkList) {
			System.out.println(hu.sendGet(baseUrl + NumericUtilCustom.longToIp(l) + "?access_key=" + tmpKey, null));
		}
	}
}
