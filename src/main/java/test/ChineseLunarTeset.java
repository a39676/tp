package test;

import java.time.LocalDateTime;
import java.util.Calendar;

import com.github.heqiao2010.lunar.LunarCalendar;

public class ChineseLunarTeset {

	
	public static void main(String[] args) {
		testLunarCalendar();
	}
	
	public static void testLunarCalendar() {
		Calendar calendar = Calendar.getInstance();
		LunarCalendar lunar = LunarCalendar.solar2Lunar(calendar);
		System.out.println(calendar.getTime() + " <====> " + lunar.getFullLunarName());
		System.out.println(lunar.getLunarMonth() + ", " + lunar.getDayOfLunarMonth());
		System.out.println(lunar.getLeapMonth());
		LocalDateTime now = LocalDateTime.now();
		
		int counting = 0;
		while(counting < 9) {
			System.out.println(now);
			calendar = localDateTimeToCalendar(now);
			lunar = LunarCalendar.solar2Lunar(calendar);
			System.out.println(lunar.getLeapMonth());
			System.out.println(lunar.isLeapYear(lunar.getLunarYear()));
			now = now.plusYears(1);
			counting++;
		}
	}

	public static Calendar localDateTimeToCalendar(LocalDateTime dateTime) {
		Calendar cal = Calendar.getInstance();
		cal.set(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth());
		return cal;
	}

}
