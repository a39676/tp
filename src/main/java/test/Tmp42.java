package test;

public class Tmp42 {

	public static void main(String[] args) {
//		String original = "4-10mm玻璃仿玉石珠透色琉璃圆珠手链项链DIY饰品配件串珠隔珠";
//		System.out.println(original);
//		String result = ZhConverterUtil.toTraditional(original);
//		System.out.println(result);
		Integer start = 453;
		Integer end = 556;
		for (int i = start; i <= end; i = i + 2) {
			System.out.print(i);
			if (i + 2 < end) {
				System.out.print(",");
			}
		}
		System.out.println();
		for (int i = start + 1; i <= end; i = i + 2) {
			System.out.print(i);
			if (i + 1 < end) {
				System.out.print(",");
			}
		}
	}
}
