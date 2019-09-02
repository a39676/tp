package job_test.east.urlTest.commonAuxiliary;

import net.sf.json.JSONObject;

public class UrlTestDomain {
	
	private String url;
	
	private String paramData = UrlTestConstant.emptyJson;
	
	private String domainName;
	

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParamData() {
		return paramData;
	}

	public void setParamData(String paramData) {
		this.paramData = paramData;
	}
	
	public UrlTestDomain insertKeyValue(String key, String value) {
		JSONObject json = JSONObject.fromObject(this.getParamData());
		json.put(key, value);
		this.setParamData(json.toString());
		return this;
	}

	@Override
	public String toString() {
		return "UrlTestDomain [url=" + url + ", paramData=" + paramData + ", domainName=" + domainName + "]";
	}

}
