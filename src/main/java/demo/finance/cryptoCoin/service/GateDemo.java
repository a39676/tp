package demo.finance.cryptoCoin.service;

import demo.finance.cryptoCoin.pojo.dto.GateKeysDTO;
import io.gate.gateapi.ApiClient;
import io.gate.gateapi.ApiException;
import io.gate.gateapi.GateApiException;
import io.gate.gateapi.api.AccountApi;
import io.gate.gateapi.models.AccountDetail;
import net.sf.json.JSONObject;
import toolPack.ioHandle.FileUtilCustom;

public class GateDemo {

	public GateKeysDTO getKeys() {
		FileUtilCustom f = new FileUtilCustom();
		String str = f.getStringFromFile("../../optionFile/cryptoCoinMonitor/cryptoCoin/option.json");
		JSONObject json = JSONObject.fromObject(str);
		GateKeysDTO dto = new GateKeysDTO();
		JSONObject user2 = json.getJSONObject("user2");
		dto.setKey(user2.getString("gateIoApiKey"));
		dto.setSec(user2.getString("gateIoSecretKey"));
		return dto;
	}

	public void getAccount() {
		GateKeysDTO keys = getKeys();
		
		ApiClient defaultClient = new ApiClient(keys.getKey(), keys.getSec());
		defaultClient.setBasePath("https://api.gateio.ws/api/v4");

		// Configure APIv4 authorization: apiv4
		defaultClient.setApiKeySecret(keys.getKey(), keys.getSec());

		AccountApi apiInstance = new AccountApi(defaultClient);
		try {
			AccountDetail result = apiInstance.getAccountDetail();
			System.out.println(result);
		} catch (GateApiException e) {
			System.err.println(
					String.format("Gate api exception, label: %s, message: %s", e.getErrorLabel(), e.getMessage()));
			e.printStackTrace();
		} catch (ApiException e) {
			System.err.println("Exception when calling AccountApi#getAccountDetail");
			System.err.println("Status code: " + e.getCode());
			System.err.println("Response headers: " + e.getResponseHeaders());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GateDemo t = new GateDemo();
		t.getAccount();
	}
}
