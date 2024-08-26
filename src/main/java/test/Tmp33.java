package test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public class Tmp33 {

	public static void main(String[] args) throws IOException, URISyntaxException {
		BigDecimal b = new BigDecimal("3.29501966E+11");
		System.out.println(b);
		System.out.println(b.divide(new BigDecimal(1000000)));
		b = new BigDecimal("106025248");
		System.out.println(b);
		System.out.println(b.divide(new BigDecimal(1000000)));
		
	}
}
