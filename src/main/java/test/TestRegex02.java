package test;


public class TestRegex02 {
	
	public static void main(String[] args) {
		String str1 = "15.013%";
		System.out.println(parseToDouble(str1));
		
		String str2 = "15";
		System.out.println(parseToDouble(str2));
	}
	
	
	public static Double parseToDouble (String str) {
		Double outputNum = null;
		String tmpStr = null;
		if (str.contains("%")) {
			tmpStr = str.replaceAll("\\%", "");
			outputNum = Double.parseDouble(tmpStr) / 100;
		} else {
			tmpStr = str;
			outputNum = Double.parseDouble(tmpStr) / 100;
		}
		
		return outputNum;
	}
}
