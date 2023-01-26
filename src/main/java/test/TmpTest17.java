package test;

import java.io.IOException;
import java.time.LocalDateTime;

public class TmpTest17 {

	public static void main(String[] args) throws IOException {
		
		LocalDateTime s = LocalDateTime.of(2022, 11, 21, 00, 0).plusDays(75);
		System.out.println(s);
	}
	
}
