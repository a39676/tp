package job_test.east.urlTest.xkMall.common.entity.type.app.product;

/**
 * 产品额度类型
 */
public enum ProductLimitType {

	/** 大额分期 */
	LARGE_LIMIT(1, "大额分期", 3000, 10000),
	/** 小额借款 */
	SMALL_LIMIT(2, "小额借款", 1000, 3000),
	//
	;

	private int value;

	private String name;

	private int min;

	private int max;

	private ProductLimitType(int value, String name, int min, int max) {
		this.value = value;
		this.name = name;
		this.min = min;
		this.max = max;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public static ProductLimitType getType(int value) {
		for (ProductLimitType type : values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
	
}
