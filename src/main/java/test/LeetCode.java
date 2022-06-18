package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode {

	public static void main(String[] args) {
		LeetCode t = new LeetCode();
		int[] result = t.twoSum(new int[] { 3, 2, 4 }, 6);
//		System.out.println(result);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]);
		}
	}

	public int[] runningSum(int[] nums) {
		int[] result = new int[nums.length];
		int tmp = 0;
		for (int i = 0; i < nums.length; i++) {
			tmp = tmp + nums[i];
			result[i] = tmp;
		}
		return result;
	}

	public int[] buildArray(int[] nums) {
		int[] result = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			result[i] = nums[nums[i]];
		}
		return result;
	}

	public int[] twoSum(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] { i, j };
				}
			}
		}
		return null;
	}

	public int[] kWeakestRows(int[][] mat, int k) {
		int tmpSummary = 0;
		int tmpIndex = 0;
		int lineLength = mat[0].length;
		List<Integer> summary = new ArrayList<>();

		for (int i = 0; i < mat.length; i++) {
			int[] line = mat[i];
			tmpSummary = 0;
			for (int j = 0; j < lineLength; j++) {
				tmpSummary = tmpSummary + line[j];
			}
			summary.add(tmpSummary);
		}

		List<Integer> resultList = new ArrayList<>();
		while (resultList.size() < k) {
			tmpSummary = 0;
			tmpIndex = 0;
			for (int i = 0; i < summary.size(); i++) {
				if (tmpSummary < summary.get(i)) {
					tmpSummary = summary.get(i);
					tmpIndex = i;
				}
			}
			resultList.add(tmpIndex);
			summary.set(tmpIndex, 0);
		}

		int[] result = new int[k];
		for (int i = 0; i < resultList.size(); i++) {
			result[i] = resultList.get(i);
		}

		return result;
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
