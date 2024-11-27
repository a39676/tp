package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Tmp32 {

	public static void main(String[] args) {

		List<BigDecimal> l = new ArrayList<>();
		l.add(new BigDecimal("1"));
		l.add(new BigDecimal("2"));
		l.add(new BigDecimal("3"));
		
		BigDecimal first = l.get(0);
		first = first.add(BigDecimal.TEN);
		
		System.out.println(l);
	}

}
