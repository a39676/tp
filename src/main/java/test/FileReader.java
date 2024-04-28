package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {

	public static void main(String[] args) {
		String filePath = "";
		Path path = Paths.get(filePath);
		try {
			BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			String currentLine = null;
			int i = 0;
			while (i < 100) {
				currentLine = reader.readLine();
				System.out.println(i);
				if (i < 30) {
					i++;
					continue;
				} else {
					System.out.println(currentLine);
				}
				i++;
			}
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
