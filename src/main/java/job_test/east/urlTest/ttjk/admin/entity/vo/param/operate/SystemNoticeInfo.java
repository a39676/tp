package job_test.east.urlTest.ttjk.admin.entity.vo.param.operate;

/**
 * 系统通知详情
 * 
 * @author tang
 * 
 */
public class SystemNoticeInfo {

	private Long id;
	/** 排序编号 */
	private Integer noticeIndex;
	/** 是否启用 */
	private Boolean online;
	/** 通知标题 */
	private String title;
	/** 链接 */
	private String url;
	/** 系统通知类型 */
	private Integer noticeSrcType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNoticeIndex() {
		return noticeIndex;
	}

	public void setNoticeIndex(Integer noticeIndex) {
		this.noticeIndex = noticeIndex;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getNoticeSrcType() {
		return noticeSrcType;
	}

	public void setNoticeSrcType(Integer noticeSrcType) {
		this.noticeSrcType = noticeSrcType;
	}

}
