package test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import toolPack.httpHandel.HttpUtil;
import tool_package.SnowFlake;

public class Tmp20 {

	private static SnowFlake snowFlake = new SnowFlake();
	private static String savingFolder = "d:/aiArt/txtToImg";
	private static String mainUrl = "http://6s3qsjoz5ajigsolccvicb5ejdsgtoyh4o6rudihw5i2kp5jfixq.remote.moe";

	private static String prompts = "ultra realistic 8k cg, picture-perfect face, flawless, clean, masterpiece, professional artwork, famous artwork, cinematic lighting, cinematic bloom, perfect face, beautiful face, beautiful eyes, ((perfect female body, narrow waist)), black hair, gorgeous queen, royal, divine, goddess, godlike, (royal palace), fantasy, dreamlike, unreal, science fiction, huge breasts, beautiful clothes, lace, lace trim, lace-trimmed legwear, nsfw, breasts out, absurdly long hair, very long hair, (rich:1.4), prestige, luxury, jewelry, diamond, gold, pearl, gem, sapphire, ruby, emerald, intricate detail, delicate pattern, sexy, charming, alluring, seductive, erotic, enchanting, hair ornament, necklace, earrings, bracelet, armlet, <lora:koreanDollLikeness_v15:0.8>";
//	 professional modelshoot style photo elegant woman, classy bedroom, kneeling on ground, wet, water on body, wet body, drops on face realistic, sharp focus, 8k high definition, insanely detailed, intricate, elegant, sunrays through window, curtains, wing eyeliner, eyeliner wings, hyper-realistic, ultra detailed, full body, long hair, detailed lingerie, stockings, showing tongue, open mouth, (kissing:1.15), horny, 2girls, (Kpop idol), ((lesbians)), doggystyle, perfect hand, <lora:koreanDollLikeness_v15:0.45>, <lora:taiwanDollLikeness_v10:0.15>
	private static String negativePrompts = "(worst quality, low quality:1.3), simple background, logo, watermark, text";
//	 paintings, sketches, (worst quality:2), (low quality:2), (normal quality:2), lowres, normal quality, ((monochrome)), ((grayscale)), skin spots, acnes, skin blemishes, age spot, glans,

	private static Integer width = 300;
	private static Integer height = 600;
	private static Integer steps = 30;
	private static String sampler = "DPM++ 2M Karras";
	private static Integer cfgScale = 7;
	private static Integer batchSize = 1;
	private static Long seed = Long.parseLong("-1");

	private static int round = 1;
	private static int maxExcepitonCount = 5;
	private static int excepitonCount = 0;

	static {
		String proxyHost = "127.0.0.1";
		String proxyPort = "10809";

		System.setProperty("http.proxyHost", proxyHost);
		System.setProperty("http.proxyPort", proxyPort);

		System.setProperty("https.proxyHost", proxyHost);
		System.setProperty("https.proxyPort", proxyPort);
	}

	public void sendTxtToImgRequest() throws IOException, URISyntaxException {
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
		System.out.println("Send request, url: " + url);
		System.out.println("JSON: " + json);
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

		File outputFolder = new File(savingFolder);
		if (!outputFolder.exists()) {
			outputFolder.mkdirs();
		}
		File outputfile = new File(savingFolder + "/" + snowFlake.getNextId() + ".jpg");
		ImageIO.write(img, "jpg", outputfile);
	}

	public static void main(String[] args) throws IOException {
		Tmp20 t = new Tmp20();

		for (int i = 0; i < round && excepitonCount < maxExcepitonCount; i++) {
			System.out.println("Round: " + i + " of " + round);
			try {
				t.sendTxtToImgRequest();
			} catch (Exception e) {
				e.printStackTrace();
				excepitonCount += 1;
			}
		}
	}
}
