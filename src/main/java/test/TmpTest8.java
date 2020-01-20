package test;

import java.math.BigDecimal;

public class TmpTest8 {
	
	public static void main(String[] args) throws Exception {
		
		BigDecimal c = new BigDecimal("0.01");
		BigDecimal total = BigDecimal.ZERO;
		
		for(int i = 1; i < 31; i++) {
			c = c.multiply(new BigDecimal(2));
			total = total.add(c);
			
			System.out.println(i + " : " + c + " : " + total);
		}
	}

}
