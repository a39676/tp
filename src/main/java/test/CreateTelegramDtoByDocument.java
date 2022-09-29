package test;

import java.util.HashMap;
import java.util.Map;

import toolPack.ioHandle.FileUtilCustom;

public class CreateTelegramDtoByDocument {

	public static void main(String[] args) {
		
		FileUtilCustom io = new FileUtilCustom();
		String content = io.getStringFromFile("d:/tmp/tmp.txt");
		Map<String, String> keyWordMap = buildKeyWordMap();
//		System.out.println(content);
		
		String[] lines = content.split(System.lineSeparator());
		String[] words = null;
		String line = lines[0];
		System.out.println("Telegram" + line + "DTO");
		
		boolean targetFlag = false;
		for(int i = 1; i < lines.length; i++) {
			line = lines[i];
			words = line.split("\\t");
			if(!targetFlag) {
				if(words.length > 0 && words[0].equals("Field")) {
					targetFlag = true;
				}
				continue;
			}
			System.out.print("private ");
			System.out.print(keyWordMap.get(words[1]));
			System.out.print(words[0] + " ");
			System.out.println(";");
		}
	}

	private static Map<String, String> buildKeyWordMap() {
		Map<String, String> keyWordMap = new HashMap<>();
		keyWordMap.put("", " ");
		keyWordMap.put("", " ");
		keyWordMap.put("", " ");
		
		keyWordMap.put("Voice", "TelegramVoiceDTO ");
		keyWordMap.put("InlineKeyboardMarkup", "TelegramInlineKeyboardMarkupDTO ");
		keyWordMap.put("Array of InlineKeyboardButton", "List<TelegramInlineKeyboardButtonDTO> ");
		keyWordMap.put("InlineKeyboardButton", "TelegramInlineKeyboardButtonDTO ");
		keyWordMap.put("CallbackGame", "TelegramCallbackGameDTO ");
		keyWordMap.put("LoginUrl", "TelegramLoginUrlDTO ");
		keyWordMap.put("WebAppInfo", "TelegramWebAppInfoDTO ");
		keyWordMap.put("WebAppData", "TelegramWebAppDataDTO ");
		keyWordMap.put("VideoChatScheduled", "TelegramVideoChatScheduledDTO ");
		keyWordMap.put("ProximityAlertTriggered", "TelegramProximityAlertTriggeredDTO ");
		keyWordMap.put("PassportData", "TelegramPassportDataDTO ");
		keyWordMap.put("EncryptedCredentials", "TelegramEncryptedCredentialsDTO ");
		keyWordMap.put("Array of EncryptedPassportElement", "List<TelegramEncryptedPassportElementDTO> ");
		keyWordMap.put("EncryptedPassportElement", "TelegramEncryptedPassportElementDTO ");
		keyWordMap.put("Array of PassportFile", "List<TelegramPassportFileDTO> ");
		keyWordMap.put("PassportFile", "TelegramPassportFileDTO ");
		keyWordMap.put("SuccessfulPayment", "TelegramSuccessfulPaymentDTO ");
		keyWordMap.put("OrderInfo", "TelegramOrderInfoDTO ");
		keyWordMap.put("LabeledPrice", "TelegramLabeledPriceDTO ");
		keyWordMap.put("Array of LabeledPrice", "List<TelegramLabeledPriceDTO> ");
		keyWordMap.put("String", "String ");
		keyWordMap.put("Integer", "Long ");
		keyWordMap.put("True", "Boolean ");
		keyWordMap.put("Boolean", "Boolean ");
		keyWordMap.put("PhotoSize", "TelegramPhotoSizeDTO ");
		keyWordMap.put("Chat", "TelegramChatDTO ");
		keyWordMap.put("Document", "TelegramDocumentDTO ");
		keyWordMap.put("Location", "TelegramLocationDTO ");
		keyWordMap.put("User", "TelegramUserDTO ");
		keyWordMap.put("Array of User", "List<TelegramUserDTO> ");
		keyWordMap.put("Audio", "TelegramAudioDTO ");
		keyWordMap.put("Animation", "TelegramAnimationDTO ");
		keyWordMap.put("Message", "TelegramMessageDTO ");
		keyWordMap.put("Array of PhotoSize", "List<TelegramPhotoSizeDTO> ");
		keyWordMap.put("File", "TelegramFileDTO ");
		keyWordMap.put("Float", "Float ");
		keyWordMap.put("Float number", "Float ");
		keyWordMap.put("MaskPosition", "TelegramMaskPositionDTO ");
		keyWordMap.put("Sticker", "TelegramStickersDTO ");
		keyWordMap.put("MessageEntity", "TelegramMessageEntityDTO ");
		keyWordMap.put("Array of MessageEntity", "List<TelegramMessageEntityDTO> ");
		keyWordMap.put("Video", "TelegramVideoDTO ");
		keyWordMap.put("VideoNote", "TelegramVideoNoteDTO ");
		keyWordMap.put("Contact", "TelegramContactDTO ");
		keyWordMap.put("Dice", "TelegramDiceDTO ");
		keyWordMap.put("Game", "TelegramGameDTO ");
		keyWordMap.put("PollOption", "TelegramPollOptionDTO ");
		keyWordMap.put("Array of PollOption", "TelegramPollOptionDTO ");
		keyWordMap.put("Poll", "TelegramPollDTO ");
		keyWordMap.put("Venue", "TelegramVenueDTO ");
		keyWordMap.put("MessageAutoDeleteTimerChanged", "TelegramMessageAutoDeleteTimerChangedDTO ");
		keyWordMap.put("Invoice", "TelegramInvoiceDTO ");
		keyWordMap.put("ShippingAddress", "TelegramShippingAddressDTO ");
		keyWordMap.put(null, " ");
		return keyWordMap;
	}
}
