package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class testRegex {
	
	public static void main(String[] args) {
		
		String tmpStr = "{\"phone\":\"13800138002\",\"name\":\"测试姓名02\"}";
		String pattern = "(?:\\{\"phone\":\")(\\d{1,20})(?:\",\"name\":\"\\S{1,20}\"\\})";
//		String pattern = "\\{\"phone\":\"(\\d{1,10})\".*\\}";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(tmpStr);
		
		System.out.println(tmpStr);
		if(m.find()) {
			System.out.println(m.group(0));
			System.out.println(m.group(1));
		} else {
			System.out.println("not found");
		}
		
	}

}
