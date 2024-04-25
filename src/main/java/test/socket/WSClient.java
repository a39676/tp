package test.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.neovisionaries.ws.client.HostnameUnverifiedException;
import com.neovisionaries.ws.client.OpeningHandshakeException;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WSClient {

	public static void main(String[] args) throws IOException {
		String apiKey = "";
		String uriStr = "wss://streamer.cryptocompare.com/v2?api_key=" + apiKey;
		String subscriptModule = "5~CCCAGG~%s~USDT";
		List<String> coinNameList = new ArrayList<>();
		coinNameList.add("BTC");
		coinNameList.add("ETH");
		coinNameList.add("DOGE");
		coinNameList.add("FIL");
		coinNameList.add("SOL");
		JSONArray jsonArray = null;
		
		JSONObject json = null;

		List<WebSocket> wsList = new ArrayList<>();
		WebSocket wss = null;
		for (int i = 0; i < coinNameList.size(); i++) {
			if (i % 2 == 0) {
				wss = new WebSocketFactory().setVerifyHostname(false).createSocket(uriStr);
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
				
				jsonArray = new JSONArray();
				
				json = new JSONObject();
				json.put("action", "SubAdd");
			}
			
			jsonArray.add(String.format(subscriptModule, coinNameList.get(i)));
			
			if (i % 2 != 0) {
				json.put("subs", jsonArray);
				try {
					// Connect to the server and perform an opening handshake.
					// This method blocks until the opening handshake is finished.
					wss.connect();
					wss.sendText(json.toString());
					
				} catch (OpeningHandshakeException e) {
					// A violation against the WebSocket protocol was detected
					// during the opening handshake.
				} catch (HostnameUnverifiedException e) {
					// The certificate of the peer does not match the expected hostname.
				} catch (WebSocketException e) {
					// Failed to establish a WebSocket connection.
				}
				
				wsList.add(wss);
			}
			
		}

	}

}
