package demo.job51.pojo.dto;

import java.time.LocalDate;

public class Job51MallApiListDataElementWorkExtendDTO extends Job51MallApiListDataElementWorkDTO {

	private LocalDate startTimeLocalDate;
	private LocalDate endTimeLocalDate;
	private int workingMonths;

	public LocalDate getStartTimeLocalDate() {
		return startTimeLocalDate;
	}

	public void setStartTimeLocalDate(LocalDate startTimeLocalDate) {
		this.startTimeLocalDate = startTimeLocalDate;
	}

	public LocalDate getEndTimeLocalDate() {
		return endTimeLocalDate;
	}

	public void setEndTimeLocalDate(LocalDate endTimeLocalDate) {
		this.endTimeLocalDate = endTimeLocalDate;
	}

	public int getWorkingMonths() {
		return workingMonths;
	}

	public void setWorkingMonths(int workingMonths) {
		this.workingMonths = workingMonths;
	}

	@Override
	public String toString() {
		return "Job51MallApiListDataElementWorkExtendDTO [startTimeLocalDate=" + startTimeLocalDate
				+ ", endTimeLocalDate=" + endTimeLocalDate + ", workingMonths=" + workingMonths + ", getStartTime()="
				+ getStartTime() + ", getEndTime()=" + getEndTime() + ", getWorkingYears()=" + getWorkingYears()
				+ ", getCompany()=" + getCompany() + ", getPosition()=" + getPosition() + ", getWorkType()="
				+ getWorkType() + ", getWorkTypeStr()=" + getWorkTypeStr() + ", getWorkFunc()=" + getWorkFunc()
				+ ", getWorkFuncStr()=" + getWorkFuncStr() + ", getIndustryTag()=" + getIndustryTag() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
