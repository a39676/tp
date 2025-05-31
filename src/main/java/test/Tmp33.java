package test;

import java.io.IOException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import toolPack.ioHandle.FileUtilCustom;

public class Tmp33 {

	@SuppressWarnings("unused")
	private final static String futureUsdtTradeUriStr = "wss://fx-ws.gateio.ws/v4/ws/usdt";
	private final static String spotUriStr = "wss://api.gateio.ws/ws/v4/";
	String filePath = "C:/Users/daven/optionFile/cryptoCoinMonitor/cryptoCoin/option.json";
	String apiKey = "";
	String apiSecret = "";
	JSONObject option = null;
	WebSocket futureTradeWsStreamClient = null;

	public JSONObject loadOption() {
		FileUtilCustom f = new FileUtilCustom();
		String content = f.getStringFromFile(filePath);
		option = JSONObject.fromObject(content);
		JSONArray userMetaData = option.getJSONArray("userMetaData");
		JSONObject user = null;
		for (int i = 0; user == null && i < userMetaData.size(); i++) {
			user = userMetaData.getJSONObject(i);
			String nickname = user.getString("nickname");
			if (!"su2".equals(nickname)) {
				user = null;
			}
		}
		if (user == null) {
			return option;
		}
		apiKey = user.getString("gateIoApiKey");
		apiSecret = user.getString("gateIoSecretKey");
		return option;
	}

	private JSONObject sign(JSONObject json) {
		String ts = String.valueOf(System.currentTimeMillis() / 1000);
		json.put("time", ts);
		String channel = json.getString("channel");
		String event = json.getString("event");
		JSONObject authJson = new JSONObject();
		String sign = sign(channel, event);
		authJson.put("method", "api_key");
		authJson.put("KEY", apiKey);
		authJson.put("SIGN", sign);
		json.put("auth", authJson);
		return json;
	}

	private String sign(String channel, String event) {
		String ts = String.valueOf(System.currentTimeMillis() / 1000);
		String signatureString = String.format("%s\n%s\n%s\n%s\n%s", "api_key", channel, event,
				DigestUtils.sha512Hex(event), ts);

		String signature = null;
		try {
			Mac hmacSha512 = Mac.getInstance("HmacSHA512");
			SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(), "HmacSHA512");
			hmacSha512.init(spec);
			signature = Hex.encodeHexString(hmacSha512.doFinal(signatureString.getBytes()));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return signature;
	}

	public void startWs() {
		try {
			futureTradeWsStreamClient = new WebSocketFactory().setVerifyHostname(false)
					.createSocket(spotUriStr);
			futureTradeWsStreamClient.addListener(new WebSocketAdapter() {
				@Override
				public void onTextMessage(WebSocket websocket, String message) throws Exception {
					System.out.println(message);
				}

				@Override
				public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame,
						WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
				}
			});
			futureTradeWsStreamClient.connect();
		} catch (IOException | WebSocketException e) {
			e.printStackTrace();
			futureTradeWsStreamClient = null;
		}
	}

	public void connectTrade() {
		JSONObject json = new JSONObject();
		json.put("channel", "futures.usertrades");
		json.put("event", "subscribe");
		JSONArray payload = new JSONArray();
		payload.add("20011");
		payload.add("BTC_USD");
		json.put("payload", payload);
		sign(json);
		System.out.println(json.toString());
		futureTradeWsStreamClient.sendText(json.toString());
	}

//	ws.send('{"time" : 123456, "channel" : "futures.candlesticks","event": "subscribe", "payload" : ["1m", "BTC_USD"]}')
	public void connectKLine(String symbol) {
		JSONObject json = new JSONObject();
		String ts = String.valueOf(System.currentTimeMillis() / 1000);
		json.put("time", ts);
		json.put("channel", "futures.candlesticks");
		json.put("event", "subscribe");
		JSONArray payload = new JSONArray();
		payload.add("1m");
		payload.add(symbol);
		json.put("payload", payload);
		System.out.println(json.toString());
		futureTradeWsStreamClient.sendText(json.toString());
	}

	public static void main(String[] args) {
		Tmp33 t = new Tmp33();
		t.loadOption();
		t.startWs();
		t.connectTrade();
		t.connectKLine("BTC_USDT");
	}
}
