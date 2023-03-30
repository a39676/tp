package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import openAi.pojo.dto.OpanAiChatCompletionMessageDTO;
import openAi.pojo.type.OpenAiChatCompletionMessageRoleType;
import toolPack.httpHandel.HttpUtil;

public class Tmp19 {

	public static void main(String[] args) throws IOException {
		String mainUrl = "https://chat.xianshenglu.xyz/api/chat-stream";
		HttpUtil h = new HttpUtil();

//		String str = "{\n"
//				+ "    \"model\": \"gpt-3.5-turbo\",\n"
//				+ "    \"messages\":\n"
//				+ "    [\n"
//				+ "        {\n"
//				+ "            \"role\": \"assistant\",\n"
//				+ "            \"content\": \"这是一个测试吗？有什么需要帮忙的吗？\"\n"
//				+ "        },\n"
//				+ "        {\n"
//				+ "            \"role\": \"user\",\n"
//				+ "            \"content\": \"有什么好玩的\\n\"\n"
//				+ "        }\n"
//				+ "    ],\n"
//				+ "    \"stream\": true,\n"
//				+ "    \"temperature\": 1,\n"
//				+ "    \"max_tokens\": 2000,\n"
//				+ "    \"presence_penalty\": 0\n"
//				+ "}";
		
		JSONObject json = new JSONObject();
		json.put("model", "gpt-3.5-turbo");
		json.put("stream", true);
		json.put("temperature", 1);
		json.put("max_tokens", 2000);
		json.put("presence_penalty", 0);
		List<OpanAiChatCompletionMessageDTO> msgList = new ArrayList<>();
		OpanAiChatCompletionMessageDTO dto = new OpanAiChatCompletionMessageDTO();
		dto.setRole(OpenAiChatCompletionMessageRoleType.USER.getName());
		dto.setContent("你是 ChatGPT 吗? 用的是哪个 model?");
		msgList.add(dto);
		json.put("messages", msgList);
		System.out.println(json.toString());
		
		String response = h.sendPostRestful(mainUrl, json.toString());
		System.out.println(response);
	}
}
