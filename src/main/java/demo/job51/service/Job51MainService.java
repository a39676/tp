package demo.job51.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;

import demo.job51.pojo.dto.Job51MallApiListDTO;
import demo.job51.pojo.dto.Job51MallApiListDataElementDTO;
import demo.job51.pojo.dto.Job51MallApiListDataElementResumeExpectInfoDTO;
import demo.job51.pojo.dto.Job51MallApiListDataElementWorkDTO;
import demo.job51.pojo.dto.Job51MallApiListDataElementWorkExtendDTO;
import demo.job51.pojo.dto.Job51OutputInfoDTO;
import toolPack.ioHandle.FileUtilCustom;

public class Job51MainService {

	// mallapi.51job.com/mall-portal/resumeRecommend/list

	private static final String MAIN_FOLDER_PATH = "C:\\Users\\daven\\tmp\\51job";
	private static final String INPUT_JSON_FILE_PATH = MAIN_FOLDER_PATH + "/0.json";
	private static final String OUTPUT_TXT_FILE_PATH = MAIN_FOLDER_PATH + "/tmp.txt";
	private static List<Job51OutputInfoDTO> outputInfoList = new ArrayList<>();
	private static final int MAX_MONTH_OF_WORK_GAP = 3;
	private static List<String> positionNameKeyWordList = new ArrayList<>();
	static {
		positionNameKeyWordList.add("半导体");
		positionNameKeyWordList.add("非标");
		positionNameKeyWordList.add("机械结构");
	}

	public static void main(String[] args) {
		List<Job51MallApiListDTO> list = loadFile(INPUT_JSON_FILE_PATH);
		for (int i = 0; i < list.size(); i++) {
			Job51MallApiListDTO dto = list.get(i);
			filter(dto);
		}

		if (outputInfoList.size() < 1) {
			System.err.println("Can NOT find any match");
			return;
		}
		FileUtilCustom iou = new FileUtilCustom();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < outputInfoList.size(); i++) {
			Job51OutputInfoDTO ele = outputInfoList.get(i);
			for (int positionNameKeyWordIndex = 0; positionNameKeyWordIndex < positionNameKeyWordList
					.size(); positionNameKeyWordIndex++) {
				String positionNameKeyWord = positionNameKeyWordList.get(positionNameKeyWordIndex);
				if (ele.getPostion().contains(positionNameKeyWord)) {
					ele.setPostion(ele.getPostion() + "+");
				}
			}
			sb.append(ele.toString2() + System.lineSeparator());
			System.out.println(ele.toString2());
		}
		iou.byteToFile(System.lineSeparator(), OUTPUT_TXT_FILE_PATH);
		iou.byteToFileAppendAtEnd(sb.toString().getBytes(), OUTPUT_TXT_FILE_PATH);
	}

	private static List<Job51MallApiListDTO> loadFile(String filePathStr) {
		FileUtilCustom iou = new FileUtilCustom();
		String content = iou.getStringFromFile(filePathStr);
		List<Job51MallApiListDTO> list = new ArrayList<>();
		String[] contentArray = content.split(System.lineSeparator());
		for (int i = 0; i < contentArray.length; i++) {
			String subJsonStr = contentArray[i];
			Job51MallApiListDTO dto = new Gson().fromJson(subJsonStr, Job51MallApiListDTO.class);
			list.add(dto);
		}
		return list;
	}

	private static void filter(Job51MallApiListDTO dto) {
		List<Job51MallApiListDataElementDTO> list = dto.getData().getList();
		for (int i = 0; i < list.size(); i++) {
			Job51MallApiListDataElementDTO ele = list.get(i);
			Job51OutputInfoDTO infoDTO = new Job51OutputInfoDTO();
			infoDTO.setCompanyName(ele.getWork().get(0).getCompany());
			infoDTO.setPostion(ele.getWork().get(0).getPosition());
			infoDTO.setUsername(ele.getBaseInfo().getUserName());
			System.out.println(infoDTO.toString2());

			if (ele.getIsHiChat() || ele.getIsRead()) {
				System.err.println(", 已读或已聊");
				continue;
			}
			Integer activeTypeCode = Integer.parseInt(ele.getJobSeekerActiveTypeCode());
			if (activeTypeCode > 5) {
				System.err.println(", 长时间不活跃");
				continue;
			}

			if (!companyNameFilter(ele)) {
				System.err.println(", 公司名异常 或 曾经在此公司工作");
				continue;
			}

			if (!workExpHandle(ele)) {
				System.err.println(", 频繁换工作");
				continue;
			}

			Job51MallApiListDataElementResumeExpectInfoDTO resumeExpectInfo = ele.getResumeExpectInfo();
			try {
				@SuppressWarnings("unused")
				Integer expectMinSalary = Integer.parseInt(resumeExpectInfo.getExpectMinSalary());
				Integer expectMaxSalary = Integer.parseInt(resumeExpectInfo.getExpectMaxSalary());
//				if (expectMinSalary > 10000 || expectMaxSalary > 13000) {
				if (expectMaxSalary > 13000) {
					System.err.println(", 薪资期望过高");
					continue;
				}
			} catch (Exception e) {
			}

			if (activeTypeCode == 0) {
				infoDTO.setUsername(ele.getBaseInfo().getUserName() + "_activeTypeCode=0");
			}

			outputInfoList.add(infoDTO);
//			for (int w = 0; w < workList.size(); w++) {}
		}
	}

	private static Job51MallApiListDataElementWorkExtendDTO setWorkTimeFields(Job51MallApiListDataElementWorkDTO work) {
		Job51MallApiListDataElementWorkExtendDTO result = new Job51MallApiListDataElementWorkExtendDTO();
		BeanUtils.copyProperties(work, result);
		String startTimeStr = work.getStartTime();
		String[] startTimeStrArray = startTimeStr.split("\\.");
		LocalDate startTime = LocalDate.of(Integer.parseInt(startTimeStrArray[0]),
				Integer.parseInt(startTimeStrArray[1]), 1);
		result.setStartTimeLocalDate(startTime);

		String endTimeStr = work.getEndTime();
		if (!endTimeStr.contains(".")) {
			result.setEndTimeLocalDate(LocalDate.now());
		} else {
			String[] endTimeStrArray = endTimeStr.split("\\.");
			LocalDate endTime = LocalDate.of(Integer.parseInt(endTimeStrArray[0]), Integer.parseInt(endTimeStrArray[1]),
					1);
			result.setEndTimeLocalDate(endTime);
		}
		Long months = ChronoUnit.MONTHS.between(result.getStartTimeLocalDate(), result.getEndTimeLocalDate());
		result.setWorkingMonths(months.intValue());
		return result;
	}

	private static boolean workExpHandle(Job51MallApiListDataElementDTO ele) {
		List<Job51MallApiListDataElementWorkDTO> workList = ele.getWork();

		int minAvgWorkMonths = 18;
		if ("本科".equals(ele.getBaseInfo().getTopDegreeStr())) {
			minAvgWorkMonths = 12;
		}

		if (workList.size() < 1) {
			return false;
		}

		Job51MallApiListDataElementWorkDTO work1 = workList.get(0);
		Job51MallApiListDataElementWorkExtendDTO work1Extend = setWorkTimeFields(work1);

		if (work1Extend.getEndTimeLocalDate().plusMonths(MAX_MONTH_OF_WORK_GAP).isBefore(LocalDate.now())) {
			return false;
		}

		if (workList.size() == 1) {
			return work1Extend.getWorkingMonths() >= minAvgWorkMonths;
		} else if (workList.size() > 1) {
			Job51MallApiListDataElementWorkDTO work2 = workList.get(1);
			Job51MallApiListDataElementWorkExtendDTO work2Extend = setWorkTimeFields(work2);
			return work1Extend.getWorkingMonths() + work2Extend.getWorkingMonths() >= minAvgWorkMonths * 2;
		}

		return false;
	}

	private static boolean companyNameFilter(Job51MallApiListDataElementDTO ele) {
		List<Job51MallApiListDataElementWorkDTO> workList = ele.getWork();
		for (int i = 0; i < workList.size(); i++) {
			String companyName = workList.get(i).getCompany();
			if (StringUtils.isBlank(companyName) || companyName.contains("新凯来")) {
				return false;
			}
		}
		return true;
	}
}
