package demo;

import com.github.jamesnetherton.zulip.client.Zulip;
import com.github.jamesnetherton.zulip.client.exception.ZulipClientException;

import io.taliox.zulip.ZulipRestExecutor;
import io.taliox.zulip.calls.streams.GetAllStreams;
import net.sf.json.JSONObject;
import toolPack.ioHandle.FileUtilCustom;

public class ZulipDemo2 {

	private String optionFilePath = "d:/tmp/zOption.json";
	private String userEmail = null;
	private String apiKey = null;
	private String host = null;

	public static void main(String[] args) {
		ZulipDemo2 d = new ZulipDemo2();
		d.core();
	}

	private void core() {
		FileUtilCustom f = new FileUtilCustom();
		String str = f.getStringFromFile(optionFilePath);
		JSONObject json = JSONObject.fromObject(str);
		userEmail = json.getString("email");
		apiKey = json.getString("apiKey");
		host = json.getString("host");
		try {
			@SuppressWarnings("unused")
			ZulipRestExecutor z = new ZulipRestExecutor(userEmail, apiKey, host);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteAttachment(Zulip z) {
//		TODO
	}

	@SuppressWarnings("unused")
	private void deleteMsg(ZulipRestExecutor z) throws ZulipClientException {
		GetAllStreams getAllStreams = new GetAllStreams();
		String response = z.executeCall(getAllStreams);

	}

}
