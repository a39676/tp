package taobao.pojo.dto;

import java.math.BigDecimal;

public class SkuDetailDTO {

	private String productName;
	private String productId;
	private String skuId;
	private String skuName;
	private String skuImgUrl;
	private BigDecimal amount;
	private Integer storeNumber;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getSkuImgUrl() {
		return skuImgUrl;
	}

	public void setSkuImgUrl(String skuImgUrl) {
		this.skuImgUrl = skuImgUrl;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(Integer storeNumber) {
		this.storeNumber = storeNumber;
	}

	@Override
	public String toString() {
		return "SkuDetailDTO [productName=" + productName + ", productId=" + productId + ", skuId=" + skuId
				+ ", skuName=" + skuName + ", skuImgUrl=" + skuImgUrl + ", amount=" + amount + ", storeNumber="
				+ storeNumber + "]";
	}

}
