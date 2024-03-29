package building.wechatPay_fromSDK.jsapi;

import static com.wechat.pay.java.core.http.UrlEncoder.urlEncode;
import static com.wechat.pay.java.core.util.GsonUtil.toJson;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.exception.HttpException;
import com.wechat.pay.java.core.exception.MalformedMessageException;
import com.wechat.pay.java.core.exception.ServiceException;
import com.wechat.pay.java.core.exception.ValidationException;
import com.wechat.pay.java.core.http.Constant;
import com.wechat.pay.java.core.http.DefaultHttpClientBuilder;
import com.wechat.pay.java.core.http.HostName;
import com.wechat.pay.java.core.http.HttpHeaders;
import com.wechat.pay.java.core.http.HttpMethod;
import com.wechat.pay.java.core.http.HttpRequest;
import com.wechat.pay.java.core.http.HttpResponse;
import com.wechat.pay.java.core.http.JsonRequestBody;
import com.wechat.pay.java.core.http.MediaType;
import com.wechat.pay.java.core.http.QueryParameter;
import com.wechat.pay.java.core.http.RequestBody;
import com.wechat.pay.java.service.payments.jsapi.model.CloseOrderRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayResponse;
import com.wechat.pay.java.service.payments.jsapi.model.QueryOrderByIdRequest;
import com.wechat.pay.java.service.payments.jsapi.model.QueryOrderByOutTradeNoRequest;
import com.wechat.pay.java.service.payments.model.Transaction;

import building.wechatPay_fromSDK.http.OkHttpClientAdapter;

public class JsapiCustomerService {
	private OkHttpClientAdapter httpClient;
	private HostName hostName = HostName.API;

	public JsapiCustomerService builder(Config config) {
		JsapiCustomerService obj = new JsapiCustomerService();
		obj.setHttpClient((OkHttpClientAdapter) new DefaultHttpClientBuilder().config(config).build());
		return obj;
	}

	public OkHttpClientAdapter getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(OkHttpClientAdapter httpClient) {
		this.httpClient = httpClient;
	}

	public HostName getHostName() {
		return hostName;
	}

	public void setHostName(HostName hostName) {
		this.hostName = hostName;
	}

	/**
	 * 关闭订单
	 *
	 * @param request 请求参数
	 * @throws HttpException             发送HTTP请求失败。例如构建请求参数失败、发送请求失败、I/O错误等。包含请求信息。
	 * @throws ValidationException       发送HTTP请求成功，验证微信支付返回签名失败。
	 * @throws ServiceException          发送HTTP请求成功，服务返回异常。例如返回状态码小于200或大于等于300。
	 * @throws MalformedMessageException 服务返回成功，content-type不为application/json、解析返回体失败。
	 */
	public void closeOrder(CloseOrderRequest request) {
		String requestPath = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out_trade_no}/close";

		CloseOrderRequest realRequest = request;
		// 添加 path param
		requestPath = requestPath.replace("{" + "out_trade_no" + "}", urlEncode(realRequest.getOutTradeNo()));

		if (this.hostName != null) {
			requestPath = requestPath.replaceFirst(HostName.API.getValue(), hostName.getValue());
		}
		HttpHeaders headers = new HttpHeaders();
		headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
		headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
		HttpRequest httpRequest = new HttpRequest.Builder().httpMethod(HttpMethod.POST).url(requestPath)
				.headers(headers).body(createRequestBody(realRequest)).build();
		httpClient.execute(httpRequest, null);
	}

	/**
	 * JSAPI支付下单
	 *
	 * @param request 请求参数
	 * @return PrepayResponse
	 * @throws HttpException             发送HTTP请求失败。例如构建请求参数失败、发送请求失败、I/O错误等。包含请求信息。
	 * @throws ValidationException       发送HTTP请求成功，验证微信支付返回签名失败。
	 * @throws ServiceException          发送HTTP请求成功，服务返回异常。例如返回状态码小于200或大于等于300。
	 * @throws MalformedMessageException 服务返回成功，content-type不为application/json、解析返回体失败。
	 */
	public PrepayResponse prepay(PrepayRequest request) {
		String requestPath = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";
		PrepayRequest realRequest = request;
		if (this.hostName != null) {
			requestPath = requestPath.replaceFirst(HostName.API.getValue(), hostName.getValue());
		}
		HttpHeaders headers = new HttpHeaders();
		headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
		headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
		HttpRequest httpRequest = new HttpRequest.Builder().httpMethod(HttpMethod.POST).url(requestPath)
				.headers(headers).body(createRequestBody(realRequest)).build();
		HttpResponse<PrepayResponse> httpResponse = httpClient.execute(httpRequest, PrepayResponse.class);
		return httpResponse.getServiceResponse();
	}

	/**
	 * 微信支付订单号查询订单
	 *
	 * @param request 请求参数
	 * @return Transaction
	 * @throws HttpException             发送HTTP请求失败。例如构建请求参数失败、发送请求失败、I/O错误等。包含请求信息。
	 * @throws ValidationException       发送HTTP请求成功，验证微信支付返回签名失败。
	 * @throws ServiceException          发送HTTP请求成功，服务返回异常。例如返回状态码小于200或大于等于300。
	 * @throws MalformedMessageException 服务返回成功，content-type不为application/json、解析返回体失败。
	 */
	public Transaction queryOrderById(QueryOrderByIdRequest request) {
		String requestPath = "https://api.mch.weixin.qq.com/v3/pay/transactions/id/{transaction_id}";

		QueryOrderByIdRequest realRequest = request;
		// 添加 path param
		requestPath = requestPath.replace("{" + "transaction_id" + "}", urlEncode(realRequest.getTransactionId()));

		// 添加 query param
		QueryParameter queryParameter = new QueryParameter();
		if (realRequest.getMchid() != null) {
			queryParameter.add("mchid", urlEncode(realRequest.getMchid()));
		}
		requestPath += queryParameter.getQueryStr();
		if (this.hostName != null) {
			requestPath = requestPath.replaceFirst(HostName.API.getValue(), hostName.getValue());
		}
		HttpHeaders headers = new HttpHeaders();
		headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
		headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
		HttpRequest httpRequest = new HttpRequest.Builder().httpMethod(HttpMethod.GET).url(requestPath).headers(headers)
				.build();
		HttpResponse<Transaction> httpResponse = httpClient.execute(httpRequest, Transaction.class);
		return httpResponse.getServiceResponse();
	}

	/**
	 * 商户订单号查询订单
	 *
	 * @param request 请求参数
	 * @return Transaction
	 * @throws HttpException             发送HTTP请求失败。例如构建请求参数失败、发送请求失败、I/O错误等。包含请求信息。
	 * @throws ValidationException       发送HTTP请求成功，验证微信支付返回签名失败。
	 * @throws ServiceException          发送HTTP请求成功，服务返回异常。例如返回状态码小于200或大于等于300。
	 * @throws MalformedMessageException 服务返回成功，content-type不为application/json、解析返回体失败。
	 */
	public Transaction queryOrderByOutTradeNo(QueryOrderByOutTradeNoRequest request) {
		String requestPath = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out_trade_no}";

		QueryOrderByOutTradeNoRequest realRequest = request;
		// 添加 path param
		requestPath = requestPath.replace("{" + "out_trade_no" + "}", urlEncode(realRequest.getOutTradeNo()));

		// 添加 query param
		QueryParameter queryParameter = new QueryParameter();
		if (realRequest.getMchid() != null) {
			queryParameter.add("mchid", urlEncode(realRequest.getMchid()));
		}
		requestPath += queryParameter.getQueryStr();
		if (this.hostName != null) {
			requestPath = requestPath.replaceFirst(HostName.API.getValue(), hostName.getValue());
		}
		HttpHeaders headers = new HttpHeaders();
		headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
		headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
		HttpRequest httpRequest = new HttpRequest.Builder().httpMethod(HttpMethod.GET).url(requestPath).headers(headers)
				.build();
		HttpResponse<Transaction> httpResponse = httpClient.execute(httpRequest, Transaction.class);
		return httpResponse.getServiceResponse();
	}

	private RequestBody createRequestBody(Object request) {
		return new JsonRequestBody.Builder().body(toJson(request)).build();
	}
}
