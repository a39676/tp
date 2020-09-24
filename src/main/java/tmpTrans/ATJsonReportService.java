package tmpTrans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

import at.report.pojo.dto.JsonReportDTO;
import autoTest.jsonReport.pojo.constant.AutoTestJsonReportKeyConstant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ATJsonReportService {

	public void appendContent(JsonReportDTO dto, String content) {
		appendReport(dto, content, null);
	}

	public void appendImage(JsonReportDTO dto, String imagePath) {
		appendReport(dto, null, imagePath);
	}

	private void appendReport(JsonReportDTO dto, String content, String imagePath) {
		JSONArray ja = dto.getContentJsonAry();
		if (ja == null) {
			ja = new JSONArray();
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
		String formatDateTimeStr = LocalDateTime.now().format(formatter);

		JSONObject j = null;
		if (StringUtils.isNotBlank(content)) {
			j = new JSONObject();
			j.put(AutoTestJsonReportKeyConstant.strKey, formatDateTimeStr + " " + content);
			ja.add(j);
		}

		if (StringUtils.isNotBlank(imagePath)) {
			j = new JSONObject();
			j.put(AutoTestJsonReportKeyConstant.imgKey, imagePath);
			ja.add(j);
		}

		dto.setContentJsonAry(ja);
	}

	public boolean outputReport(JsonReportDTO dto, String filePath) {

		FileOutputStream fos = null;
		try {
			File reportFolder = new File(dto.getOutputReportPath());
			if (!reportFolder.exists() || !reportFolder.isDirectory()) {
				reportFolder.mkdirs();
			}
			fos = new FileOutputStream(filePath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		if (fos != null) {
			try {
				fos.write(dto.getContentJsonAry().toString().getBytes(StandardCharsets.UTF_8));
				fos.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public boolean outputReport(JsonReportDTO dto, String folderPath, String fileName) {

		FileOutputStream fos = null;
		try {
			File reportFolder = new File(dto.getOutputReportPath());
			if (!reportFolder.exists() || !reportFolder.isDirectory()) {
				reportFolder.mkdirs();
			}
			fos = new FileOutputStream(folderPath + File.separator + fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		if (fos != null) {
			try {
				fos.write(dto.getContentJsonAry().toString().getBytes(StandardCharsets.UTF_8));
				fos.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

}
