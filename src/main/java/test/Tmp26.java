package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Tmp26 {

	public static void main(String[] args) throws IOException, URISyntaxException {

		String str = "{\n" + "    \"coursewareId\": \"aa724f2f-3d68-4030-b2db-aecfdab1c6a5\",\n"
				+ "    \"learnSecond\": 360,\n" + "    \"trainProjectId\": \"de6c897e-b130-4e0a-a2d9-afad04cb7e95\",\n"
				+ "    \"inputDataType\": \"pc\",\n" + "    \"inputDataDevice\": \"pc\"\n" + "}";
//		String urlStr = "https://edu.gzmtr.cc/bingosoft-train-courseonline-api/pc/userLearnNew?access_token=08b00d42-1f18-483a-9168-b1165739b94b&_=1687343566478";
//		String urlStr = "https://edu.gzmtr.cc/bingosoft-train-courseonline-api/pc/userLearnNew?access_token=08b00d42-1f18-483a-9168-b1165739b94b";
//		String urlStr = "https://edu.gzmtr.cc/bingosoft-train-courseonline-api/pc/userLearnNew?access_token=b97cd049-c751-4e96-9b95-ef07f33fe04e&_=1687358246236";
		String urlStr = "https://edu.gzmtr.cc/bingosoft-train-courseonline-api/pc/userLearnNew?access_token=7f825d62-c70e-4719-9f01-a27f28d32fa9&_=1687358785224";
//		String cookieStr = "JSESSIONID=70DBEAE6691F2CB77EF5615B9E32BE8D; username=ToTgZcHW9xBbxyyc/cTYa7++q4A0Hgv893+eiWCKPpvOqbDC2BqK+5He/BGYrOM28QU/QW6sRszCTW2KzFlMsQ==; tenantCode=gC1QQ0kf3we/SH9dbrHT5QD8IyFjqFe998yxKnZ7dYuwnHV6tRZttVpowh5COzQE2nlKHBcOWdgkDnrWJz7UFA==; __SecurityService_loginId_koken_=25f2c93e-ab08-4a2b-bd1e-16335b815801; __sso_token__=08b00d42-1f18-483a-9168-b1165739b94b; servletName=ssoclient"; 

		Map<String, String> requestPropertyMap = new HashMap<>();
		requestPropertyMap.put("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36");
		requestPropertyMap.put("Content-Type", "application/json; charset=UTF-8");
		requestPropertyMap.put("Data-Type", "json; charset=UTF-8");

		HttpURLConnection con = null;
		StringBuilder response = new StringBuilder();

		byte[] postData = str.getBytes(StandardCharsets.UTF_8);

		try {

			URL myurl = new URI(urlStr).toURL();
			con = (HttpURLConnection) myurl.openConnection();

			con.setDoOutput(true);
			con.setRequestMethod("POST");
//			con.setRequestProperty("Cookie", cookieStr);

			for (Entry<String, String> entry : requestPropertyMap.entrySet()) {
				con.setRequestProperty(entry.getKey(), entry.getValue());
			}

			if (postData != null) {
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.write(postData);
				wr.flush();
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;

			while ((line = in.readLine()) != null) {
				response.append(line);
				response.append(System.lineSeparator());
			}

			System.out.println(response);

		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}
}
