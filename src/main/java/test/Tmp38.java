package test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;

import net.sf.json.JSONObject;
import toolPack.ioHandle.FileUtilCustom;

public class Tmp38 {

	static String inputFolderPath = "C:\\Users\\daven\\tmp\\胖月亮16mm琉璃珠串珠散珠子弯月牙中孔手工diy手链古风发簪材料\\tmp";
	static String outputFolderPath = "C:/Users/daven/tmp/outputFolder";

	public String findApiKey() {
		FileUtilCustom ioUtil = new FileUtilCustom();
		String optionFilePath = System.getProperty("user.home") + "/optionFile/tp/option.json";
		JSONObject json = JSONObject.fromObject(ioUtil.getStringFromFile(optionFilePath));
		return json.getString("geminiKey");
	}

	public void sendMission() {
		File folder = new File(inputFolderPath);
		File[] fileArray = folder.listFiles();
		List<File> fileList = new ArrayList<>();
		for (int i = 0; i < fileArray.length; i++) {
			File file = fileArray[i];
			String fileName = file.getName();
			if (fileName.endsWith("jpg") || fileName.endsWith("png")) {
				fileList.add(file);
			}
		}

		Client client = Client.builder().apiKey(findApiKey()).build();

		for (int i = 0; i < fileList.size(); i++) {
			File imgFile = fileList.get(i);
			String fileName = imgFile.getName();
			// 2. 读取图片
			byte[] imageBytes = null;
			try {
				imageBytes = Files.readAllBytes(Paths.get(imgFile.getAbsolutePath()));
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 3. 配置生成参数（必须指定包含 IMAGE 模态）
			List<String> responseModalities = new ArrayList<>();
			responseModalities.add("TEXT");
			responseModalities.add("IMAGE");
			GenerateContentConfig config = GenerateContentConfig.builder().responseModalities(responseModalities)
					.build();

			// 4. 调用模型进行图生图任务
			// 模型名称：gemini-2.5-flash-image (即 Nano Banana)
			GenerateContentResponse response = client.models.generateContent("gemini-2.5-flash-image",
					Content.fromParts(Part.fromBytes(imageBytes, "image/png"), // 原始图片
							Part.fromText("remove all text") // 修改指令
					), config);

			// 5. 处理并保存生成的图片
			for (Part part : Objects.requireNonNull(response.parts())) {
				if (part.inlineData().isPresent()) {
					var blob = part.inlineData().get();
					if (!fileName.endsWith("png")) {
						fileName = fileName.replaceAll("jpg", "png");
					}
					try {
						Files.write(Paths.get(outputFolderPath + "/" + fileName), blob.data().get());
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("新图片已生成：" + fileName);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {

		String proxyHost = "127.0.0.1";
		String proxyPort = "10809";
		System.setProperty("socksProxyHost", proxyHost);
		System.setProperty("socksProxyPort", proxyPort);

		Tmp38 t = new Tmp38();

		t.sendMission();
	}
}
