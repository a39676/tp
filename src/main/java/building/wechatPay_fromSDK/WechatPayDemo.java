package building.wechatPay_fromSDK;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.exception.ServiceException;
import com.wechat.pay.java.core.exception.ValidationException;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayResponse;

import net.sf.json.JSONObject;
import tool_package.SnowFlake;
import wechatPayApi.jsApi.pojo.dto.WechatPayJsApiPrepayDTO;
import wechatPayApi.jsApi.pojo.result.WechatPayJsApiPrepayResult;
import wechatSdk.pojo.dto.AiServiceBuyMembershipFromWechatAttachmentDTO;

public class WechatPayDemo {

	private static WechatPayDemoOption option;
	private static SnowFlake snowFlake = new SnowFlake();

	static {
		option = new WechatPayDemoOption();
		option.loadOption();
	}

	public WechatPayJsApiPrepayResult prepay(WechatPayJsApiPrepayDTO dto) {
		Config config = new RSAAutoCertificateConfig.Builder().merchantId(option.getMerchantId())
				.privateKey(option.getPrivateKey()).merchantSerialNumber(option.getMerchantSerialNumber())
				.apiV3Key(option.getApiV3key()).build();
		JsapiService service = new JsapiService.Builder().config(config).build();

		PrepayRequest request = new PrepayRequest();
		Amount amount = new Amount();
		amount.setTotal(dto.getAmount());

		request.setAmount(amount);
		request.setAppid(option.getAppId());
		request.setMchid(option.getMerchantId());
		request.setDescription(dto.getDescription());
		request.setNotifyUrl(dto.getNotifyUrl());
		Payer payer = new Payer();
		payer.setOpenid(dto.getPayerOpenId());
		request.setPayer(payer);
		request.setTimeExpire(dto.getExpireTimeStr());
		request.setOutTradeNo(String.valueOf(snowFlake.getNextId()));
		request.setAttach(JSONObject.fromObject(dto.getAttach()).toString());

		String prepayId = null;
		PrepayResponse response = null;
		WechatPayJsApiPrepayResult r = new WechatPayJsApiPrepayResult();
		try {
			response = service.prepay(request);
			System.out.println("response: " + response);
			prepayId = response.getPrepayId();
			r.setPrepayId(prepayId);
			r.setIsSuccess();
		} catch (ServiceException e1) {
			System.out.println(e1);
			e1.getResponseBody();
			r.setMessage(e1.getResponseBody());
		} catch (ValidationException e2) {
			r.setMessage("ValidationException");
		} catch (Exception e) {
			r.setMessage(e.getLocalizedMessage());
		}

		return r;
	}

	private WechatPayJsApiPrepayDTO createWechatPayJsApiPrepayDTO() {
		WechatPayJsApiPrepayDTO prepayDTO = new WechatPayJsApiPrepayDTO();
		prepayDTO.setPayerOpenId("oXt5d5l_w5Fjl7IyaQ1SF7fTjS1gs");
		prepayDTO.setDescription("testing jsapi prepay");
		prepayDTO.setNotifyUrl("https://demo.com");
		prepayDTO.setOutTradeNo(String.valueOf(snowFlake.getNextId()));
		prepayDTO.setExpireTimeStr("2023-03-15T18:00:00Z");
		prepayDTO.setAttach(new AiServiceBuyMembershipFromWechatAttachmentDTO());
		prepayDTO.setAmount(1);
		return prepayDTO;
	}

	public static void main(String[] args) {
		WechatPayDemo o = new WechatPayDemo();
		WechatPayJsApiPrepayDTO prepayDTO = o.createWechatPayJsApiPrepayDTO();
		WechatPayJsApiPrepayResult r = o.prepay(prepayDTO);
		System.err.println("feed back: " + r.getPrepayId() + ", error msg: " + r.getMessage());
	}
}
