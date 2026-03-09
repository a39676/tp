package demo.job51.pojo.dto;

public class Job51OutputInfoDTO {

	private String companyName;
	private String postion;
	private String username;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPostion() {
		return postion;
	}

	public void setPostion(String postion) {
		this.postion = postion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Job51OutputInfoDTO [companyName=" + companyName + ", postion=" + postion + ", username=" + username
				+ "]";
	}

	public String toString2() {
		return companyName + System.lineSeparator() + postion + System.lineSeparator() + username + System.lineSeparator();
	}
}
