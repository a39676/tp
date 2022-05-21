package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TmpTest16 {

	public static void main(String[] args) throws Exception {

		Map<Integer, String> map = new HashMap<>();
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		map.put(4, "D");
		map.put(5, "E");

		List<Integer> keyList = Arrays.asList(1, 2, 3);

		Map<Integer, String> newMap = new HashMap<>(map);
		newMap.keySet().retainAll(keyList);

		System.out.println(newMap);
	}
}
