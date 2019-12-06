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
				2130706433L,
				1123635125L,
				1123635127L,
				1123635123L,
				236493731L,
				236565664L,
				460553218L,
				575242250L,
				597500057L,
				598198785L,
				621375824L,
				676177774L,
				720112242L,
				757106638L,
				758593551L,
				856670033L,
				860717471L,
				861657649L,
				878219233L,
				918037142L,
				1019160141L,
				1033351854L,
				1040453326L,
				1123631499L,
				1123631501L,
				1123631503L,
				1123631755L,
				1123631757L,
				1123631759L,
				1123632473L,
				1123632475L,
				1123632477L,
				1123632665L,
				1123632667L,
				1123632669L,
				1123634168L,
				1123634170L,
				1123634172L,
				1123634987L,
				1161474617L,
				1426771907L,
				1757432342L,
				1757432346L,
				1757432358L,
				1757693647L,
				1971855448L,
				2028945704L,
				2028945740L,
				2032224942L,
				2261855141L,
				2313318684L,
				2328199492L,
				2331442437L,
				2426970874L,
				2655351885L,
				2655379748L,
				2680906029L,
				2809330287L,
				3030637571L,
				3030637635L,
				3050701760L,
				3507576882L,
				3507576938L,
				3507576994L,
				3507577146L,
				3512832203L
				
		};
		
		NumericUtilCustom n = new NumericUtilCustom();
		for(Long l : checkList) {
			System.out.println(hu.sendGet(baseUrl + n.longToIp(l) + "?access_key=" + tmpKey, null));
		}
		
	}
}
