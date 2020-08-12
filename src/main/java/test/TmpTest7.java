package test;

import java.util.concurrent.ThreadLocalRandom;

public class TmpTest7 {

	public static void main(String[] args) throws Exception {
		long minMS = 1;
		long maxMS = 10;
		Long l = null;
		
		int minCount = 0;
		int maxCount = 0;
		for(int i = 0; i < 1000; i++) {
			l = ThreadLocalRandom.current().nextLong(minMS, maxMS);
			if(l == minMS) {
				minCount++;
			} else if (l == maxMS) {
				maxCount++;
			}
		}
		
		System.out.println(minCount);
		System.out.println(maxCount);
	}
}
