package test;

import java.util.List;

public class TmpTest16 {

	public static void main(String[] args) {
		var s = "Str";
		System.out.println(s.getClass());
		var b = List.of("1", 2);
		System.out.println(b.getClass());
		
		String str = """
				some
				string 
				block
				""";
		System.out.println(str);
	}
}
