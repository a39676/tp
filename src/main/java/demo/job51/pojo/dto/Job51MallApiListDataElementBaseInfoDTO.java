package demo.job51.pojo.dto;

public class Job51MallApiListDataElementBaseInfoDTO {

	private String userName; // "冼先生",
	private String workYearStr; // "2年",
	private String age; // "25",
	private String topDegreeStr; // "本科",

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWorkYearStr() {
		return workYearStr;
	}

	public void setWorkYearStr(String workYearStr) {
		this.workYearStr = workYearStr;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTopDegreeStr() {
		return topDegreeStr;
	}

	public void setTopDegreeStr(String topDegreeStr) {
		this.topDegreeStr = topDegreeStr;
	}

	@Override
	public String toString() {
		return "Job51MallApiListDataElementBaseInfoDTO [userName=" + userName + ", workYearStr=" + workYearStr
				+ ", age=" + age + ", topDegreeStr=" + topDegreeStr + "]";
	}

}
