package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode {

	public static void main(String[] args) {
		LeetCode t = new LeetCode();
		int[] result = t.shuffle(new int[] { 2, 5, 1, 3, 4, 7 }, 3);
		for(int i : result) {
			System.out.print(i);
		}
	}

	public int[] shuffle(int[] nums, int n) {
		int[] result = new int[nums.length];
		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < nums.length; i++) {
			if(i < n) {
				x[i] = nums[i];
			} else {
				y[i - n] = nums[i];
			}
		}
		
		for(int i = 0; i < x.length; i++) {
			result[i * 2] = x[i];
		}
		
		for(int i = 0; i < y.length; i++) {
			result[i * 2 + 1] = y[i];
		}
		return result;
	}

	public int mostWordsFound(String[] sentences) {
		int max = 0;
		int tmpLength;
		for (String sentence : sentences) {
			tmpLength = sentence.split(" ").length;
			if (max < tmpLength) {
				max = tmpLength;
			}
		}
		return max;
	}

	public String defangIPaddr(String address) {
		return address.replaceAll("\\.", "[.]");
	}

	public int finalValueAfterOperations(String[] operations) {
		int x = 0;
		for (String op : operations) {
			if (op.contains("+")) {
				x++;
			} else {
				x--;
			}
		}
		return x;
	}

	public boolean checkTree(TreeNode root) {
		return root.val == root.left.val + root.right.val;
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
