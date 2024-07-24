package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tmp32 {

	public static void main(String[] args) {
		String str1 = "AEVOUSDT,ALICEUSDT,TLMUSDT,SAGAUSDT,ARKMUSDT,MAGICUSDT,1000SHIBUSDT,DYMUSDT,XLMUSDT,NFPUSDT,TRBUSDT,ACHUSDT,PHBUSDT,CFXUSDT,LINAUSDT,COMBOUSDT,HBARUSDT,VETUSDT,DUSKUSDT,AXLUSDT,ACEUSDT,BICOUSDT,PORTALUSDT,MANTAUSDT,EGLDUSDT,QNTUSDT,ETHFIUSDT,SNXUSDT,PENDLEUSDT,PIXELUSDT,TNSRUSDT,SSVUSDT,FETUSDT,YFIUSDT,AMBUSDT,AIUSDT,LDOUSDT,LQTYUSDT,RDNTUSDT,LPTUSDT";
		String str2 = "AEVOUSDT,ALICEUSDT,TLMUSDT,SAGAUSDT,ARKMUSDT,MAGICUSDT,1000SHIBUSDT,DYMUSDT,XLMUSDT,NFPUSDT,TRBUSDT,ACHUSDT,PHBUSDT,CFXUSDT,LINAUSDT,COMBOUSDT,HBARUSDT,VETUSDT,DUSKUSDT,AXLUSDT,ACEUSDT,BICOUSDT,PORTALUSDT,MANTAUSDT,EGLDUSDT,QNTUSDT,ETHFIUSDT,SNXUSDT,PENDLEUSDT,PIXELUSDT,TNSRUSDT,SSVUSDT,FETUSDT,YFIUSDT,AMBUSDT,AIUSDT,LDOUSDT,LQTYUSDT,RDNTUSDT";
		List<String> l1 = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l1.addAll(Arrays.asList(str1.split(",")));
		l2.addAll(Arrays.asList(str2.split(",")));

		List<String> l3 = new ArrayList<>();
		for (int i = 0; i < l1.size(); i++) {
			if (!l3.contains(l1.get(i))) {
				l3.add(l1.get(i));
			}
		}

		for (int i = 0; i < l2.size(); i++) {
			if (!l3.contains(l2.get(i))) {
				l3.add(l2.get(i));
			}
		}

		System.out.println(l3);
	}
}
