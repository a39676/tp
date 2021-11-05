package test;

import java.time.LocalDateTime;

public class TmpTest12 {

	public static void main(String[] args) {

		LocalDateTime now = LocalDateTime.now();

//		LunarSolarConverter c = new LunarSolarConverter();
		Solar s = new Solar();
		s.solarDay = now.getDayOfMonth();
		s.solarMonth = now.getMonthValue();
		s.solarYear = now.getYear();
		Lunar lunar = LunarSolarConverter.SolarToLunar(s);

		System.out.println(lunar.isleap);
		System.out.println(lunar.lunarYear);
		System.out.println(lunar.lunarMonth);
		System.out.println(lunar.lunarDay);

	}
}
