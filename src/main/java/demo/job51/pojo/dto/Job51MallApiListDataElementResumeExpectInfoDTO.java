package demo.job51.pojo.dto;

public class Job51MallApiListDataElementResumeExpectInfoDTO {

	private String expectJobArea; // "040000",
	private String expectJobAreaStr; // "深圳",
	private String expectWorkFunc; // "6739",
	private String expectWorkFuncStr; // "半导体设备工程师",
	private String expectSalary; // "11000-17000/月",
	private String expectMinSalary; // "11000",
	private String expectMaxSalary; // "17000"

	public String getExpectJobArea() {
		return expectJobArea;
	}

	public void setExpectJobArea(String expectJobArea) {
		this.expectJobArea = expectJobArea;
	}

	public String getExpectJobAreaStr() {
		return expectJobAreaStr;
	}

	public void setExpectJobAreaStr(String expectJobAreaStr) {
		this.expectJobAreaStr = expectJobAreaStr;
	}

	public String getExpectWorkFunc() {
		return expectWorkFunc;
	}

	public void setExpectWorkFunc(String expectWorkFunc) {
		this.expectWorkFunc = expectWorkFunc;
	}

	public String getExpectWorkFuncStr() {
		return expectWorkFuncStr;
	}

	public void setExpectWorkFuncStr(String expectWorkFuncStr) {
		this.expectWorkFuncStr = expectWorkFuncStr;
	}

	public String getExpectSalary() {
		return expectSalary;
	}

	public void setExpectSalary(String expectSalary) {
		this.expectSalary = expectSalary;
	}

	public String getExpectMinSalary() {
		return expectMinSalary;
	}

	public void setExpectMinSalary(String expectMinSalary) {
		this.expectMinSalary = expectMinSalary;
	}

	public String getExpectMaxSalary() {
		return expectMaxSalary;
	}

	public void setExpectMaxSalary(String expectMaxSalary) {
		this.expectMaxSalary = expectMaxSalary;
	}

	@Override
	public String toString() {
		return "Job51MallApiListDataElementResumeExpectInfoDTO [expectJobArea=" + expectJobArea + ", expectJobAreaStr="
				+ expectJobAreaStr + ", expectWorkFunc=" + expectWorkFunc + ", expectWorkFuncStr=" + expectWorkFuncStr
				+ ", expectSalary=" + expectSalary + ", expectMinSalary=" + expectMinSalary + ", expectMaxSalary="
				+ expectMaxSalary + "]";
	}

}
