package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ai.aiChat.pojo.dto.AiChatSendNewMsgFromApiDTO;
import net.sf.json.JSONObject;
import openAi.pojo.dto.OpanAiChatCompletionMessageDTO;
import toolPack.httpHandel.HttpUtil;

public class Tmp21 {

	public static void main(String[] args) throws IOException {
		AiChatSendNewMsgFromApiDTO dto = new AiChatSendNewMsgFromApiDTO();
		dto.setApiKey("mWpUQTtU5n5O9Zw5HecLVIqLYKQOCapxJf5zqoIn9PM=");
		List<OpanAiChatCompletionMessageDTO> messages = new ArrayList<>();
		OpanAiChatCompletionMessageDTO msgDto = new OpanAiChatCompletionMessageDTO();
		msgDto.setRole("system");
		msgDto.setContent("content from system");
		messages.add(msgDto);
		msgDto = new OpanAiChatCompletionMessageDTO();
		msgDto.setRole("user");
		msgDto.setContent("content from user");
		messages.add(msgDto);
		dto.setMessages(messages);
		dto.setTemperature(1D);
		dto.setTop_p(1D);
		dto.setN(1);
		List<String> stopWords = new ArrayList<>();
		stopWords.add("stop word 1");
		stopWords.add("stop word 2");
		stopWords.add("stop word 3");
		dto.setStop(stopWords);
		dto.setMax_tokens(4000);
		dto.setPresence_penalty(1D);
		dto.setFrequency_penalty(1D);
		Map<String, Integer> loginBiasMap = new HashMap<>();
		loginBiasMap.put("WordNeed", 100);
		loginBiasMap.put("WordDoNotNeed", -100);
		dto.setLogit_bias(loginBiasMap);
		
		JSONObject j = JSONObject.fromObject(dto);
		System.out.println(j.toString());
		
		HttpUtil h = new HttpUtil();
		String response = h.sendPostRestful("http://localhost:10001/aiChatApi/sendChatCompletion", j.toString());
		System.out.println(response);
	}
}
