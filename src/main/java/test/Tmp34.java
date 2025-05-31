package test;

import java.io.IOException;
import java.util.Date;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Tmp34 {

	private final static String futureUsdtTradeUriStr = "wss://api.gateio.ws/ws/v4/";
	JSONObject option = null;
	WebSocket futureTradeWsStreamClient = null;

	public JSONObject loadOption() {
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
		return option;
	}

	public void startWs() {
		try {
			futureTradeWsStreamClient = new WebSocketFactory().setVerifyHostname(false)
					.createSocket(futureUsdtTradeUriStr);
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

	public void connectKLine() {
		JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject();
		jsonArray.add("1m");
		jsonArray.add("BTC_USDT");
		json.put("time", new Date().getTime());
		json.put("channel", "spot.candlesticks");
		json.put("event", "subscribe");
		json.put("payload", jsonArray);
		System.out.println(json.toString());
		futureTradeWsStreamClient.sendText(json.toString());
	}

	public static void main(String[] args) {
		Tmp34 t = new Tmp34();
		t.loadOption();
		t.startWs();
		t.connectKLine();
	}
}
