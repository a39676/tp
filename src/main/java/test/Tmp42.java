package test;

import java.util.ArrayList;
import java.util.List;

import com.github.houbb.opencc4j.util.ZhConverterUtil;

public class Tmp42 {

	public static void main(String[] args) {
		String original = "8mm猫眼树脂珠圆珠串珠子手工DIY饰品手链手机链散珠跨境热卖";
		String result = ZhConverterUtil.toTraditional(original);
		System.out.println(result);
		List<String> l = new ArrayList<>();
		l.add("a");
		l.add("b");
		l.add("c");
		l.add("d");
		System.out.println(l.subList(l.size() - 2, l.size()));
	}
}
