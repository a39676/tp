package tmpTrans;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordReportDTO extends ReportCommonDTO {

	private XWPFDocument wordDocument;


	public XWPFDocument getWordDocument() {
		return wordDocument;
	}

	public void setWordDocument(XWPFDocument document) {
		this.wordDocument = document;
	}

	@Override
	public String toString() {
		return "WordReportDTO [document=" + wordDocument + ", getContent()=" + getContent() + ", getImgPath()="
				+ getImgPath() + ", getOutputReportPath()=" + getOutputReportPath() + ", getReportFileName()="
				+ getReportFileName() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
