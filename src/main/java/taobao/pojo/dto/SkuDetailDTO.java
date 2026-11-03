package taobao.pojo.dto;

import java.time.LocalDateTime;

public class SkuDetailDTO {

	private String skuID;
	private LocalDateTime fileDateTime;

	public String getSkuID() {
		return skuID;
	}

	public void setSkuID(String skuID) {
		this.skuID = skuID;
	}

	public LocalDateTime getFileDateTime() {
		return fileDateTime;
	}

	public void setFileDateTime(LocalDateTime fileDateTime) {
		this.fileDateTime = fileDateTime;
	}

	@Override
	public String toString() {
		return "SkuDetailDTO [skuID=" + skuID + ", fileDateTime=" + fileDateTime + "]";
	}

}
