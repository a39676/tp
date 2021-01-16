package test;

import java.util.Calendar;

import com.github.heqiao2010.lunar.LunarCalendar;

public class ChineseLunarTeset {

	
	public static void main(String[] args) {
		Calendar today = Calendar.getInstance();
		LunarCalendar lunar = LunarCalendar.solar2Lunar(today);
		System.out.println(today.getTime() + " <====> " + lunar.getFullLunarName());
		System.out.println(lunar.getLunarMonth() + ", " + lunar.getDayOfLunarMonth());
		
	}
	
}
