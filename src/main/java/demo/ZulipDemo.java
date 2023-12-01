package demo;

import java.util.List;

import com.github.jamesnetherton.zulip.client.Zulip;
import com.github.jamesnetherton.zulip.client.api.message.Message;
import com.github.jamesnetherton.zulip.client.api.message.MessageRecipient;
import com.github.jamesnetherton.zulip.client.api.message.request.DeleteMessageApiRequest;
import com.github.jamesnetherton.zulip.client.api.message.request.GetMessagesApiRequest;
import com.github.jamesnetherton.zulip.client.exception.ZulipClientException;

import net.sf.json.JSONObject;
import toolPack.ioHandle.FileUtilCustom;

public class ZulipDemo {

	private String optionFilePath = "d:/tmp/zOption.json";
	private String userEmail = null;
	private String apiKey = null;
	private String host = null;

	public static void main(String[] args) {
		ZulipDemo d = new ZulipDemo();
		d.core();
	}

	private void core() {
		FileUtilCustom f = new FileUtilCustom();
		String str = f.getStringFromFile(optionFilePath);
		JSONObject json = JSONObject.fromObject(str);
		userEmail = json.getString("userEmail");
		apiKey = json.getString("apiKey");
		host = json.getString("host");
		try {
			Zulip z = new Zulip(userEmail, apiKey, host);
			GetMessagesApiRequest msgReq = z.messages().getMessages(999, 999, 0L);
			List<Message> history = msgReq.execute();
			Message msg = null;
			for (int i = 0; i < history.size(); i++) {
				msg = history.get(i);
				System.out.println(msg.getId());
				System.out.println(msg.getContent());
				List<MessageRecipient> recipients = msg.getRecipients();
				if (recipients != null && !recipients.isEmpty()) {
					for (int j = 0; j < recipients.size(); j++) {
						MessageRecipient recipient = recipients.get(j);
						System.out.println(recipient.getFullName());
						if (recipient.getFullName().equals("Java") || recipient.getFullName().equals("Lee")) {
							deleteMsg(z, msg.getId());
						}
					}
				}
			}
			z.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteMsg(Zulip z, long msgId) throws ZulipClientException {
		DeleteMessageApiRequest req = z.messages().deleteMessage(msgId);
		req.execute();
	}
}
