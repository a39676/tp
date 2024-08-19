package test.aiArt;

import java.time.LocalDateTime;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import auxiliaryCommon.pojo.result.CommonResult;
import auxiliaryCommon.pojo.type.BaseResultType;
import toolPack.dateTimeHandle.DateHandler;
import toolPack.dateTimeHandle.LocalDateTimeAdapter;
import toolPack.dateTimeHandle.LocalDateTimeHandler;
import toolPack.ioHandle.FileUtilCustom;
import toolPack.numericHandel.NumericUtilCustom;
import tool_package.SnowFlake;

public abstract class CommonService {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	protected static LocalDateTimeHandler localDateTimeHandler = new LocalDateTimeHandler();
	protected static DateHandler dateHandler = new DateHandler();
	protected static LocalDateTimeAdapter localDateTimeAdapter = new LocalDateTimeAdapter();
	protected static SnowFlake snowFlake = new SnowFlake();
	protected static NumericUtilCustom numericUtil = new NumericUtilCustom();
	protected static FileUtilCustom ioUtil = new FileUtilCustom();

	protected static final long THE_START_TIME = 946656000000L;
	protected static final String MAIN_FOLDER_PATH = "/home/u2/bbt";

	protected String createDateDescription(Date inputDate) {
		if (inputDate == null) {
			return "";
		}
		Long oneHourLong = 1000L * 60 * 60;
		Long timeDiff = System.currentTimeMillis() - inputDate.getTime();
		if (timeDiff < (oneHourLong / 2)) {
			return "a moment...";
		} else if (timeDiff <= oneHourLong) {
			return "not long ago";
		} else if (timeDiff <= (oneHourLong * 12)) {
			return String.valueOf(timeDiff / oneHourLong) + " hours ago";
		} else if (timeDiff <= (oneHourLong * 24)) {
			return "today";
		} else if (timeDiff <= (oneHourLong * 24 * 3)) {
			return String.valueOf(timeDiff / (oneHourLong * 24)) + " days ago";
		} else if (timeDiff <= (oneHourLong * 24 * 7)) {
			return "within a week";
		} else if (timeDiff <= (oneHourLong * 24 * 31)) {
			return "within a month";
		} else {
			return "long long ago...";
		}
	}

	protected CommonResult nullParam() {
		CommonResult result = new CommonResult();
		result.fillWithResult(BaseResultType.NULL_PARAM);
		return result;
	}

	protected CommonResult errorParam() {
		CommonResult result = new CommonResult();
		result.fillWithResult(BaseResultType.ERROR_PARAM);
		return result;
	}

	protected CommonResult serviceError() {
		CommonResult result = new CommonResult();
		result.fillWithResult(BaseResultType.SERVICE_ERROR);
		return result;
	}

	protected CommonResult normalSuccess() {
		CommonResult result = new CommonResult();
		result.normalSuccess();
		return result;
	}

	protected CommonResult notLogin() {
		CommonResult result = new CommonResult();
		result.failWithMessage("请登录后操作");
		return result;
	}

	protected boolean isWindows() {
		String os = System.getProperty("os.name");
		if (os != null) {
			if (os.toLowerCase().contains("windows")) {
				return true;
			}
		}
		return false;
	}

	protected boolean isLinux() {
		String os = System.getProperty("os.name");
		if (os != null) {
			if (os.toLowerCase().contains("linux")) {
				return true;
			}
		}
		return false;
	}

	protected String getSuffixName(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		return str.substring(str.lastIndexOf(".") + 1);
	}

	protected String pathChangeByDetectOS(String oldPath) {
		if (isWindows()) {
			return oldPath.replaceAll("/", "\\\\");
		} else {
			return oldPath.replaceAll("\\\\", "/");
		}
	}

	protected static <T> T buildObjFromJsonCustomization(String jsonStr, Class<T> clazz) {
		String className = clazz.getSimpleName();

		try {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, localDateTimeAdapter).create();

			return gson.fromJson(jsonStr, clazz);

		} catch (Exception e) {
			e.printStackTrace();
			String msg = String.format("Build gson error, param name: %s ", className);
			System.err.println(msg);
		}
		return null;

	}

//	protected void sendTelegramMsg(String msg) {
//		TelegramBotNoticeMessageDTO dto = new TelegramBotNoticeMessageDTO();
//		dto.setId(TelegramStaticChatID.MY_ID);
//		dto.setBotName(TelegramBotType.CX_MESSAGE.getName());
//		dto.setMsg(msg);
//		telegramMessageAckProducer.send(dto);
//	}
}
