package test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import toolPack.httpHandel.HttpUtil;
import tool_package.SnowFlake;

public class Tmp20 {

	private static SnowFlake snowFlake = new SnowFlake();
	private static String savingFolder = "d:/tmp/txtToImg";
	private static String mainUrl = " http://lmin53hivnamlwaejpcilcoom7kgil7jcjmu5dmovjmm64wnijmq.remote.moe";

	private static String prompts = "";
	private static String negativePrompts = "";

	private static Integer width = 300;
	private static Integer height = 600;
	private static Integer steps = 35;
	private static String sampler = "DPM++ 2M Karras";
	private static Integer cfgScale = 7;
	private static Integer batchSize = 1;
	private static Long seed = Long.parseLong("-1");

	static {
		String proxyHost = "127.0.0.1";
		String proxyPort = "10809";

		System.setProperty("http.proxyHost", proxyHost);
		System.setProperty("http.proxyPort", proxyPort);

		System.setProperty("https.proxyHost", proxyHost);
		System.setProperty("https.proxyPort", proxyPort);
	}

	public void sendTxtToImgRequest() throws IOException {
		JSONObject json = JSONObject.fromObject(
				"{\"enable_hr\": false,\"denoising_strength\": 0,\"firstphase_width\": 0,\"firstphase_height\": 0,\"hr_scale\": 2,\"hr_upscaler\": \"string\",\"hr_second_pass_steps\": 0,\"hr_resize_x\": 0,\"hr_resize_y\": 0,\"prompt\": \"\",\"styles\": [],\"seed\": -1,\"subseed\": -1,\"subseed_strength\": 0,\"seed_resize_from_h\": -1,\"seed_resize_from_w\": -1,\"sampler_name\": \"Euler a\",\"batch_size\": 1,\"n_iter\": 1,\"steps\": 20,\"cfg_scale\": 7,\"width\": 512,\"height\": 512,\"restore_faces\": false,\"tiling\": false,\"negative_prompt\": \"\",\"eta\": 0,\"s_churn\": 0,\"s_tmax\": 0,\"s_tmin\": 0,\"s_noise\": 1,\"override_settings\": {},\"override_settings_restore_afterwards\": true,\"script_args\": [],}");
		json.put("prompt", prompts);
		json.put("negative_prompt", negativePrompts);
		json.put("sampler_name", sampler);
		if (width != null) {
			json.put("width", width);
		}
		if (height != null) {
			json.put("height", height);
		}
		if (cfgScale != null) {
			json.put("cfg_scale", cfgScale);
		}
		if (steps != null) {
			json.put("steps", steps);
		}
		if (batchSize != null) {
			json.put("batch_size", batchSize);
		}
		if (seed != null) {
			json.put("seed", seed);
		}

		String uri = "/sdapi/v1/txt2img";
		HttpUtil h = new HttpUtil();
		String url = mainUrl + uri;
		System.out.println("Send request");
		String response = h.sendPostRestful(url, json.toString());
		System.out.println("Response receive");
		JSONObject responseJson = JSONObject.fromObject(response);
		JSONArray imageListInBase64 = responseJson.getJSONArray("images");
		System.out.println("Receive " + imageListInBase64.size() + " image(s)");

		for (int i = 0; i < imageListInBase64.size(); i++) {
			try {
				System.out.println("Try to save img: " + i);
				saveImg(imageListInBase64.getString(i));
				System.out.println("Img saved");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void saveImg(String data) throws IOException {
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(data);
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));

		File outputfile = new File(savingFolder + "/" + snowFlake.getNextId() + ".jpg");
		ImageIO.write(img, "jpg", outputfile);
	}

	public static void main(String[] args) throws IOException {
		Tmp20 t = new Tmp20();

		int round = 5;
		for (int i = 0; i < round; i++) {
			System.out.println("Round: " + round);
			t.sendTxtToImgRequest();
		}
	}
}
