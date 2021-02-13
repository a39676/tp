package demo.teaching.match.pojo.bo;

public class AdditionFactorBO {

	private Double factor1;
	private Double factor2;

	public Double getFactor1() {
		return factor1;
	}

	public void setFactor1(Double factor1) {
		this.factor1 = factor1;
	}

	public Double getFactor2() {
		return factor2;
	}

	public void setFactor2(Double factor2) {
		this.factor2 = factor2;
	}

	@Override
	public String toString() {
		return "AdditionFactorBO [factor1=" + factor1 + ", factor2=" + factor2 + "]";
	}

}
