package taobao.pojo.type;

public enum TaobaoSkuNameBuildingType {
	
	BEAD_CREATING("散珠_创建", 1),
	BEAD_EDITING("散珠_编辑", 2),
	CREAM_GLUE_EDITING("奶油胶_编辑", 3),
	;
	
	private String name;
	private Integer code;
	
	TaobaoSkuNameBuildingType(String name, Integer code) {
		this.name = name;
		this.code = code;
	}
	

	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}

	public static TaobaoSkuNameBuildingType getType(String typeName) {
		for(TaobaoSkuNameBuildingType t : TaobaoSkuNameBuildingType.values()) {
			if(t.getName().equals(typeName)) {
				return t;
			}
		}
		return null;
	}
	
	public static TaobaoSkuNameBuildingType getType(Integer typeCode) {
		for(TaobaoSkuNameBuildingType t : TaobaoSkuNameBuildingType.values()) {
			if(t.getCode().equals(typeCode)) {
				return t;
			}
		}
		return null;
	}
}
