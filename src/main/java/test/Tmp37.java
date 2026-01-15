package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;

import net.sf.json.JSONObject;
import toolPack.ioHandle.FileUtilCustom;

public class Tmp37 {
	
	public static String findApiKey() {
		FileUtilCustom ioUtil = new FileUtilCustom();
		String optionFilePath = System.getProperty("user.home") + "/optionFile/tp/option.json";
		JSONObject json = JSONObject.fromObject(ioUtil.getStringFromFile(optionFilePath));
		return json.getString("geminiKey");
	}

	public static void main(String[] args) throws IOException {

		String proxyHost = "127.0.0.1";
		String proxyPort = "10809";
//
//		System.setProperty("http.proxyHost", proxyHost);
//		System.setProperty("http.proxyPort", proxyPort);
//
//		System.setProperty("https.proxyHost", proxyHost);
//		System.setProperty("https.proxyPort", proxyPort);
//		
		System.setProperty("socksProxyHost", proxyHost);
		System.setProperty("socksProxyPort", proxyPort);

		try {
			Client client = Client.builder().apiKey(findApiKey()).build();
			List<String> responseModalities = new ArrayList<>();
			responseModalities.add("TEXT");
			responseModalities.add("IMAGE");
			GenerateContentConfig config = GenerateContentConfig.builder().responseModalities(responseModalities)
					.build();

			GenerateContentResponse response = client.models.generateContent("gemini-2.5-flash-image",
					Content.fromParts(Part.fromText("""
							remove all text
							"""),
							Part.fromBytes(Files.readAllBytes(Path.of(
									"C:\\Users\\daven\\tmp\\微景观仿真古风装饰花瓶 迷你树脂装饰diy配件娃娃屋汽车桌面摆件\\SKU图片/sku-5-天球瓶.jpg")),
									"image/jpeg")),
					config);

			for (Part part : response.parts()) {
				if (part.text().isPresent()) {
					System.out.println(part.text().get());
				} else if (part.inlineData().isPresent()) {
					var blob = part.inlineData().get();
					if (blob.data().isPresent()) {
						Files.write(Paths.get("gemini_generated_image.png"), blob.data().get());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
