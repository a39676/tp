package test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TmpTest17 {

	public static void main(String[] args) throws IOException {
		
		LocalDateTime startTime = LocalDateTime.now().minusMinutes(3);
		long minutes = ChronoUnit.MINUTES.between(startTime, LocalDateTime.now());
		System.out.println(minutes);
	}
	
}
