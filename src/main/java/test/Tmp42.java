package test;

public class Tmp42 {

	public static void main(String[] args) {
//		String original = "夜光彩色树脂五角星卡通DIY手工小摆件饰品水杯贴冰箱贴材料";
//		System.out.println(original);
//		String result = ZhConverterUtil.toTraditional(original);
//		System.out.println(result);
		Integer start = 1091;
		Integer end = 1148;
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
