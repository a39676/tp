package demo.finance.cryptoCoin.pojo.dto;

public class GateKeysDTO {

	private String key;
	private String sec;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSec() {
		return sec;
	}

	public void setSec(String sec) {
		this.sec = sec;
	}

	@Override
	public String toString() {
		return "GateKeysDTO [key=" + key + ", sec=" + sec + "]";
	}

}
