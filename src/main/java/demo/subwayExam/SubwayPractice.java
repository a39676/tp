package demo.subwayExam;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import demo.subwayExam.pojo.dto.SubwayPracticeAnswerDTO;
import demo.subwayExam.pojo.dto.SubwayPracticeChoiceDTO;
import demo.subwayExam.pojo.dto.SubwayPracticeDTO;
import demo.subwayExam.pojo.dto.SubwayPracticeOutputDTO;
import demo.subwayExam.pojo.dto.SubwayPracticeOutputQuestionDTO;
import demo.subwayExam.pojo.dto.SubwayPracticeQuestionDTO;
import demo.subwayExam.pojo.dto.SubwayPracticeSubAnswerDTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import toolPack.httpHandel.HttpUtil;
import toolPack.ioHandle.FileUtilCustom;

public class SubwayPractice {

	private static String mainUrl = "https://api-phx-hw02.yunxuetang.cn";
	@SuppressWarnings("unused")
	private static final int SINGLE_CHOICE = 0;
	@SuppressWarnings("unused")
	private static final int MULTIPLE_CHOICE = 1;
	private static final int JUDGE = 2;

	public static void main(String[] args) {
		List<String> practiceIdList = getPracticeIdList();
		for (String practiceId : practiceIdList) {
			handlePractice(practiceId);
		}
	}

	public static List<String> getPracticeIdList() {
		String url = mainUrl + "/audit/todos/list";
		String requestParam = "{\"attributes\":[],\"catgCodes\":[\"practice\"],\"sceneTarget\":\"0\",\"startTime\":\"\",\"endTime\":\"\"}";
		List<String> idList = new ArrayList<>();
		HttpUtil h = new HttpUtil();
		try {
			String response = h.sendPost(url, requestParam, getRequestPropertyMap());

			JSONObject ja = JSONObject.fromObject(response);
			JSONArray datas = ja.getJSONArray("datas");
			for (int i = 0; i < datas.size(); i++) {
				JSONObject data = datas.getJSONObject(i);
				idList.add(data.getString("subjectId"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return idList;
	}

	public static void handlePractice(String practiceId) {
		SubwayPracticeDTO practice = saveExam(practiceId);

		List<SubwayPracticeQuestionDTO> listQues = practice.getListQues();
		SubwayPracticeOutputDTO outputDTO = new SubwayPracticeOutputDTO();
		outputDTO.setQuestions(new ArrayList<>());
		SubwayPracticeOutputQuestionDTO questionOutputDTO = null;

		for (int i = 0; i < listQues.size(); i++) {
			SubwayPracticeQuestionDTO question = listQues.get(i);
			if (question.getChoiceItems() != null && !question.getChoiceItems().isEmpty()) {
				questionOutputDTO = new SubwayPracticeOutputQuestionDTO();
				questionOutputDTO.setQuestion(question.getContent());
				questionOutputDTO.setChoices(new ArrayList<>());
				questionOutputDTO.setAnswers(new ArrayList<>());
				for (SubwayPracticeChoiceDTO choice : question.getChoiceItems()) {
					questionOutputDTO.getChoices().add(choice.getOrderIndex() + ", " + choice.getItemContent());
					if ("1".equals(choice.getAnswer())) {
						questionOutputDTO.getAnswers().add(choice.getOrderIndex() + ", " + choice.getItemContent());
					}
				}
				outputDTO.getQuestions().add(questionOutputDTO);
			} else {
				questionOutputDTO = new SubwayPracticeOutputQuestionDTO();
				questionOutputDTO.setQuestion(question.getContent());
				questionOutputDTO.setAnswers(new ArrayList<>());
				if (handleJudgeQuestion(question, practice)) {
					questionOutputDTO.getAnswers().add("T");
				} else {
					questionOutputDTO.getAnswers().add("F");
				}
				outputDTO.getQuestions().add(questionOutputDTO);
			}
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(outputDTO);
		FileUtilCustom f = new FileUtilCustom();
		f.byteToFile(jsonString.toString().getBytes(StandardCharsets.UTF_8),
				(System.getProperty("user.home") + "/tmp/subway/" + practice.getPraName() + "_answer.txt"));
	}

	public static boolean sendAnswer(String practiceId, SubwayPracticeAnswerDTO dto) {
		String url = mainUrl + "/ote/upm/%s/users/%s/answer";
		url = String.format(url, practiceId, dto.getPuId());

		JSONObject mainAnswer = new JSONObject();
		mainAnswer.put("isH5Exam", 0);
		mainAnswer.put("uniqueId", dto.getUniqueId());
		mainAnswer.put("pumId", dto.getPumId());
		mainAnswer.put("isLastQues", 0);

		mainAnswer.put("answers", dto.getAnswers());

		try {
			HttpURLConnection conn = null;
			StringBuilder response = new StringBuilder();

			byte[] postData = mainAnswer.toString().getBytes(StandardCharsets.UTF_8);

			try {

				URL myurl = new URI(url).toURL();
				conn = (HttpURLConnection) myurl.openConnection();

				conn.setDoOutput(true);
				conn.setRequestMethod("PUT");

				Map<String, String> requestPropertyMap = getRequestPropertyMap();
				for (Entry<String, String> entry : requestPropertyMap.entrySet()) {
					conn.setRequestProperty(entry.getKey(), entry.getValue());
				}

				if (postData != null) {
					DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
					wr.write(postData);
					wr.flush();
				}

				BufferedReader in = null;
				if ("gzip".equals(conn.getContentEncoding())) {
					in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
				} else {
					in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
				}

				String line;

				while ((line = in.readLine()) != null) {
					response.append(line);
					response.append(System.lineSeparator());
				}

			} finally {
				if (conn != null) {
					conn.disconnect();
				}
			}
//			System.out.println(response);
//			[{"examQuesId":"466a37bd-0bb5-4ab0-82ed-deedc25ca49d","quesType":2,"answers":null,"keywords":null,"fillinAnswers":null,"fillinCorrectedInfo":null,"judgeAnswer":0,"explainText":"","answerPlay":null,"explainPlay":null,"corrected":0,"allowViewSingleQuesResult":1,"allowViewSingleQuesAnswer":1,"allowViewSingleQuesExplain":1}]
			JSONArray responseJsonArray = JSONArray.fromObject(response.toString());
			JSONObject answerResult = responseJsonArray.getJSONObject(0);
			if (answerResult.containsKey("corrected")) {
				String corrected = answerResult.getString("corrected");
				return "1".equals(corrected);
			} else {
				System.out.println(response);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static SubwayPracticeDTO saveExam(String practiceId) {
		HttpUtil h = new HttpUtil();

		String url = mainUrl + "/ote/upm/start?praId=%s&usedTime=6&praBatchId=";
		url = String.format(url, practiceId);

		Map<String, String> parameters = new HashMap<>();
		Map<String, String> requestPropertyMap = getRequestPropertyMap();
		String response = null;
		try {
			response = h.sendGet(url, parameters, requestPropertyMap);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		SubwayPracticeDTO practiceDTO = new Gson().fromJson(response, SubwayPracticeDTO.class);

		FileUtilCustom f = new FileUtilCustom();
		String filePath = System.getProperty("user.home") + "/tmp/subway/" + practiceDTO.getPraName() + "_question.txt";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(practiceDTO);
		f.byteToFile(jsonString.toString().getBytes(StandardCharsets.UTF_8),filePath);

		return practiceDTO;
	}

	private static boolean handleJudgeQuestion(SubwayPracticeQuestionDTO question, SubwayPracticeDTO practice) {
		if (question.getSubmitQuesAnswers() != null) {
			List<String> answers = question.getSubmitQuesAnswers();
			Integer corrected = question.getCorrected();
			String answer = answers.get(0);
			if (1 == (corrected)) {
				return "1".equals(answer);
			} else {
				return !"1".equals(answer);
			}
		} else {
			return sendAnswerForJudgeQuestion(question, practice);
		}
	}

	private static boolean sendAnswerForJudgeQuestion(SubwayPracticeQuestionDTO question, SubwayPracticeDTO practice) {
		String uniqueId = practice.getUniqueId();
		String pumId = practice.getPumId();
		String puId = practice.getPuId();
		SubwayPracticeSubAnswerDTO subAnswer = new SubwayPracticeSubAnswerDTO();
		subAnswer.setQuesId(question.getId());
		subAnswer.setLastSubmitTime(new Date().getTime());
		subAnswer.setAnswer(Arrays.asList("1"));
		subAnswer.setExamQuesId(question.getExamQuesId());
		subAnswer.setQuesType(JUDGE);
		SubwayPracticeAnswerDTO answerDTO = new SubwayPracticeAnswerDTO();
		answerDTO.setAnswers(Arrays.asList(subAnswer));
		answerDTO.setPuId(puId);
		answerDTO.setPumId(pumId);
		answerDTO.setUniqueId(uniqueId);
		boolean answerFlag = sendAnswer(practice.getPraId(), answerDTO);
		return answerFlag;
	}

	public static Map<String, String> getRequestPropertyMap() {
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJvcmdJZCI6ImQ1YmNiZjhjLWU1OTUtNDBmZi05YzBjLWYzOWE1ZDAyZjFiYyIsInVzZXJJZCI6IjM0YTI3M2Y2LWU4MDgtNDgzYi1iMTAxLTk4NDczM2QxMDIzOCIsImNsdXN0ZXJJZCI6Imh1YXdlaS1pdGFpIiwiZXhwIjoxNzIzMDgyODM4fQ.mhVaWfr2Dj6lG-3UCPju9mZijpGz-W0XTGCP05O4g4YQdIQrdQEEOPDaKV96mBc-tBMSi5arm7O2d_mke7F_aw"; 
		
		Map<String, String> requestPropertyMap = new HashMap<>();
		requestPropertyMap.put("token", token);
		requestPropertyMap.put("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:128.0) Gecko/20100101 Firefox/128.0");
		requestPropertyMap.put("Accept", "application/json, text/plain, */*");
		requestPropertyMap.put("Accept-Encoding", "gzip, deflate, br, zstd");
		requestPropertyMap.put("Accept-Language", "en-US,en;q=0.5");
		requestPropertyMap.put("Connection", "keep-alive");
		requestPropertyMap.put("Content-Type", "application/json;charset=UTF-8");
		requestPropertyMap.put("Host", "api-phx-hw02.yunxuetang.cn");
		requestPropertyMap.put("Origin", "https://gzmtr.yunxuetang.cn");
		requestPropertyMap.put("Referer", "https://gzmtr.yunxuetang.cn/");
		requestPropertyMap.put("Sec-Fetch-Dest", "empty");
		requestPropertyMap.put("Sec-Fetch-Mode", "cors");
		requestPropertyMap.put("Sec-Fetch-Site", "same-site");
		requestPropertyMap.put("source", "501");
		requestPropertyMap.put("TE", "trailers");
		requestPropertyMap.put("Yxt-OrgDomain", "gzmtr.yunxuetang.cn");
//		requestParameters.put("YxtSpanId", "f42f0ed6b2132446");
//		requestParameters.put("YxtTraceId", "5.d5bcbf8c-e595-40ff-9c0c-f39a5d02f1bc.34a273f6-e808-483b-b101-984733d10238.1721873802452.81556025");
		return requestPropertyMap;
	}
}
