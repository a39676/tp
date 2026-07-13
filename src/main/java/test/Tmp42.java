package test;

import com.github.houbb.opencc4j.util.ZhConverterUtil;

public class Tmp42 {

	public static void main(String[] args) {
		String original = "4-10mm玻璃仿玉石珠透色琉璃圆珠手链项链DIY饰品配件串珠隔珠";
		System.out.println(original);
		String result = ZhConverterUtil.toTraditional(original);
		System.out.println(result);
	}
}
