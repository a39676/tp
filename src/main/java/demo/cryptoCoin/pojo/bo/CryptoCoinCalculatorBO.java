package demo.cryptoCoin.pojo.bo;

public class CryptoCoinCalculatorBO {

	private Double price;
	private Double counting = 0D;

	public Double getCounting() {
		return counting;
	}

	public void setCounting(Double counting) {
		this.counting = counting;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CryptoCoinCalculatorBO [price=" + price + ", counting=" + counting + "]";
	}

}
