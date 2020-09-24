package tmpTrans;

import auxiliaryCommon.pojo.result.CommonResult;

public class ScreenshotSaveResult extends CommonResult {

	private String cloudId;
	private String url;
	private String savingPath;
	private String fileName;

	public String getCloudId() {
		return cloudId;
	}

	public void setCloudId(String cloudId) {
		this.cloudId = cloudId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSavingPath() {
		return savingPath;
	}

	public void setSavingPath(String savingPath) {
		this.savingPath = savingPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "ScreenshotSaveResult [cloudId=" + cloudId + ", url=" + url + ", savingPath=" + savingPath
				+ ", fileName=" + fileName + "]";
	}

}
