package demo.subwayExam;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;

import demo.subwayExam.pojo.dto.SubwayStudyListDTO;
import demo.subwayExam.pojo.dto.SubwayStudyProjectDTO;
import demo.subwayExam.pojo.dto.SubwayStudyProjectDataDTO;
import demo.subwayExam.pojo.dto.SubwayStudyProjectDetailDTO;
import demo.subwayExam.pojo.dto.SubwayStudySendSecondDTO;
import demo.subwayExam.pojo.dto.SubwayStudyStudyLogDTO;
import demo.subwayExam.pojo.dto.SubwayStudyStudyParamDTO;
import demo.subwayExam.pojo.dto.SubwayStudyTargetParamDTO;
import demo.subwayExam.pojo.dto.SubwayStudyTaskDetailDTO;
import demo.subwayExam.pojo.dto.SubwayStudyTaskPreinitDTO;
import net.sf.json.JSONObject;
import toolPack.dateTimeHandle.LocalDateTimeHandler;
import toolPack.httpHandel.HttpUtil;

public class SubwayStudy {

	private static String token = "eyJhbGciOiJIUzUxMiJ9.eyJvcmdJZCI6ImQ1YmNiZjhjLWU1OTUtNDBmZi05YzBjLWYzOWE1ZDAyZjFiYyIsInVzZXJJZCI6IjM0YTI3M2Y2LWU4MDgtNDgzYi1iMTAxLTk4NDczM2QxMDIzOCIsImNsdXN0ZXJJZCI6Imh1YXdlaS1pdGFpIiwiZXhwIjoxNzIzMDgyODM4fQ.mhVaWfr2Dj6lG-3UCPju9mZijpGz-W0XTGCP05O4g4YQdIQrdQEEOPDaKV96mBc-tBMSi5arm7O2d_mke7F_aw";
	private static String mainUrl = "https://api-phx-hw02.yunxuetang.cn";

	public static void main(String[] args) {
		SubwayStudyListDTO listDTO = getStudyList();

		if (listDTO == null) {
			return;
		}

		List<SubwayStudyProjectDTO> targetProjectList = findTargetProjectList(listDTO);
		for (SubwayStudyProjectDTO project : targetProjectList) {
			SubwayStudyProjectDetailDTO projectDetail = getProjectDetail(project.getProjectId());
			for (SubwayStudyProjectDataDTO data : projectDetail.getDatas()) {
				for (SubwayStudyTaskDetailDTO task : data.getStudentTaskBeans()) {
					UUID uuid = UUID.randomUUID();
					preinitTask(task, data.getId());
//					kngPlay(task, data.getId());

					studyLogStart(task, data.getId(), uuid.toString());
//					sendSecond(task, data.getId());
				}
			}
		}
	}

	public static SubwayStudyListDTO getStudyList() {
		SubwayStudyListDTO dto = null;
		String url = mainUrl
				+ "/o2o/study/list/nocount?desc=1&orderType=1&category=&status=2&keyword=&showUnFinish=0&limit=16&offset=0&tagIds=";
		HttpUtil h = new HttpUtil();

		Map<String, String> requestPropertyMap = getRequestPropertyMap();

		try {
			String response = h.sendGet(url, new HashMap<>(), requestPropertyMap);
			dto = new Gson().fromJson(response, SubwayStudyListDTO.class);
			System.out.println(dto);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return dto;
	}

	public static List<SubwayStudyProjectDTO> findTargetProjectList(SubwayStudyListDTO dto) {
		List<SubwayStudyProjectDTO> list = dto.getDatas();
		if (list == null || list.isEmpty()) {
			return list;
		}

		for (int i = 0; i < list.size(); i++) {
			SubwayStudyProjectDTO project = list.get(i);
			if (project.getCompleteCount() == 1) {
				list.remove(i);
				i++;
			}
		}

		return list;
	}

	public static SubwayStudyProjectDetailDTO getProjectDetail(String projectId) {
		String url = mainUrl + "/o2o/client/projects/" + projectId + "/tree?preview=false";
		SubwayStudyProjectDetailDTO dto = null;
		HttpUtil h = new HttpUtil();

		Map<String, String> requestPropertyMap = getRequestPropertyMap();

		try {
			String response = h.sendGet(url, new HashMap<>(), requestPropertyMap);
			System.out.println(response);
			dto = new Gson().fromJson(response, SubwayStudyProjectDetailDTO.class);
			System.out.println(dto);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return dto;
	}

	private static void preinitTask(SubwayStudyTaskDetailDTO taskDetail, String projectId) {
		String url = mainUrl + "/kng/study/submit/preinit";

		HttpUtil h = new HttpUtil();
		Map<String, String> requestPropertyMap = getRequestPropertyMap();

		SubwayStudyTaskPreinitDTO dto = new SubwayStudyTaskPreinitDTO();
		SubwayStudyTargetParamDTO targetParam = new SubwayStudyTargetParamDTO();
		targetParam.setBatchId(taskDetail.getId());
		targetParam.setProjectId(projectId);
		targetParam.setFlipId("");
		targetParam.setBatchId("");
		SubwayStudyStudyParamDTO studyParam = new SubwayStudyStudyParamDTO();
		studyParam.setOriginOrgId(taskDetail.getOrgId());
		studyParam.setPreviewType(0);
		dto.setTargetParam(targetParam);
		dto.setStudyParam(studyParam);
		dto.setCourseId("");
		dto.setKngId(taskDetail.getTargetId());
		dto.setTargetCode("o2o");
		dto.setTargetId(projectId);
		dto.setCustomFunctionCode("");

		JSONObject json = JSONObject.fromObject(dto);

		try {
			String response = h.sendPost(url, json.toString(), requestPropertyMap);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

	private static void kngPlay(SubwayStudyTaskDetailDTO taskDetail, String projectId) {
		String url = mainUrl + "/kng/study/kngPlay";

		HttpUtil h = new HttpUtil();
		Map<String, String> requestPropertyMap = getRequestPropertyMap();

		SubwayStudyTaskPreinitDTO dto = new SubwayStudyTaskPreinitDTO();
		SubwayStudyTargetParamDTO targetParam = new SubwayStudyTargetParamDTO();
		targetParam.setBatchId(taskDetail.getId());
		targetParam.setProjectId(projectId);
		targetParam.setFlipId("");
		targetParam.setBatchId("");
		SubwayStudyStudyParamDTO studyParam = new SubwayStudyStudyParamDTO();
		studyParam.setOriginOrgId(taskDetail.getOrgId());
		studyParam.setPreviewType(0);
		dto.setTargetParam(targetParam);
		dto.setStudyParam(studyParam);
		dto.setCourseId("");
		dto.setKngId(taskDetail.getTargetId());
		dto.setTargetCode("o2o");
		dto.setTargetId(projectId);
		dto.setCustomFunctionCode("");

		JSONObject json = JSONObject.fromObject(dto);

		try {
			String response = h.sendPost(url, json.toString(), requestPropertyMap);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private static void studyLogStart(SubwayStudyTaskDetailDTO taskDetail, String projectId, String uuid) {
//		TODO
		String url = mainUrl + "/studylog/logs";
		String datetimeFormat = "d/M/yyyy, H:mm:ss a";

		HttpUtil h = new HttpUtil();
		Map<String, String> requestPropertyMap = getRequestPropertyMap();

		LocalDateTimeHandler localDateTimeHandler = new LocalDateTimeHandler();
		SubwayStudyStudyLogDTO dto = new SubwayStudyStudyLogDTO();
		JSONObject requestHeaderJson = new JSONObject();
		requestHeaderJson.put("source", 501);
		dto.setRequestHeaderJson(requestHeaderJson);
		dto.setLogTypeCode("003");
		dto.setUuId(uuid);
		String urlModel = "https://gzmtr.yunxuetang.cn/o2o/#/playinfo?projectid=%s&taskId=%s";
		String pageUrl = String.format(urlModel, projectId, taskDetail.getId());
		dto.setPageUrl(pageUrl);
		dto.setCourseId("");
		dto.setKngId(taskDetail.getTargetId());
		dto.setExt1("开始定时器");
		dto.setExt2("");
		dto.setExt3("1");
		dto.setExt4("");
		dto.setExt5(localDateTimeHandler.dateToStr(LocalDateTime.now(), datetimeFormat));
		

		JSONObject json = JSONObject.fromObject(dto);

		try {
			String response = h.sendPost(url, json.toString(), requestPropertyMap);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static void sendSecond(SubwayStudyTaskDetailDTO taskDetail, String projectId) {
		String url = mainUrl + "/kng/study/submit/second?trackId=" + taskDetail.getTrackId();
		SubwayStudySendSecondDTO dto = new SubwayStudySendSecondDTO();
		SubwayStudyTargetParamDTO targetParam = new SubwayStudyTargetParamDTO();
		targetParam.setBatchId(taskDetail.getId());
		targetParam.setProjectId(projectId);
		targetParam.setFlipId("");
		targetParam.setBatchId("");
		dto.setTargetParam(targetParam);

		dto.setKngId(taskDetail.getTargetId());
		dto.setTargetId(projectId);
		dto.setTargetCode("o2o");
		dto.setOriginOrgId(taskDetail.getOrgId());

		JSONObject json = JSONObject.fromObject(dto);

		Map<String, String> requestPropertyMap = getRequestPropertyMap();

		HttpUtil h = new HttpUtil();
		try {
			String response = h.sendPost(url, json.toString(), requestPropertyMap);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private static Map<String, String> getRequestPropertyMap() {
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
		return requestPropertyMap;
	}
}
