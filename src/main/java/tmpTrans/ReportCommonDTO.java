package tmpTrans;

public abstract class ReportCommonDTO {

	private String content;

	private String imgPath;

	private String outputReportPath;

	private String reportFileName;


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getOutputReportPath() {
		return outputReportPath;
	}

	public void setOutputReportPath(String outputReportPath) {
		this.outputReportPath = outputReportPath;
	}

	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	@Override
	public String toString() {
		return "ReportCommonDTO [content=" + content + ", imgPath=" + imgPath + ", outputReportPath=" + outputReportPath
				+ ", reportFileName=" + reportFileName + "]";
	}

}
