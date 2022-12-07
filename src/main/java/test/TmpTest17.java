package test;

import java.util.HashMap;
import java.util.Map;

public class TmpTest17 {

	public static void main(String[] args) {
		
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		
		map.put(null, 1);
		
		System.out.println(map.get(null));
	}
}
