package demo.job51.pojo.dto;

import java.util.List;

public class Job51MallApiListDataElementDTO {

	private String jobSeekerActiveTypeCode; // 4:24h内活跃, 5:3日内
	private String jobSeekerActiveType; // 1小时前活跃;
	private Boolean isHiChat;
	private Boolean isRead;
	private Job51MallApiListDataElementBaseInfoDTO baseInfo;
	private Job51MallApiListDataElementResumeExpectInfoDTO resumeExpectInfo;
	private List<Job51MallApiListDataElementWorkDTO> work;
	private String labelUserTag; // 急切求职

	public String getJobSeekerActiveTypeCode() {
		return jobSeekerActiveTypeCode;
	}

	public void setJobSeekerActiveTypeCode(String jobSeekerActiveTypeCode) {
		this.jobSeekerActiveTypeCode = jobSeekerActiveTypeCode;
	}

	public String getJobSeekerActiveType() {
		return jobSeekerActiveType;
	}

	public void setJobSeekerActiveType(String jobSeekerActiveType) {
		this.jobSeekerActiveType = jobSeekerActiveType;
	}

	public Boolean getIsHiChat() {
		return isHiChat;
	}

	public void setIsHiChat(Boolean isHiChat) {
		this.isHiChat = isHiChat;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Job51MallApiListDataElementBaseInfoDTO getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(Job51MallApiListDataElementBaseInfoDTO baseInfo) {
		this.baseInfo = baseInfo;
	}

	public Job51MallApiListDataElementResumeExpectInfoDTO getResumeExpectInfo() {
		return resumeExpectInfo;
	}

	public void setResumeExpectInfo(Job51MallApiListDataElementResumeExpectInfoDTO resumeExpectInfo) {
		this.resumeExpectInfo = resumeExpectInfo;
	}

	public List<Job51MallApiListDataElementWorkDTO> getWork() {
		return work;
	}

	public void setWork(List<Job51MallApiListDataElementWorkDTO> work) {
		this.work = work;
	}

	public String getLabelUserTag() {
		return labelUserTag;
	}

	public void setLabelUserTag(String labelUserTag) {
		this.labelUserTag = labelUserTag;
	}

	@Override
	public String toString() {
		return "Job51MallApiListDataElementDTO [jobSeekerActiveTypeCode=" + jobSeekerActiveTypeCode
				+ ", jobSeekerActiveType=" + jobSeekerActiveType + ", isHiChat=" + isHiChat + ", isRead=" + isRead
				+ ", baseInfo=" + baseInfo + ", resumeExpectInfo=" + resumeExpectInfo + ", work=" + work
				+ ", labelUserTag=" + labelUserTag + "]";
	}

}
