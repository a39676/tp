package test;

import java.util.HashMap;
import java.util.Map;

public class LeetCode {

	public static void main(String[] args) {
		LeetCode t = new LeetCode();
		int result = t.numberOfSteps(18);
		System.out.println(result);
	}

	public int numberOfSteps(int num) {
		int step = 0;
		while (num != 0) {
			if (num % 2 == 0) {
				num = num / 2;
				step++;
			} else {
				num = num - 1;
				step++;
			}
		}

		return step;
	}
	
	public int romanToInt(String s) {
		Map<String, Integer> map = new HashMap<>();
		map.put("I", 1);
		map.put("V", 5);
		map.put("X", 10);
		map.put("L", 50);
		map.put("C", 100);
		map.put("D", 500);
		map.put("M", 1000);

		s = s.toUpperCase();

		int total = 0;
		int currentNum = 0;
		int lastNum = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			currentNum = map.get(String.valueOf(s.charAt(i)));

			if (currentNum < lastNum) {
				total -= currentNum;
			} else {
				total += currentNum;
			}

			lastNum = currentNum;
		}

		return total;
	}
}
