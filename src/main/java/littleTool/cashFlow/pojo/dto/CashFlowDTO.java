package littleTool.cashFlow.pojo.dto;

import java.math.BigDecimal;

import auxiliaryCommon.pojo.type.TimeUnitType;

public class CashFlowDTO {

	private String flowName;
	private TimeUnitType timeUnit;
	private BigDecimal amount;

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public TimeUnitType getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnitType timeUnit) {
		this.timeUnit = timeUnit;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "CashFlowDTO [flowName=" + flowName + ", timeUnit=" + timeUnit + ", amount=" + amount + "]";
	}

}
