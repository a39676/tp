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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import demo.subwayExam.pojo.dto.SubwayExamAnswerDTO;
import demo.subwayExam.pojo.dto.SubwayExamSubAnswerDTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import toolPack.httpHandel.HttpUtil;
import toolPack.ioHandle.FileUtilCustom;

public class SubwayExam {

	private static String parId = "e5e0c616-70df-4a9b-b9e8-cd62a31d652d";
	private static String token = "eyJhbGciOiJIUzUxMiJ9.eyJvcmdJZCI6ImQ1YmNiZjhjLWU1OTUtNDBmZi05YzBjLWYzOWE1ZDAyZjFiYyIsInVzZXJJZCI6IjM0YTI3M2Y2LWU4MDgtNDgzYi1iMTAxLTk4NDczM2QxMDIzOCIsImNsdXN0ZXJJZCI6Imh1YXdlaS1pdGFpIiwiZXhwIjoxNzIzMDgyODM4fQ.mhVaWfr2Dj6lG-3UCPju9mZijpGz-W0XTGCP05O4g4YQdIQrdQEEOPDaKV96mBc-tBMSi5arm7O2d_mke7F_aw";
	private static String mainUrl = "https://api-phx-hw02.yunxuetang.cn/ote/upm/start?praId=%s&usedTime=6&praBatchId=";
	private static final int SINGLE_CHOICE = 0;
	private static final int MULTIPLE_CHOICE = 1;
	private static final int JUDGE = 2;

	public static void main(String[] args) {
		String exam = saveExam();
		JSONObject examJson = JSONObject.fromObject(exam);
		String uniqueId = examJson.getString("uniqueId");
		String pumId = examJson.getString("pumId");
		String puId = examJson.getString("puId");

		JSONArray listQues = examJson.getJSONArray("listQues");
		SubwayExamAnswerDTO answerDTO = null;
		SubwayExamSubAnswerDTO subAnswer = null;
		for (int i = 0; i < listQues.size(); i++) {
			JSONObject question = listQues.getJSONObject(i);
			int questionType = question.getInt("quesType");
			if (JUDGE == questionType) {
				subAnswer = new SubwayExamSubAnswerDTO();
				subAnswer.setQuesId(question.getString("id"));
				subAnswer.setLastSubmitTime(new Date().getTime());
				subAnswer.setAnswer(Arrays.asList("1"));
				subAnswer.setExamQuesId(question.getString("examQuesId"));
				subAnswer.setQuesType(JUDGE);
			}
			answerDTO = new SubwayExamAnswerDTO();
			answerDTO.setAnswers(Arrays.asList(subAnswer));
			answerDTO.setPuId(puId);
			answerDTO.setPumId(pumId);
			answerDTO.setUniqueId(uniqueId);
			sendAnswer(answerDTO);
			return;
		}

	}

	public static String sendAnswer(SubwayExamAnswerDTO dto) {
		String answerMainUrl = "https://api-phx-hw02.yunxuetang.cn/ote/upm/%s/users/%s/answer";
		String url = String.format(answerMainUrl, parId, dto.getPuId());

		JSONObject mainAnswer = new JSONObject();
		mainAnswer.put("isH5Exam", 0);
		mainAnswer.put("uniqueId", dto.getUniqueId());
		mainAnswer.put("pumId", dto.getPumId());
		mainAnswer.put("isLastQues", 0);

		mainAnswer.put("answers", dto.getAnswers());

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

		try {

			HttpURLConnection con = null;
			StringBuilder response = new StringBuilder();

			byte[] postData = mainAnswer.toString().getBytes(StandardCharsets.UTF_8);

			try {

				URL myurl = new URI(url).toURL();
				con = (HttpURLConnection) myurl.openConnection();

				con.setDoOutput(true);
				con.setRequestMethod("PUT");

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

			} finally {
				if (con != null) {
					con.disconnect();
				}
			}
			System.out.println(response);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String saveExam() {
		HttpUtil h = new HttpUtil();

		String url = String.format(mainUrl, parId);

		Map<String, String> parameters = new HashMap<>();
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
		String response = null;
		try {
			response = h.sendGet(url, parameters, requestPropertyMap);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
//		System.out.println(response);

		FileUtilCustom f = new FileUtilCustom();
		f.byteToFile(response.getBytes(StandardCharsets.UTF_8), "d:/tmp/question/tmp1.txt");

		return response;
	}
}
