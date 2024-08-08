package demo.finance.cryptoCoin.pojo.type;

public enum CryptoCoinTagType {

	BTC("BTC",1),
	ETH("ETH",2),
	SOL("SOL",3),
	NFT("NFT",4),
	EXCHANGE("exchange",5),
	FUN("FUN",6),
	;

	private String name;
	private Integer code;

	CryptoCoinTagType(String name, Integer code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}

	public static CryptoCoinTagType getType(String typeName) {
		for (CryptoCoinTagType t : CryptoCoinTagType.values()) {
			if (t.getName().equals(typeName)) {
				return t;
			}
		}
		return null;
	}

	public static CryptoCoinTagType getType(Integer code) {
		for (CryptoCoinTagType t : CryptoCoinTagType.values()) {
			if (t.getCode().equals(code)) {
				return t;
			}
		}
		return null;
	}

}
