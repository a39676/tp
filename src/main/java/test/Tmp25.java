package test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

import net.sf.json.JSONObject;
import toolPack.httpHandel.HttpUtil;

public class Tmp25 {

	public static void main(String[] args) throws Exception {
		ImageToImageFromApiDTO dto = new ImageToImageFromApiDTO();

		dto.setApiKey("bVwI2z0+=");
		
		byte[] fileContent = null;
		String encodedString = null;
		
		dto.setImagesInBase64("https://i.ibb.co/Jnc3fTQ/1117084181252411392.png");
//		try {
//			fileContent = FileUtils.readFileToByteArray(new File("d:/tmp/25.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		encodedString = Base64.getEncoder().encodeToString(fileContent);
//		dto.setImagesInBase64(encodedString);

		
		
//		dto.setMaskImageInBase64("https://i.ibb.co/16cWNWj/1116804169299267584.png");
		try {
			fileContent = FileUtils.readFileToByteArray(new File("d:/tmp/mask.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		encodedString = Base64.getEncoder().encodeToString(fileContent);
		dto.setMaskImageInBase64(encodedString);
		
//		System.out.println(dto.getImagesInBase64());
//		System.out.println(dto.getMaskImageInBase64());
		
		dto.setBatchSize(3);
		dto.setCfgScale(7);
		dto.setDenoisingStrength(0.5);
		dto.setWidth(512);
		dto.setHeight(776);
		dto.setIsFromApi(true);
		dto.setModelCode(2);
		dto.setModelName("chilloutmix_NiPrunedFp32Fix");
		dto.setNegativePrompts(
				"badArtist,badArtistAnime,badHands_v5,badHand_v4,badImage_v2,badPictureChill_v7_5,badPrompt,badPromptByNeg_v2,ngDeepNegative_v1_75t,veryBadImageNegative_v1_3,");
		dto.setPrompts(
				"holding white paper");
		dto.setSampler(17);
		dto.setSeed(-1L);
		dto.setSteps(30);
		
		dto.setInpaintFullRes(false);
		dto.setInpaintingMaskInvert(false);

		JSONObject json = JSONObject.fromObject(dto);
//		System.out.println(json);

		HttpUtil h = new HttpUtil();

		String host = "http://127.0.0.1:10001";
		
		String response = h.sendPostRestful(host + "/aiArtApi/imageToImage", json.toString());
		System.out.println(response);
	}
}
