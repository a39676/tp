package demo.image.pojo;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;
import numericHandel.NumericUtilCustom;

public class ImageCache {
	private Long imageId;

	private String imageUrl;

	private String imageName;

	private Long articleId;

	private String remark;

	private Boolean isDownload;

	private Date createTime;

	private Date downloadTime;

	private String md5Mark;

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getIsDownload() {
		return isDownload;
	}

	public void setIsDownload(Boolean isDownload) {
		this.isDownload = isDownload;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDownloadTime() {
		return downloadTime;
	}

	public void setDownloadTime(Date downloadTime) {
		this.downloadTime = downloadTime;
	}

	public String getMd5Mark() {
		return md5Mark;
	}

	public void setMd5Mark(String md5Mark) {
		this.md5Mark = md5Mark;
	}


	@Override
	public String toString() {
		return "ImageCache [imageId=" + imageId + ", imageUrl=" + imageUrl + ", imageName=" + imageName + ", articleId="
				+ articleId + ", remark=" + remark + ", isDownload=" + isDownload + ", createTime=" + createTime
				+ ", downloadTime=" + downloadTime + ", md5Mark=" + md5Mark + "]";
	}

	public ImageCache createImageCacheFromJson(JSONObject json) {
		ImageCache ic = new ImageCache();
		String isDownload;
		if (json.containsKey("create_time")) {
			ic.setCreateTime(new Date(json.getJSONObject("create_time").getLong("time")));
		}
		if (json.containsKey("createTime")) {
			ic.setCreateTime(new Date(json.getJSONObject("createTime").getLong("time")));
		}
		if (json.containsKey("download_time")) {
			ic.setDownloadTime(new Date(json.getJSONObject("download_time").getLong("time")));
		}
		if (json.containsKey("downloadTime")) {
			ic.setDownloadTime(new Date(json.getJSONObject("downloadTime").getLong("time")));
		}
		if (json.containsKey("image_id")) {
			ic.setImageId(Long.parseLong(json.getString("image_id")));
		}
		if (json.containsKey("imageId")) {
			ic.setImageId(Long.parseLong(json.getString("imageId")));
		}
		if (json.containsKey("image_name")) {
			ic.setImageName(json.getString("image_name"));
		}
		if (json.containsKey("imageName")) {
			ic.setImageName(json.getString("imageName"));
		}
		if (json.containsKey("article_id") && NumericUtilCustom.matchInteger(json.getString("article_id"))) {
			ic.setArticleId(Long.parseLong(json.getString("article_id")));
		}
		if (json.containsKey("articleId") && NumericUtilCustom.matchInteger(json.getString("articleId"))) {
			ic.setArticleId(Long.parseLong(json.getString("articleId")));
		}
		if (json.containsKey("image_url")) {
			ic.setImageUrl(json.getString("image_url"));
		}
		if (json.containsKey("imageUrl")) {
			ic.setImageUrl(json.getString("imageUrl"));
		}

		if (json.containsKey("is_download")) {
			isDownload = json.getString("is_download");
			if (isDownload.equals("1") || isDownload.equals("true")) {
				ic.setIsDownload(true);
			}
		} else {
			ic.setIsDownload(false);
		}

		if (json.containsKey("isDownload")) {
			isDownload = json.getString("isDownload");
			if (isDownload.equals("1") || isDownload.equals("true")) {
				ic.setIsDownload(true);
			}
		} else {
			ic.setIsDownload(false);
		}

		if (json.containsKey("remark") && StringUtils.isNotBlank(json.getString("remark"))) {
			ic.setRemark(json.getString("remark"));
		}
		if (json.containsKey("md5_mark") && StringUtils.isNotBlank(json.getString("md5_mark"))) {
			ic.setMd5Mark(json.getString("md5_mark"));
		}
		if (json.containsKey("md5Mark") && StringUtils.isNotBlank(json.getString("md5Mark"))) {
			ic.setMd5Mark(json.getString("md5Mark"));
		}

		return ic;
	}

}