package demo.job51.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;

import demo.job51.pojo.dto.Job51MallApiListDTO;
import demo.job51.pojo.dto.Job51MallApiListDataElementDTO;
import demo.job51.pojo.dto.Job51MallApiListDataElementResumeExpectInfoDTO;
import demo.job51.pojo.dto.Job51MallApiListDataElementWorkDTO;
import demo.job51.pojo.dto.Job51MallApiListDataElementWorkExtendDTO;
import toolPack.ioHandle.FileUtilCustom;

public class Job51MainService {

	// mallapi.51job.com/mall-portal/resumeRecommend/list

	private static String mainFolderPath = "C:\\Users\\daven\\tmp\\51job";
	static {

	}

	public static void main(String[] args) {
		for (int i = 1; i < 11; i++) {
			Job51MallApiListDTO dto = loadFile(mainFolderPath + "/" + i + ".json");
			filter(dto);
		}
	}

	private static Job51MallApiListDTO loadFile(String filePathStr) {
		FileUtilCustom iou = new FileUtilCustom();
		String content = iou.getStringFromFile(filePathStr);
		Job51MallApiListDTO dto = new Gson().fromJson(content, Job51MallApiListDTO.class);
		return dto;
	}

	private static void filter(Job51MallApiListDTO dto) {
		StringBuilder sb = new StringBuilder();
		List<Job51MallApiListDataElementDTO> list = dto.getData().getList();
		for (int i = 0; i < list.size(); i++) {
			Job51MallApiListDataElementDTO ele = list.get(i);
			if (ele.getIsHiChat() || ele.getIsRead()) {
				continue;
			}
			Integer activeTypeCode = Integer.parseInt(ele.getJobSeekerActiveTypeCode());
			if (activeTypeCode > 4) {
				continue;
			}

			List<Job51MallApiListDataElementWorkDTO> workList = ele.getWork();
			Job51MallApiListDataElementWorkDTO work1 = workList.get(0);
			Job51MallApiListDataElementWorkExtendDTO work1Extend = setWorkTimeFields(work1);
			if (work1Extend.getWorkingMonths() < 18) {
				continue;
			}

			Job51MallApiListDataElementResumeExpectInfoDTO resumeExpectInfo = ele.getResumeExpectInfo();
			try {
				Integer expectMinSalary = Integer.parseInt(resumeExpectInfo.getExpectMinSalary());
				Integer expectMaxSalary = Integer.parseInt(resumeExpectInfo.getExpectMaxSalary());
				if (expectMinSalary > 9000 || expectMaxSalary > 11000) {
					continue;
				}
			} catch (Exception e) {
			}

			sb.append(ele.getWork().get(0).getCompany() + System.lineSeparator());
			System.out.println(ele.getWork().get(0).getCompany());
			sb.append(ele.getWork().get(0).getPosition() + System.lineSeparator());
			System.out.println(ele.getWork().get(0).getPosition());
			if (activeTypeCode == 0) {
				System.out.println(ele.getBaseInfo().getUserName() + "_activeTypeCode=0");
				sb.append(ele.getBaseInfo().getUserName() + "_activeTypeCode=0" + System.lineSeparator());
			} else {
				System.out.println(ele.getBaseInfo().getUserName());
				sb.append(ele.getBaseInfo().getUserName() + System.lineSeparator());
			}
			sb.append(System.lineSeparator());
			System.out.println();
//			for (int w = 0; w < workList.size(); w++) {}
		}

		if (sb.length() < 1) {
			System.err.println("Can NOT find any match");
		}

		FileUtilCustom iou = new FileUtilCustom();
		iou.byteToFileAppendAtEnd(sb.toString().getBytes(), mainFolderPath + "/tmp.txt");
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
}
