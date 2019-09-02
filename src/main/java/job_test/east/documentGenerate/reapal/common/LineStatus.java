package job_test.east.documentGenerate.reapal.common;

public class LineStatus {
	
	private Integer status = 0;
	
	public Integer notStart = 0;
	public Integer commentStart = 1;
	public Integer commentEnd = 2;
	public Integer dataStart = 3;
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getNotStart() {
		return notStart;
	}
	public void setNotStart(Integer notStart) {
		this.notStart = notStart;
	}
	public Integer getCommentStart() {
		return commentStart;
	}
	public void setCommentStart(Integer commentStart) {
		this.commentStart = commentStart;
	}
	public Integer getCommentEnd() {
		return commentEnd;
	}
	public void setCommentEnd(Integer commentEnd) {
		this.commentEnd = commentEnd;
	}
	public Integer getDataStart() {
		return dataStart;
	}
	public void setDataStart(Integer dataStart) {
		this.dataStart = dataStart;
	}
	
}
