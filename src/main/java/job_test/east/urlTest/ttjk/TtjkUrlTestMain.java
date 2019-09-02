package job_test.east.urlTest.ttjk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import job_test.east.EastHttpTool;
import job_test.east.urlTest.commonAuxiliary.UrlTestConstant;
import job_test.east.urlTest.commonAuxiliary.UrlTestDomain;
import job_test.east.urlTest.ttjk.admin.entity.vo.param.operate.SystemNoticeInfo;
import job_test.east.urlTest.xkMall.admin.entity.vo.result.operate.CashProductVO;
import net.sf.json.JSONObject;

public class TtjkUrlTestMain {

	public static TtjkAuxiliary eta = new TtjkAuxiliary();
	public static EastHttpTool eHttpTool = new EastHttpTool();

	public boolean userLogin() throws UnsupportedEncodingException {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.login);
		String result = sendPost(ed);
		eta.saveIdToken(result);

		return eta.isStatus0(result);
	}

	public void insertOperatorId(UrlTestDomain ed) {
		JSONObject json = JSONObject.fromObject(ed.getParamData());
		json.put("operatorId", eta.getId());
		json.put("token", eta.getToken());
		ed.setParamData(json.toString());
	}

//	public void insertKeyValue(UrlTestDomain ed, String key, String value) {
//		JSONObject json = JSONObject.fromObject(ed.getParamData());
//		json.put(key, value);
//		ed.setParamData(json.toString());
//	}

	public String sendPost(UrlTestDomain ed) {
		return eHttpTool.sendPost(ed.getUrl(), ed.getParamData());
	}

	public String sendGet(UrlTestDomain ed) {
		return eHttpTool.sendGet(ed.getUrl());
	}

	public String addAD(Integer adIdx, String image, Integer srcType, String src, Integer adType, boolean online) {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.addAD);
		insertOperatorId(ed);
		ed.insertKeyValue("adIdx", adIdx.toString())
		.insertKeyValue("srcType", srcType.toString())
		.insertKeyValue("src", src)
		.insertKeyValue("adType", adType.toString())
		.insertKeyValue("online", String.valueOf(online));
		
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String test() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.test);
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String getADList(Integer pageNo, Integer pageSize, Integer adType) {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.getADList);
		insertOperatorId(ed);
		ed.insertKeyValue("pageNo", pageNo.toString())
		.insertKeyValue("pageSize", pageSize.toString())
		.insertKeyValue("adType", adType.toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String uploadADPicture(byte[] pictureBase64) {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.uploadADPicture);
		insertOperatorId(ed);
		ed.insertKeyValue("picture", new String(pictureBase64));
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String getADInfo(Integer adId, Integer adType) {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.getADInfo);
		insertOperatorId(ed);
		ed.insertKeyValue("id", adId.toString())
		.insertKeyValue("adType", adType.toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String editAD(Integer adId, Integer adIdx, String image, Integer srcType, String src, Integer adType,
			Boolean online) {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.editAD);
		insertOperatorId(ed);
		ed.insertKeyValue("id", adId.toString())
		.insertKeyValue("adIdx", adIdx.toString())
		.insertKeyValue("image", image.toString())
		.insertKeyValue("srcType", srcType.toString())
		.insertKeyValue("src", src.toString())
		.insertKeyValue("adType", adType.toString())
		.insertKeyValue("online", String.valueOf(online));
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String deleteAD(Integer adId, Integer adType) {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.deleteAD);
		insertOperatorId(ed);
		ed.insertKeyValue("id", adId.toString())
		.insertKeyValue("adType", adType.toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String addRedPacket() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.addRedPacket);
		insertOperatorId(ed);
		ed.insertKeyValue("redPacketInfo",
				"{\r\n" + "	\"redPacketName\":\"测试红包3\",\r\n" + "	\"redUseType\":\"1\",\r\n"
						+ "	\"redPacketDes\":\"测试红包3描述\",\r\n" + "	\"redEffectiveType\":\"2\",\r\n"
						+ "	\"takeDate\":\"1498838400000\",\r\n" + "	\"loseDate\":\"1540915200000\",\r\n"
						+ "	\"days\":\"5\",\r\n" + "	\"ticketDerateType\":\"5\",\r\n"
						+ "	\"ticketDeductType\":\"1\",\r\n" + "	\"ticketMoney\":\"56\",\r\n"
						+ "	\"ticketDiscount\":\"0\",\r\n" + "	\"ticketPeriodType\":\"1\",\r\n"
						+ "	\"derateDays\":\"3\",\r\n" + "	\"deratePeriods\":\"1\",\r\n"
						+ "	\"isMoneyLimit\":\"true\",\r\n" + "	\"limitLower\":\"10\",\r\n"
						+ "	\"limitUpper\":\"900\"\r\n" + "}");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String editRedPacket() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.editRedPacket);
		insertOperatorId(ed);
		ed.insertKeyValue("redPacketInfo",
				"{\r\n" + "	\"id\":\"4\",\r\n" + "	\"redPacketName\":\"测试红包改\",\r\n" + "	\"redUseType\":\"1\",\r\n"
						+ "	\"redPacketDes\":\"测试红包改描述\",\r\n" + "	\"redEffectiveType\":\"2\",\r\n"
						+ "	\"takeDate\":\"1498838400000\",\r\n" + "	\"loseDate\":\"1540915200000\",\r\n"
						+ "	\"days\":\"5\",\r\n" + "	\"ticketDerateType\":\"5\",\r\n"
						+ "	\"ticketDeductType\":\"1\",\r\n" + "	\"ticketMoney\":\"56\",\r\n"
						+ "	\"ticketDiscount\":\"0\",\r\n" + "	\"ticketPeriodType\":\"1\",\r\n"
						+ "	\"derateDays\":\"3\",\r\n" + "	\"deratePeriods\":\"1\",\r\n"
						+ "	\"isMoneyLimit\":\"true\",\r\n" + "	\"limitLower\":\"10\",\r\n"
						+ "	\"limitUpper\":\"900\",\r\n" + "	\"ticketUseType\":\"1\"\r\n" + "}");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String getRedPacketDetail(Long redPacketId) {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.getRedPacketDetail);
		insertOperatorId(ed);
		ed.insertKeyValue("redPacketId", redPacketId.toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String redPacketList() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.redPacketList);
		insertOperatorId(ed);
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String sendRedPacket() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.sendRedPacket);
		insertOperatorId(ed);
		ed.insertKeyValue("selectType", "1")
		.insertKeyValue("redPacketId", "1")
		.insertKeyValue("sendRedPacket", "true")
		.insertKeyValue("sendMessage", "false")
		.insertKeyValue("headLine", "一级标题")
		.insertKeyValue("subHead", "二级标题")
		.insertKeyValue("content", "正文内容")
		.insertKeyValue("sendTime", "2019-01-01 00:00:01");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String sendRedPacketRecordList() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.sendRedPacketRecordList);
		insertOperatorId(ed);
		ed.insertKeyValue("pageNo", "1")
		.insertKeyValue("pageSize", "10")
		.insertKeyValue("startTime", "2017-01-01 00:00:01")
		.insertKeyValue("endTime", "2019-01-01 00:00:01");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String getAdvancedManageList() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.getAdvancedManageList);
		insertOperatorId(ed);
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String editAdvancedSwitch() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.editAdvancedSwitch);
		insertOperatorId(ed);
		ed.insertKeyValue("infos", "[{\"authType\":\"1\",\"authName\":\"芝麻信用\",\"authStatus\":\"true\",\"authLimit\":\"0\",\"passRate\":\"0\"},{\"authType\":\"2\",\"authName\":\"运营商\",\"authStatus\":\"true\",\"authLimit\":\"30\",\"passRate\":\"30\"}]");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	
	public String editAdvancedLimit() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.editAdvancedLimit);
		insertOperatorId(ed);
		ed.insertKeyValue("infos", "[{\"authType\":\"1\",\"authName\":\"芝麻信用\",\"authStatus\":\"true\",\"authLimit\":\"10\",\"passRate\":\"3\"},{\"authType\":\"2\",\"authName\":\"运营商\",\"authStatus\":\"true\",\"authLimit\":\"30\",\"passRate\":\"30\"}]");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	
	public String editAdvancedForceInfo() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.editAdvancedForceInfo);
		insertOperatorId(ed);
		ed.insertKeyValue("forceInfo", "{\"flag\":\"true\", \"info\":{\"authType\":\"1\",\"authName\":\"芝麻信用\",\"authStatus\":\"true\",\"authLimit\":\"0\",\"passRate\":\"0\"}}");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	
	public String editAdvancedMultiInfo() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.editAdvancedMultiInfo);
		insertOperatorId(ed);
		ed.insertKeyValue("multiInfo", "{\r\n" + 
				"		\"flag\":\"true\",\r\n" + 
				"		\"infos\":[\r\n" + 
				"			{\"authType\":\"1\",\"authName\":\"芝麻信用\",\"authStatus\":\"true\",\"authLimit\":\"0\",\"passRate\":\"0\"},\r\n" + 
				"			{\"authType\":\"2\",\"authName\":\"运营商\",\"authStatus\":\"false\",\"authLimit\":\"0\",\"passRate\":\"0\"}\r\n" + 
				"		]\r\n" + 
				"	}");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String getProductList() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.getProductList);
		insertOperatorId(ed);
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String createProduct() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.createProduct);
		insertOperatorId(ed);
		CashProductVO vo = new CashProductVO();
		vo.setProductIndex(1);
		vo.setProductName("测试产品1");
		vo.setProductDes("测试产品1描述");
		vo.setProductLimitType(1);
		vo.setProductStatus(true);
		vo.setOnlineDate(1540915200000L);
		vo.setOfflineDate(1541915200000L);
		vo.setInterestPercentage(1);
		vo.setInterestFixation(2);
		vo.setManagerPercentage(1);
		vo.setManagerFixation(3);
		vo.setFactoragePercentage(1);
		vo.setFactorageFixation(2);
		vo.setFastPercentage(20);
		vo.setFastFixation(30);
		vo.setOverduePercentage(25);
		vo.setOverdueFixation(30);
		vo.setDerateCurrentPercentage(5);
		vo.setDerateCurrentFixation(1);
		vo.setDerateMultiPercentage(5);
		vo.setDerateMultiFixation(5);
		vo.setProductType(1);
		vo.setDays(3);
		vo.setPeriods(1);
		vo.setRepayType(1);
		vo.setProductServiceObject(2);
		vo.setLoanCountMin(1);
		vo.setLoanCountMax(10);
		ed.insertKeyValue("cashProduct", JSONObject.fromObject(vo).toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String getProductDetails() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.getProductDetails);
		insertOperatorId(ed);
		ed.insertKeyValue("id", "2");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String updateProduct() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.updateProduct);
		insertOperatorId(ed);
		CashProductVO vo = new CashProductVO();
		vo.setProductIndex(1);
		vo.setProductName("测试产品1改");
		vo.setProductDes("测试产品1描述");
		vo.setProductLimitType(1);
		vo.setProductStatus(true);
		vo.setOnlineDate(1540915200000L);
		vo.setOfflineDate(1541915200000L);
		vo.setInterestPercentage(1);
		vo.setInterestFixation(2);
		vo.setManagerPercentage(1);
		vo.setManagerFixation(3);
		vo.setFactoragePercentage(1);
		vo.setFactorageFixation(2);
		vo.setFastPercentage(20);
		vo.setFastFixation(30);
		vo.setOverduePercentage(25);
		vo.setOverdueFixation(30);
		vo.setDerateCurrentPercentage(5);
		vo.setDerateCurrentFixation(1);
		vo.setDerateMultiPercentage(5);
		vo.setDerateMultiFixation(5);
		vo.setProductType(1);
		vo.setDays(3);
		vo.setPeriods(1);
		vo.setRepayType(1);
		vo.setProductServiceObject(2);
		vo.setLoanCountMin(1);
		vo.setLoanCountMax(10);
		ed.insertKeyValue("cashProduct", JSONObject.fromObject(vo).toString()).insertKeyValue("id", "2");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String deleteProduct() {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.deleteProduct);
		insertOperatorId(ed);
		ed.insertKeyValue("id", "1");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String addSystemNotice(SystemNoticeInfo info) {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.addSystemNotice);
		insertOperatorId(ed);
		ed.insertKeyValue("info", JSONObject.fromObject(info).toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String getSystemNoticeList(Integer pageNo, Integer pageSize) {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.getSystemNoticeList);
		insertOperatorId(ed);
		ed.insertKeyValue("pageNo", pageNo.toString())
		.insertKeyValue("pageSize", pageSize.toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String systemNoticeDetail(Long noticeId) {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.systemNoticeDetail);
		insertOperatorId(ed);
		ed.insertKeyValue("noticeId", noticeId.toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	
	public String editSystemNotice(SystemNoticeInfo notice) {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.editSystemNotice);
		insertOperatorId(ed);
		ed.insertKeyValue("info", JSONObject.fromObject(notice).toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String deleteSystemNotice(Long noticeId) {
		UrlTestDomain ed = eta.getTestUrl(TtjkUrlTestConstant.deleteSystemNotice);
		insertOperatorId(ed);
		ed.insertKeyValue("noticeId", noticeId.toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	

	public static void main(String[] args) throws IOException {
		TtjkAuxiliary.setProject(UrlTestConstant.ttjk);

		TtjkUrlTestMain em = new TtjkUrlTestMain();
		System.out.println("login status: " + em.userLogin());
		System.out.println(new TtjkAuxiliary().getToken());
		
		em.uploadADPicture(null);

		
	}

}
