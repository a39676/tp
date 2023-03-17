package building.wechatPay_fromSDK;

import net.sf.json.JSONObject;
import toolPack.ioHandle.FileUtilCustom;

public class WechatPayOption {
	/** 商户号 */
	private String merchantId = "";
	/** 商户API私钥路径 */
	private String privateKey = "";
	/** 商户证书序列号 */
	private String merchantSerialNumber = "";
	/** 商户APIV3密钥 */
	private String apiV3key = "";
	private String appId = "";

	public void loadOption() {
		FileUtilCustom f = new FileUtilCustom();
		String filePathStr = "d:/tmp/tmp.json";
		String content = f.getStringFromFile(filePathStr);
		JSONObject json = JSONObject.fromObject(content);
		merchantId = json.getString("merchantId");
		privateKey = json.getString("privateKey");
		merchantSerialNumber = json.getString("merchantSerialNumber");
		apiV3key = json.getString("apiV3key");
		appId = json.getString("appId");
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getMerchantSerialNumber() {
		return merchantSerialNumber;
	}

	public void setMerchantSerialNumber(String merchantSerialNumber) {
		this.merchantSerialNumber = merchantSerialNumber;
	}

	public String getApiV3key() {
		return apiV3key;
	}

	public void setApiV3key(String apiV3key) {
		this.apiV3key = apiV3key;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

}
