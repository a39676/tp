package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AppearCounting {

	private static String filePath = "d:/tmp/tmp";

	public static void main(String[] args) {

		Path path = Paths.get(filePath);
		Map<String, Integer> map = new HashMap<>();
		
		try {
			BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			String currentLine = null;
			while ((currentLine = reader.readLine()) != null) {
				if (map.containsKey(currentLine)) {
					map.put(currentLine, map.get(currentLine) + 1);
				} else {
					map.put(currentLine, 1);
				}
			}
			reader.close();
		} catch (IOException ex) {
			System.out.println("File reading error");
			ex.printStackTrace(); // handle an exception here
		}
		
		if(map.isEmpty()) {
			System.out.println("Empty file");
			return;
		}
		
		Set<String> result = new HashSet<>();
		
		Integer maxCounting = 0;
		
		for(Entry<String, Integer> es : map.entrySet()) {
			if(es.getValue() > maxCounting) {
				maxCounting = es.getValue();
				result.clear();
				result.add(es.getKey());
			} else if(es.getValue().equals(maxCounting)) {
				result.add(es.getKey());
			}
		}
		
		System.out.println(String.format("Appear counting: %s, most frequently words: %s", maxCounting, result));
	}

}
