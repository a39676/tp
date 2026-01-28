package demo.pojo.type;

public enum ImageBlurJobType {
	
	XY_LOGO("xyLogo", 1),
	XY_BOTTOM_RIGHT("xyBottomRight", 2),
	
	;
	
	private String name;
	private Integer code;
	
	ImageBlurJobType(String name, Integer code) {
		this.name = name;
		this.code = code;
	}
	

	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}

	public static ImageBlurJobType getType(String typeName) {
		for(ImageBlurJobType t : ImageBlurJobType.values()) {
			if(t.getName().equals(typeName)) {
				return t;
			}
		}
		return null;
	}
	
	public static ImageBlurJobType getType(Integer typeCode) {
		for(ImageBlurJobType t : ImageBlurJobType.values()) {
			if(t.getCode().equals(typeCode)) {
				return t;
			}
		}
		return null;
	}
}
