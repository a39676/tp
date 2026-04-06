package test;

import com.github.houbb.opencc4j.util.ZhConverterUtil;

public class Tmp42 {

	public static void main(String[] args) {
		String original = "生命不息，奋斗不止, 公里, 里面, abc, 123, {}[]";
		String result = ZhConverterUtil.toTraditional(original);
		System.out.println(result);
	}
}
