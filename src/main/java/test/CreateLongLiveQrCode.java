package test;

import java.io.IOException;
import java.net.URISyntaxException;

import net.sf.json.JSONObject;
import toolPack.httpHandel.HttpUtil;
import wechatApi.pojo.constant.WechatApiUrlConstant;

public class CreateLongLiveQrCode {

	public static void main(String[] args) throws IOException, URISyntaxException {
		String accessToken = "";
		HttpUtil h = new HttpUtil();
		String url = WechatApiUrlConstant.CREATE_LONGLIVE_QR_CODE;
		url = url + accessToken;

		JSONObject json = new JSONObject();
		json.put("action_name", "QR_LIMIT_SCENE");

		JSONObject sceneJson = new JSONObject();
		sceneJson.put("scene_id", 2);

		JSONObject actionInfoJson = new JSONObject();
		actionInfoJson.put("scene", sceneJson);

		json.put("action_info", actionInfoJson);

		System.out.println(json);

		String response = h.sendPostRestful(url, json.toString());
		System.out.println(response);

	}
}
