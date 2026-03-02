package demo.job51.pojo.dto;

public class Job51MallApiListDataElementWorkDTO {

	private String startTime; // "2024.02",
	private String endTime; // "至今",
	private String workingYears; // "2年1个月",
	private String company; // "东莞市竞沃电子科技",
	private String position; // "机械结构工程师",
	private String workType; // "0",
	private String workTypeStr; // "全职",
	private String workFunc; // "0561",
	private String workFuncStr; // "机械结构工程师",
	private String industryTag; // "机械/设备/重工"

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getWorkingYears() {
		return workingYears;
	}

	public void setWorkingYears(String workingYears) {
		this.workingYears = workingYears;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getWorkTypeStr() {
		return workTypeStr;
	}

	public void setWorkTypeStr(String workTypeStr) {
		this.workTypeStr = workTypeStr;
	}

	public String getWorkFunc() {
		return workFunc;
	}

	public void setWorkFunc(String workFunc) {
		this.workFunc = workFunc;
	}

	public String getWorkFuncStr() {
		return workFuncStr;
	}

	public void setWorkFuncStr(String workFuncStr) {
		this.workFuncStr = workFuncStr;
	}

	public String getIndustryTag() {
		return industryTag;
	}

	public void setIndustryTag(String industryTag) {
		this.industryTag = industryTag;
	}

	@Override
	public String toString() {
		return "Job51MallApiListDataElementWorkDTO [startTime=" + startTime + ", endTime=" + endTime + ", workingYears="
				+ workingYears + ", company=" + company + ", position=" + position + ", workType=" + workType
				+ ", workTypeStr=" + workTypeStr + ", workFunc=" + workFunc + ", workFuncStr=" + workFuncStr
				+ ", industryTag=" + industryTag + "]";
	}

}
