package demo.cloudinary.pojo.type;

public enum ChannelType {
	
	c1("1", 1),
	c9("9", 9),
	c10("10", 10),
	zoo("zoo", 16),
	;
	
	private String name;
	private Integer code;
	
	ChannelType(String name, Integer code) {
		this.name = name;
		this.code = code;
	}
	

	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}

	public static ChannelType getType(String typeName) {
		for(ChannelType t : ChannelType.values()) {
			if(t.getName().equals(typeName)) {
				return t;
			}
		}
		return null;
	}
	
	public static ChannelType getType(Integer typeCode) {
		for(ChannelType t : ChannelType.values()) {
			if(t.getCode().equals(typeCode)) {
				return t;
			}
		}
		return null;
	}
}
