package test;

import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class TmpTest5 {

	public static void main(String[] args) {
		YearMonth ym1 = YearMonth.now();
		YearMonth ym2 = YearMonth.of(2016, Month.FEBRUARY);
		
		System.out.println(ym1.until(ym2,  ChronoUnit.MONTHS));
	}
}
