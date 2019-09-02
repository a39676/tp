package demo.image.ocr;

public class NameCard {

	private String name;
	private String job;
	private String phone;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "NameCard [name=" + name + ", job=" + job + ", phone=" + phone + "]";
	}
}
