package demo.teaching.match.pojo.bo;

public class AdditionFactor3ElementBO {

	private Double factor1;
	private Double factor2;
	private Double factor3;

	public Double getFactor3() {
		return factor3;
	}

	public void setFactor3(Double factor3) {
		this.factor3 = factor3;
	}

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
		return "AdditionFactor3ElementBO [factor1=" + factor1 + ", factor2=" + factor2 + ", factor3=" + factor3 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((factor1 == null) ? 0 : factor1.hashCode());
		result = prime * result + ((factor2 == null) ? 0 : factor2.hashCode());
		result = prime * result + ((factor3 == null) ? 0 : factor3.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdditionFactor3ElementBO other = (AdditionFactor3ElementBO) obj;
		if (factor1 == null) {
			if (other.factor1 != null)
				return false;
		} else if (!factor1.equals(other.factor1))
			return false;
		if (factor2 == null) {
			if (other.factor2 != null)
				return false;
		} else if (!factor2.equals(other.factor2))
			return false;
		if (factor3 == null) {
			if (other.factor3 != null)
				return false;
		} else if (!factor3.equals(other.factor3))
			return false;
		return true;
	}

	public String getEquationString() {
		return this.getFactor1().intValue() + " + " + this.getFactor2().intValue() + " + " + this.getFactor3().intValue() + " = ";
	}
}
