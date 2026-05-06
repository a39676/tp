package test;

import com.github.houbb.opencc4j.util.ZhConverterUtil;

public class Tmp42 {

	public static void main(String[] args) {
		String original = "仿兔毛球绒球20mm穿孔手工DIY配件手机链包挂车挂服装饰品辅料";
		System.out.println(original);
		String result = ZhConverterUtil.toTraditional(original);
		System.out.println(result);
	}
}
