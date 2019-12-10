package demo.cloudinary.service;

import java.io.File;
import java.io.IOException;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import net.sf.json.JSONObject;
import toolPack.cloudinary.pojo.constant.CloudinaryConstant;
import toolPack.cloudinary.pojo.result.CloudinaryUploadResult;

public class CloudinaryAPIUploader {
	
	private long maxSize = 10485760L;
	
	public CloudinaryUploadResult uploadCore(File f) {
		
		CloudinaryUploadResult result = null;

		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				CloudinaryConstant.cloudName, "dy20bdekn",
				CloudinaryConstant.apiKey, "915927123645857",
				CloudinaryConstant.apiSecret, "k8483Du7gcUp49UrKKnm9RgXsiYf"));
		
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
