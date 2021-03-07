package demo.cryptoCoin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import demo.cryptoCoin.pojo.bo.CryptoCoinCalculatorBO;

public class CryptoCoinCalculator {

	private static List<Double> gearList = Arrays.asList(1D, 2D, 1.5D, 0.5, 0.3, -0.3, -0.5, -0.659, -1.8, -2.5, -0.7, -0.789,
			-1D, -2D, -1.5);
	private static List<CryptoCoinCalculatorBO> tradDataList = new ArrayList<>();

	public CryptoCoinCalculatorBO summary(List<CryptoCoinCalculatorBO> boList) {
		CryptoCoinCalculatorBO result = new CryptoCoinCalculatorBO();
		Double totalCounting = 0D;
		Double totalPrice = 0D;
		for (CryptoCoinCalculatorBO bo : boList) {
			totalCounting += bo.getCounting();
			totalPrice += (bo.getPrice() * bo.getCounting());
		}
		result.setCounting(totalCounting);
		result.setPrice(totalPrice / totalCounting);
		return result;
	}

	public void printResult(CryptoCoinCalculatorBO bo) {
		System.out.println(bo.getPrice());
		Collections.sort(gearList);
		for (Double gear : gearList) {
			System.out.println(BigDecimal.valueOf(bo.getPrice() * (1 + gear / 100)).setScale(4, RoundingMode.HALF_UP)
					+ ", " + gear);
		}
	}

	public void addTradHistory(Double price, Double counting) {
		CryptoCoinCalculatorBO bo = new CryptoCoinCalculatorBO();
		bo.setCounting(counting);
		bo.setPrice(price);
		tradDataList.add(bo);
	}

	public static void main(String[] args) {
		CryptoCoinCalculator c = new CryptoCoinCalculator();
		c.addTradHistory(40.0485, 50D);
		c.addTradHistory(40.2103, 30D);
		c.addTradHistory(40.3308, 30D);
		c.addTradHistory(40.5344, 13D);
		c.addTradHistory(40.6871, 15D);
		c.addTradHistory(40.4295, 14.661);
		c.addTradHistory(41.355, 43.9833);

		CryptoCoinCalculatorBO resultBO = c.summary(tradDataList);
		c.printResult(resultBO);
	}
}
