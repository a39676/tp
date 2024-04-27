package test.socket;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import com.neovisionaries.ws.client.HostnameUnverifiedException;
import com.neovisionaries.ws.client.OpeningHandshakeException;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GateIoWebSocketClient {

	public static void main(String[] args) throws IOException {
		String uriStr = "wss://api.gateio.ws/ws/v4/";

		WebSocket wss = new WebSocketFactory().setVerifyHostname(false).createSocket(uriStr);

		wss.addListener(new WebSocketAdapter() {
			@Override
			public void onTextMessage(WebSocket websocket, String message) throws Exception {
//				System.out.println(message);
				System.out.println(LocalDateTime.now() + ", " + message);
			}

			@Override
			public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame,
					WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {

			}
		});

		try {
			// Connect to the server and perform an opening handshake.
			// This method blocks until the opening handshake is finished.
			wss.connect();

			JSONArray jsonArray = new JSONArray();
			JSONObject json = new JSONObject();
			jsonArray.add("1m");
			jsonArray.add("BTC_USDT");
			json.put("time", new Date().getTime());
			json.put("channel", "spot.candlesticks");
			json.put("event", "subscribe");
			json.put("payload", jsonArray);
			wss.sendText(json.toString());

			jsonArray = new JSONArray();
			json = new JSONObject();
			jsonArray.add("1m");
			jsonArray.add("ETH_USDT");
			json.put("time", new Date().getTime());
			json.put("channel", "spot.candlesticks");
			json.put("event", "subscribe");
			json.put("payload", jsonArray);
			wss.sendText(json.toString());

//			jsonArray = new JSONArray();
//			json = new JSONObject();
//			jsonArray.add("BTC_USDT");
//			jsonArray.add("ETH_USDT");
//			json.put("time", new Date().getTime());
//			json.put("channel", "spot.trades");
//			json.put("event", "subscribe");
//			json.put("payload", jsonArray);
//			wss.sendText(json.toString());

			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			jsonArray = new JSONArray();
			json = new JSONObject();
			jsonArray.add("1m");
			jsonArray.add("BTC_USDT");
			json.put("time", new Date().getTime());
			json.put("channel", "spot.candlesticks");
			json.put("event", "unsubscribe");
			json.put("payload", jsonArray);
			wss.sendText(json.toString());

		} catch (OpeningHandshakeException e) {
			e.printStackTrace();
			// A violation against the WebSocket protocol was detected
			// during the opening handshake.
		} catch (HostnameUnverifiedException e) {
			e.printStackTrace();
			// The certificate of the peer does not match the expected hostname.
		} catch (WebSocketException e) {
			e.printStackTrace();
			// Failed to establish a WebSocket connection.
		}

	}

}
