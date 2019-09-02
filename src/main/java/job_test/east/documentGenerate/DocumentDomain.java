package job_test.east.documentGenerate;

public class DocumentDomain {

	private String paramName;

	private String paramType;

	private String comment = "";

	private boolean isNecessary;

	private String remark;

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void addComment(String comment) {
		this.comment = this.comment + comment;
	}

	public boolean isNecessary() {
		return isNecessary;
	}

	public void setNecessary(boolean isNecessary) {
		this.isNecessary = isNecessary;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "DocumentDomain [paramName=" + paramName + ", paramType=" + paramType + ", comment=" + comment
				+ ", isNecessary=" + isNecessary + ", remark=" + remark + "]";
	}
	
}
