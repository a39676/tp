package test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Tmp30 extends Tmp29 {

	private static String zoneId = "73781fbd92bc596851de8e471279b315";
	private static String url = "https://api.cloudflare.com/client/v4/zones/" + zoneId + "/dns_records";
	private static String targetHost = "worker1.fdjaoreql.top";
	private static String targetIp = "116.23.76.101";
	private static String email = "q39676@gmail.com";
	private static String key = "STxf560eTOvy6dbTKwSv19cV4io5MPGB-zq9UEkH";

	public static void main(String[] args) {
		Tmp30 t = new Tmp30();
		t.create();
	}

	public void getTargetFromDnsList() {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		String responseStr = null;
		try {
			HttpGet request = new HttpGet(url);
			request.addHeader("x-auth-email", email);
			request.addHeader("Authorization", "Bearer " + key);
			HttpResponse response = httpClient.execute(request);
			responseStr = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
			System.out.println(responseStr);
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		JSONObject json = JSONObject.fromObject(responseStr);
		JSONArray list = json.getJSONArray("result");
		JSONObject targetRecord = null;
		for (int i = 0; i < list.size() && targetRecord == null; i++) {
			JSONObject tmpJson = list.getJSONObject(i);
			if (targetHost.equals(tmpJson.getString("name"))) {
				targetRecord = tmpJson;
			}
		}

		if (targetRecord != null) {
			System.out.println(targetRecord.getString("id"));
		}
	}

	public void create() {
		JSONObject paramJson = new JSONObject();
		paramJson.put("type", "A");
		paramJson.put("name", targetHost);
		paramJson.put("content", targetIp);
		paramJson.put("ttl", "120");
		paramJson.put("proxied", false);

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost request = new HttpPost(url);
			StringEntity params = new StringEntity(paramJson.toString());
//			params.setContentType("application/json");
			request.addHeader("x-auth-email", email);
//			request.addHeader("x-auth-key", key);
			request.addHeader("Authorization", "Bearer " + key);

//			request.addHeader("Content-Type", "application/json");
			request.setEntity(params);
			HttpResponse response = httpClient.execute(request);
			String responseStr = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
			System.out.println(responseStr);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void edit(String recordID) {
		JSONObject paramJson = new JSONObject();
		paramJson.put("type", "A");
		paramJson.put("name", targetHost);
		paramJson.put("content", targetIp);
		paramJson.put("ttl", "3600");
		paramJson.put("proxied", false);

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPatch request = new HttpPatch(url + "/" + recordID);
			StringEntity params = new StringEntity(paramJson.toString());
			request.addHeader("x-auth-email", email);
			request.addHeader("Authorization", "Bearer " + key);
			request.setEntity(params);
			HttpResponse response = httpClient.execute(request);
			String responseStr = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
			System.out.println(responseStr);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
