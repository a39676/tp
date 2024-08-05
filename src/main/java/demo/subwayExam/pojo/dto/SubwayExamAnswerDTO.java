package demo.subwayExam.pojo.dto;

import java.util.List;

public class SubwayExamAnswerDTO {

	private String uniqueId;
	private String pumId;
	private String puId;
	private List<SubwayExamSubAnswerDTO> answers;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getPumId() {
		return pumId;
	}

	public void setPumId(String pumId) {
		this.pumId = pumId;
	}

	public String getPuId() {
		return puId;
	}

	public void setPuId(String puId) {
		this.puId = puId;
	}

	public List<SubwayExamSubAnswerDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<SubwayExamSubAnswerDTO> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "SubwayExamAnswerDTO [uniqueId=" + uniqueId + ", pumId=" + pumId + ", puId=" + puId + ", answers="
				+ answers + "]";
	}

}
