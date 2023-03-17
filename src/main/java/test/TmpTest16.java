package test;

import com.google.gson.Gson;

import wechatApi.pojo.result.GetWechatUserInfoOrUidAccessTokenByCodeResult;

public class TmpTest16 {

	public static void main(String[] args) throws Exception {

//		System.out.println(UUID.randomUUID());
		
		String str = "{\"success\":true,\"error-codes\":[],\"challenge_ts\":\"2023-03-11T11:48:48.361Z\",\"hostname\":\"localhost\",\"action\":\"\",\"cdata\":\"\"}";
//		str = "{\n"
//				+ "    \"access_token\": \"ACCESS_TOKEN\",\n"
//				+ "    \"expires_in\": 7200,\n"
//				+ "    \"refresh_token\": \"REFRESH_TOKEN\",\n"
//				+ "    \"openid\": \"OPENID\",\n"
//				+ "    \"scope\": \"SCOPE\",\n"
//				+ "    \"is_snapshotuser\": 1,\n"
//				+ "    \"unionid\": \"UNIONID\"\n"
//				+ "}";
		str = "{\"errcode\":40029,\"errmsg\":\"invalid code\"}";
		GetWechatUserInfoOrUidAccessTokenByCodeResult r = new Gson().fromJson(str, GetWechatUserInfoOrUidAccessTokenByCodeResult.class);
		System.out.println(r.toString());
	}
}
