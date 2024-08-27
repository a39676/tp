package demo.finance.cryptoCoin.pojo.bo;

public class CryptoCoinPriceRatioDTO implements Comparable<CryptoCoinPriceRatioDTO> {

	private String symbol;
	private Double ratio;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	@Override
	public int compareTo(CryptoCoinPriceRatioDTO o) {
		if (o.getRatio() > this.ratio) {
			return 1;
		} else if (o.getRatio() == this.ratio) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return "CryptoCoinPriceRatioDTO [symbol=" + symbol + ", ratio=" + ratio + "]";
	}

}
