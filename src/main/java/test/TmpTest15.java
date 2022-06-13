package test;

import java.io.IOException;

public class TmpTest15 {

	public static void main(String[] args) throws IOException {

		String str = "A、B、D";
//		String str = "错误";
//		String str = "A";

		System.out.println(str.matches("^.*[A-Z].*$"));
		String[] arr = str.split("、");
		for (String c : arr) {
			System.out.println(c);
		}

		str = "A";
		arr = str.split("、");
		for (String c : arr) {
			System.out.println(c);
			System.out.println((int) c.charAt(0));
		}
		
		str = "1、两个空压机故障时，若压力表主风管压力小于7bar，则运行至终点站复位两个A车的3QF04（空压机断路器）、2QF06（空压机启动控制断路器）";
		int i = str.indexOf('、');
		System.out.println(str.substring(i + 1, str.length()));
	}
}
