package demo.pojo.type;

public enum ImageBlurJobType {
	
	XY_LOGO_TOP_LEFT("xyLogoTopLeft", 1),
	NAME_BOTTOM_RIGHT("nameBottomRight", 2),
	KUO_CHENG_BOTTOM_RIGHT("kuoChengBottomRight", 3),
	KUO_CHENG_LOGO_TOP_LEFT("kuoChengLogoTopLeft", 4),
	XY_LOGO_BOTTOM_LEFT("xyBottomLeft", 5),
	XY_LOGO_TOP_RIGHT("xyLogoTopRight", 6),
	XY_LOGO_MIDDLE_RIGHT("xyLogoMiddleRight", 7),
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
