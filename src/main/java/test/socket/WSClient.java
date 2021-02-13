package test.socket;

import java.io.IOException;

import com.neovisionaries.ws.client.HostnameUnverifiedException;
import com.neovisionaries.ws.client.OpeningHandshakeException;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

public class WSClient {

	public static void main(String[] args) throws IOException {
		String uriStr = "wss://streamer.cryptocompare.com/v2?api_key=";
		String addSub = "{\"action\": \"SubAdd\",\"subs\": [\"5~CCCAGG~BTC~USD\", \"0~Coinbase~ETH~USD\", \"2~Binance~BTC~USDT\"]}";

		WebSocket wss = new WebSocketFactory().createSocket(uriStr);

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
			wss.sendText(addSub);

		} catch (OpeningHandshakeException e) {
			// A violation against the WebSocket protocol was detected
			// during the opening handshake.
		} catch (HostnameUnverifiedException e) {
			// The certificate of the peer does not match the expected hostname.
		} catch (WebSocketException e) {
			// Failed to establish a WebSocket connection.
		}

	}

}
