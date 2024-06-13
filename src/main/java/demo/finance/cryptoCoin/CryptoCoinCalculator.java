package demo.finance.cryptoCoin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import demo.finance.cryptoCoin.pojo.bo.CryptoCoinCalculatorBO;

public class CryptoCoinCalculator {

	private static List<Double> gearList = null;
	private static List<CryptoCoinCalculatorBO> tradDataList = new ArrayList<>();
	private static Double feeRate = 0.002;

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

	public void setGearList(Double stepLong, int stepCount, int direction) {
		gearList = new ArrayList<>();
		if (direction >= 0) {
			for (int i = 0; i < stepCount; i++) {
				gearList.add(stepLong * (1 + i));
			}
		}
		if (direction <= 0) {
			for (int i = 0; i < stepCount; i++) {
				gearList.add(0 - stepLong * (1 + i));
			}
		}
	}

	public void printResult(CryptoCoinCalculatorBO bo) {
		System.out.println(bo.getPrice() + ", " + bo.getCounting());
		Collections.sort(gearList);
		for (Double gear : gearList) {
			System.out.println(BigDecimal.valueOf(bo.getPrice() * (1 + gear / 100)).setScale(10, RoundingMode.HALF_UP)
					+ ", " + gear);
		}
	}

	public void addTradHistory(Double price, Double counting) {
		CryptoCoinCalculatorBO bo = new CryptoCoinCalculatorBO();
		if(counting > 0) {
			bo.setCounting(counting * (1 - feeRate));
		} else {
			bo.setCounting(counting);
		}
		bo.setPrice(price);
		tradDataList.add(bo);
	}
	
	public void addTradHistory(Double price, Integer counting) {
		addTradHistory(price, counting.doubleValue());
	}
	public void addTradHistory(Integer price, Double counting) {
		addTradHistory(price.doubleValue(), counting);
	}
	public void addTradHistory(Integer price, Integer counting) {
		addTradHistory(price.doubleValue(), counting.doubleValue());
	}

	public void cleanTradHistory() {
		tradDataList.clear();
	}

	public static void main(String[] args) {
		CryptoCoinCalculator c = new CryptoCoinCalculator();
		c.setGearList(0.5D, 5, 1);

		CryptoCoinCalculatorBO resultBO = c.summary(tradDataList);

		c.cleanTradHistory();
		c.addTradHistory(0.5001, 1658);
		c.addTradHistory(0.502129, 2280);
		resultBO = c.summary(tradDataList);
		c.printResult(resultBO);
		
	}
}
