package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import openAi.pojo.dto.OpanAiChatCompletionMessageDTO;
import openAi.pojo.type.OpenAiChatCompletionMessageRoleType;
import toolPack.httpHandel.HttpUtil;

public class Tmp19 {

	public static void main(String[] args) throws IOException, InterruptedException {
		Tmp19 t = new Tmp19();
		t.sending();
	}

	private void sending() throws IOException, InterruptedException {
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
		dto.setContent("Can you act as a history teacher and told me US Modern History?");
		msgList.add(dto);
		json.put("messages", msgList);

		String response = h.sendPostRestful(mainUrl, json.toString());
		System.out.println(response);

		int count = 0;
		int totalCount = 0;
		boolean flag = true;
		while (count < 500) {
			dto = new OpanAiChatCompletionMessageDTO();
			dto.setRole(OpenAiChatCompletionMessageRoleType.ASSISTANT.getName());
			dto.setContent(response);
			msgList.add(dto);

			msgList = roleChange(msgList);

			while (msgList.size() > 30) {
				msgList.remove(3);
			}

			randomSleep();

			json.put("messages", msgList);
			while (!flag) {
				try {
					response = h.sendPostRestful(mainUrl, json.toString());
					flag = true;
				} catch (Exception e) {
					flag = false;
				}

			}
			System.out.println(response);

			// cut list
			count++;
			totalCount++;
			System.out.println(totalCount);
		}
	}

	private List<OpanAiChatCompletionMessageDTO> roleChange(List<OpanAiChatCompletionMessageDTO> inputMsgList) {
		List<OpanAiChatCompletionMessageDTO> msgList = new ArrayList<>();
		for (OpanAiChatCompletionMessageDTO msg : inputMsgList) {
			if (OpenAiChatCompletionMessageRoleType.USER.getName().equals(msg.getRole())) {
				msg.setRole(OpenAiChatCompletionMessageRoleType.ASSISTANT.getName());
			} else {
				msg.setRole(OpenAiChatCompletionMessageRoleType.USER.getName());
			}
			msgList.add(msg);
		}
		return msgList;
	}

	private void randomSleep() throws InterruptedException {
		Double i = ((Math.random() * (2000L - 100L)) + 100L);
		Thread.sleep(i.longValue());
	}
}
