package test.aiArt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import ai.aiArt.pojo.dto.TextToImageDTO;
import ai.aiArt.pojo.type.AiArtSamplerType;
import ai.automatic1111.pojo.dto.AiArtAutomaticModelDTO;
import ai.automatic1111.pojo.result.AiArtAutomaticGetModelResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import toolPack.httpHandel.HttpUtil;

@Scope("singleton")
@Service
public class AiArtAutomaticUtilDev extends CommonService {

	@Autowired
	private HttpUtil httpUtil;
	@Autowired
	private SystemOptionService optionService;

	private static boolean isGenerating = false;

	public JSONObject sendTxtToImgRequestInDev(TextToImageDTO dto) {
		JSONObject responseJson = new JSONObject();
		JSONArray ja = new JSONArray();
		byte[] fileContent = null;
		try {
			fileContent = FileUtils.readFileToByteArray(new File("d:/tmp/00.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		ja.add(encodedString);
		responseJson.put("images", ja);
		return responseJson;
	}

	public JSONObject sendTxtToImgRequest(TextToImageDTO dto) {
		isGenerating = true;
		JSONObject json = JSONObject.fromObject(
				"{\"enable_hr\": false,\"denoising_strength\": 0,\"firstphase_width\": 0,\"firstphase_height\": 0,\"hr_scale\": 2,\"hr_upscaler\": \"string\",\"hr_second_pass_steps\": 0,\"hr_resize_x\": 0,\"hr_resize_y\": 0,\"prompt\": \"\",\"styles\": [],\"seed\": -1,\"subseed\": -1,\"subseed_strength\": 0,\"seed_resize_from_h\": -1,\"seed_resize_from_w\": -1,\"sampler_name\": \"Euler a\",\"batch_size\": 1,\"n_iter\": 1,\"steps\": 20,\"cfg_scale\": 7,\"width\": 512,\"height\": 512,\"restore_faces\": false,\"tiling\": false,\"negative_prompt\": \"\",\"eta\": 0,\"s_churn\": 0,\"s_tmax\": 0,\"s_tmin\": 0,\"s_noise\": 1,\"override_settings\": {},\"override_settings_restore_afterwards\": true,\"script_args\": [],}");
		json.put("prompt", dto.getPrompts());
		json.put("negative_prompt", dto.getNegativePrompts());
		AiArtSamplerType samplerType = AiArtSamplerType.getType(dto.getSampler());
		if (samplerType == null) {
			samplerType = AiArtSamplerType.DPM_2M_Karras;
		}
		json.put("sampler_name", samplerType.getName());
		json.put("width", dto.getWidth());
		json.put("height", dto.getHeight());
		json.put("cfg_scale", dto.getCfgScale());
		json.put("steps", dto.getSteps());
		json.put("batch_size", dto.getBatchSize());
		json.put("seed", dto.getSeed());

		if (dto.getEnableHr()) {
			json.put("enable_hr", true);
			json.put("denoising_strength", dto.getDenoisingStrength());
			json.put("hr_upscaler", dto.getHrUpscalerName());

			json.put("firstphase_width", dto.getWidth());
			json.put("firstphase_height", dto.getHeight());

			json.put("hr_second_pass_steps", dto.getHrSecondPassSteps());

			if (dto.getHrResizeX() != null && dto.getHrResizeY() != null) {
				json.put("hr_resize_x", dto.getHrResizeX());
				json.put("hr_resize_y", dto.getHrResizeY());
			} else {
				json.put("hr_scale", dto.getHrScale());
			}
		}

		log.error("parameter json: " + json.toString());

		changeModelIfNecessary(dto.getModelName());

		String uri = "/sdapi/v1/txt2img";
		String url = optionService.getAutomaticUrl() + uri;
		String response = null;
		try {
			response = httpUtil.sendPostRestful(url, json.toString());
		} catch (Exception e) {
			e.printStackTrace();
//			sendTelegramMsg("AI art automatic generate image error: " + e.getLocalizedMessage());
			log.error("AI art text to image request error: " + e.getLocalizedMessage());
		}
		isGenerating = false;
		JSONObject responseJson = JSONObject.fromObject(response);
		return responseJson;
	}

	public AiArtAutomaticGetModelResult getModelList() {
		String uri = "/sdapi/v1/sd-models";
		String url = optionService.getAutomaticUrl() + uri;
		String response = null;
		AiArtAutomaticGetModelResult r = new AiArtAutomaticGetModelResult();
		try {
			response = httpUtil.sendGet(url);
//			[{"title":"chilled_re-generic.safetensors [7282703625]","model_name":"chilled_re-generic","hash":"7282703625","sha256":"7282703625bb79973e5e0d7493d4569f208e3fc3d26ddb0dbaf455ce171c2be2","filename":"/content/stable-diffusion-webui/models/Stable-diffusion/chilled_re-generic.safetensors","config":null}]
			JSONArray responseJsonArray = JSONArray.fromObject(response);
			r.setModelList(new ArrayList<>());
			String subJsonStr = null;
			AiArtAutomaticModelDTO subDTO = null;
			for (int i = 0; i < responseJsonArray.size(); i++) {
				subJsonStr = responseJsonArray.getString(i);
				subDTO = buildObjFromJsonCustomization(subJsonStr, AiArtAutomaticModelDTO.class);
				r.getModelList().add(subDTO);
			}
			r.setIsSuccess();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Get model list request error: " + e.getLocalizedMessage());
		}

		return r;
	}

	public JSONObject getAutomaticeOption() {
		String uri = "/sdapi/v1/options";
		String url = optionService.getAutomaticUrl() + uri;
		String response = null;
		try {
			response = httpUtil.sendGet(url);
		} catch (Exception e) {
			e.printStackTrace();
//			sendTelegramMsg("AI art get option error: " + e.getLocalizedMessage());
			log.error("AI art get option error: " + e.getLocalizedMessage());
		}
		JSONObject responseJson = JSONObject.fromObject(response);

		return responseJson;
	}

	/*
	 * can set nsfw filter here
	 */
	public void setAutomaticeOption(JSONObject json) {
		String uri = "/sdapi/v1/options";
		String url = optionService.getAutomaticUrl() + uri;
		@SuppressWarnings("unused")
		String response = null;
		try {
			response = httpUtil.sendPostRestful(url, json.toString());
		} catch (Exception e) {
			e.printStackTrace();
//			sendTelegramMsg("AI art set option error: " + e.getLocalizedMessage());
			log.error("AI art set option error: " + e.getLocalizedMessage());
		}
	}

	public void changeModelIfNecessary(String modelName) {
		JSONObject option = getAutomaticeOption();
		if (option.getString("sd_model_checkpoint").equals(modelName)) {
			return;
		}

		List<SdModelDTO> modelList = getAllModel();
		boolean containFlag = false;
		for (int i = 0; i < modelList.size() && !containFlag; i++) {
			containFlag = modelList.get(i).getModel_name().equals(modelName);
		}
		if (!containFlag) {
			return;
		}

		option.put("sd_model_checkpoint", modelName);
		setAutomaticeOption(option);
	}

	public List<SdModelDTO> getAllModel() {
		String uri = "/sdapi/v1/sd-models";
		String url = optionService.getAutomaticUrl() + uri;
		String response = null;
		try {
			response = httpUtil.sendGet(url);
		} catch (Exception e) {
			e.printStackTrace();
//			sendTelegramMsg("AI art get model list error: " + e.getLocalizedMessage());
			log.error("AI art get model list error: " + e.getLocalizedMessage());
		}
		JSONArray responseJsonArray = JSONArray.fromObject(response);

		List<SdModelDTO> modelList = new ArrayList<>();
		for (int i = 0; i < responseJsonArray.size(); i++) {
			SdModelDTO dto = new SdModelDTO();
			JSONObject json = responseJsonArray.getJSONObject(i);
			dto.setConfig(json.getString("config"));
			dto.setFilename(json.getString("filename"));
			dto.setHash(json.getString("hash"));
			dto.setModel_name(json.getString("model_name"));
			dto.setSha256(json.getString("sha256"));
			dto.setTitle(json.getString("title"));
			modelList.add(dto);
		}
		return modelList;
	}

	public boolean getIsGenerating() {
		return isGenerating;
	}
}
