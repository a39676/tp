package test;

public class Tmp32 {

	public static void main(String[] args) {
		String str = "reconnect__ORDIUSDT_2024-04-19 04:53:00";
		String prefix = "reconnect_";
		int start = str.indexOf(prefix);
		int end = str.indexOf("2024");
		System.out.println(str.substring(start + prefix.length(), end));
	}
}
