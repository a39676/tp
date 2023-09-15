package test;

import java.time.LocalDateTime;

public class KeepLive {

	public static void main(String[] args) {
		while(true) {
			try {
				Thread.sleep(5000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(LocalDateTime.now());
		}
	}
}
