package test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TmpTest7 {
	
	public static void main(String[] args) {
		LocalDateTime n = LocalDateTime.now();
		LocalDateTime n2 = LocalDateTime.now().plusDays(2);
		
		long d = ChronoUnit.DAYS.between(n2, n);
		
		System.out.println(d);
		
	}

}
