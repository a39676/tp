package test.socket;

import java.io.IOException;

import com.neovisionaries.ws.client.HostnameUnverifiedException;
import com.neovisionaries.ws.client.OpeningHandshakeException;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class CoinbaseWebSocketClient {

	public static void main(String[] args) throws IOException {
		setProxy();
		String uriStr = "wss://advanced-trade-ws.coinbase.com";
		JSONArray jsonArray = new JSONArray();
		jsonArray.add("BTC-USD");
		jsonArray.add("ETH-USD");
		jsonArray.add("DOGE-USD");
		JSONObject json = new JSONObject();
		json.put("type", "subscribe");
		json.put("channel", "level2");
		json.put("product_ids", jsonArray);

		WebSocket wss = new WebSocketFactory().setVerifyHostname(false).createSocket(uriStr);

		wss.addListener(new WebSocketAdapter() {
			@Override
			public void onTextMessage(WebSocket websocket, String message) throws Exception {
				System.out.println(message);
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

	public static void setProxy() {
		String proxyHost = "127.0.0.1";
		String proxyPort = "10809";

		System.setProperty("http.proxyHost", proxyHost);
		System.setProperty("http.proxyPort", proxyPort);

		System.setProperty("https.proxyHost", proxyHost);
		System.setProperty("https.proxyPort", proxyPort);
	}
}
