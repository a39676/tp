package test.aiArt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import toolPack.ioHandle.FileUtilCustom;

@Scope("singleton")
@Service
public class SystemOptionService extends CommonService {

	@Value("${optionFilePath.system}")
	private String optionFilePath;

	private String env;
	private String managerCode;
	private String automaticUrl;
	private String cthulhuUrl;
	private Integer maxFailedSendCount;
	private List<String> nsfwPrompt;

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getAutomaticUrl() {
		return automaticUrl;
	}

	public void setAutomaticUrl(String automaticUrl) {
		this.automaticUrl = automaticUrl;
	}

	public String getCthulhuUrl() {
		return cthulhuUrl;
	}

	public void setCthulhuUrl(String cthulhuUrl) {
		this.cthulhuUrl = cthulhuUrl;
	}

	public Integer getMaxFailedSendCount() {
		return maxFailedSendCount;
	}

	public void setMaxFailedSendCount(Integer maxFailedSendCount) {
		this.maxFailedSendCount = maxFailedSendCount;
	}

	public List<String> getNsfwPrompt() {
		return nsfwPrompt;
	}

	public void setNsfwPrompt(List<String> nsfwPrompt) {
		this.nsfwPrompt = nsfwPrompt;
	}

	public String getOptionFilePath() {
		return optionFilePath;
	}

	public void setOptionFilePath(String optionFilePath) {
		this.optionFilePath = optionFilePath;
	}

	@Override
	public String toString() {
		return "SystemOptionService [env=" + env + ", managerCode=" + managerCode + ", automaticUrl=" + automaticUrl
				+ ", cthulhuUrl=" + cthulhuUrl + ", maxFailedSendCount=" + maxFailedSendCount + ", nsfwPrompt="
				+ nsfwPrompt + "]";
	}

	@PostConstruct
	public void refreshOption() {
		String proxyHost = "127.0.0.1";
		String proxyPort = "10809";

		System.setProperty("http.proxyHost", proxyHost);
		System.setProperty("http.proxyPort", proxyPort);

		System.setProperty("https.proxyHost", proxyHost);
		System.setProperty("https.proxyPort", proxyPort);

		File optionFile = new File(optionFilePath);
		if (!optionFile.exists()) {
			return;
		}
		try {
			FileUtilCustom fileUtil = new FileUtilCustom();
			String jsonStr = fileUtil.getStringFromFile(optionFilePath);
			JSONObject json = JSONObject.fromObject(jsonStr);
			setEnv(json.getString("env"));
			setManagerCode(json.getString("managerCode"));
			setAutomaticUrl(json.getString("automaticUrl"));
			setCthulhuUrl(json.getString("cthulhuUrl"));
			setMaxFailedSendCount(json.getInt("maxFailedSendCount"));
			List<String> nsfwPromptList = new ArrayList<>();
			JSONArray nsfwPromptJsonArray = json.getJSONArray("nsfwPrompt");
			for (int i = 0; i < nsfwPromptJsonArray.size(); i++) {
				nsfwPromptList.add(nsfwPromptJsonArray.getString(i));
			}
			setNsfwPrompt(nsfwPromptList);

			log.error("Option loaded");
			log.error(toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Option loading error: " + e.getLocalizedMessage());
		}
		
	}
}
