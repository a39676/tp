package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tmp32 {

	public static void main(String[] args) throws IOException {

		List<String> l = new ArrayList<>();
		l.add("a");
		l.add("b");
		Map<String, List<String>> m = new HashMap<>();
		m.put("k", l);
		l = m.get("k");
		l.add("c");
		System.out.println(m);
	}
}
