package tmpTrans;

import net.sf.json.JSONArray;

public class JsonReportDTO extends ReportCommonDTO {

	private JSONArray contentJsonAry;

	public JSONArray getContentJsonAry() {
		return contentJsonAry;
	}

	public void setContentJsonAry(JSONArray contentJsonAry) {
		this.contentJsonAry = contentJsonAry;
	}

	@Override
	public String toString() {
		return "JsonReportDTO [contentJsonAry=" + contentJsonAry + ", getContentJsonAry()=" + getContentJsonAry()
				+ ", getContent()=" + getContent() + ", getImgPath()=" + getImgPath() + ", getOutputReportPath()="
				+ getOutputReportPath() + ", getReportFileName()=" + getReportFileName() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
