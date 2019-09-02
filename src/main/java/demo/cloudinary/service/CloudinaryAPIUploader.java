package demo.cloudinary.service;

import java.io.File;
import java.io.IOException;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import demo.cloudinary.pojo.dto.CloudinaryUploadResult;
import net.sf.json.JSONObject;

public class CloudinaryAPIUploader {
	
	private long maxSize = 10485760L;
	
	public CloudinaryUploadResult uploadCore(File f) {
		
		CloudinaryUploadResult result = null;

		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "dy20bdekn",
				  "api_key", "915927123645857",
				  "api_secret", "k8483Du7gcUp49UrKKnm9RgXsiYf"));
		
		if(f.length() > maxSize) {
			System.out.println(f.getName() + " is too large, size: " + f.length());
			result = new CloudinaryUploadResult();
			return result;
		}
		
		JSONObject resultJson = null;
		try {
			resultJson = JSONObject.fromObject(cloudinary.uploader().upload(f, ObjectUtils.emptyMap()));
		} catch (IOException e) {
			e.printStackTrace();
			return result;
		}
		result = buildResult(resultJson);
		return result;
//		return null;
	}
	
	private CloudinaryUploadResult buildResult(JSONObject j) {
		CloudinaryUploadResult r = new CloudinaryUploadResult();
		r.setSecureUrl(j.getString("secure_url"));
		r.setOriginalFilename(j.getString("original_filename"));
		r.setPublicId(j.getString("public_id"));
		r.setSuccess(true);
		return r;
	}
}
