package job_test.east.urlTest.xkMall;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import job_test.east.EastHttpTool;
import job_test.east.urlTest.commonAuxiliary.UrlTestConstant;
import job_test.east.urlTest.commonAuxiliary.UrlTestDomain;
import job_test.east.urlTest.xkMall.admin.entity.vo.result.operate.CashProductVO;
import net.sf.json.JSONObject;

public class XkMallUrlTestMain {

	public static XkMallAuxiliary eta = new XkMallAuxiliary();
	public static EastHttpTool eHttpTool = new EastHttpTool();

	public boolean userLogin() throws UnsupportedEncodingException {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.login);
		String result = sendPost(ed);
		eta.saveIdToken(result);

		return eta.isStatus0(result);
	}

	public void insertManagerId(UrlTestDomain ed) {
		JSONObject json = JSONObject.fromObject(ed.getParamData());
		json.put("managerId", eta.getId());
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
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.addAD);
		insertManagerId(ed);
		ed.insertKeyValue("adIdx", adIdx.toString())
		.insertKeyValue("srcType", srcType.toString())
		.insertKeyValue("src", src)
		.insertKeyValue("adType", adType.toString())
		.insertKeyValue("online", String.valueOf(online));
		
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String test() {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.test);
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String getADList(Integer pageNo, Integer pageSize, Integer adType) {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.getADList);
		insertManagerId(ed);
		ed.insertKeyValue("pageNo", pageNo.toString())
		.insertKeyValue("pageSize", pageSize.toString())
		.insertKeyValue("adType", adType.toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String uploadADPicture(byte[] pictureBase64) {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.uploadADPicture);
		insertManagerId(ed);
		ed.insertKeyValue("picture", new String(pictureBase64));
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String getADInfo(Integer adId, Integer adType) {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.getADInfo);
		insertManagerId(ed);
		ed.insertKeyValue("id", adId.toString())
		.insertKeyValue("adType", adType.toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String editAD(Integer adId, Integer adIdx, String image, Integer srcType, String src, Integer adType,
			Boolean online) {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.editAD);
		insertManagerId(ed);
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
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.deleteAD);
		insertManagerId(ed);
		ed.insertKeyValue("id", adId.toString())
		.insertKeyValue("adType", adType.toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String addRedPacket() {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.addRedPacket);
		insertManagerId(ed);
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
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.editRedPacket);
		insertManagerId(ed);
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
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.getRedPacketDetail);
		insertManagerId(ed);
		ed.insertKeyValue("redPacketId", redPacketId.toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String redPacketList() {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.redPacketList);
		insertManagerId(ed);
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public String sendRedPacket() {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.sendRedPacket);
		insertManagerId(ed);
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
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.sendRedPacketRecordList);
		insertManagerId(ed);
		ed.insertKeyValue("pageNo", "1")
		.insertKeyValue("pageSize", "10")
		.insertKeyValue("startTime", "2017-01-01 00:00:01")
		.insertKeyValue("endTime", "2019-01-01 00:00:01");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String getAdvancedManageList() {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.getAdvancedManageList);
		insertManagerId(ed);
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String editAdvancedSwitch() {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.editAdvancedSwitch);
		insertManagerId(ed);
		ed.insertKeyValue("infos", "[{\"authType\":\"1\",\"authName\":\"芝麻信用\",\"authStatus\":\"true\",\"authLimit\":\"0\",\"passRate\":\"0\"},{\"authType\":\"2\",\"authName\":\"运营商\",\"authStatus\":\"true\",\"authLimit\":\"30\",\"passRate\":\"30\"}]");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	
	public String editAdvancedLimit() {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.editAdvancedLimit);
		insertManagerId(ed);
		ed.insertKeyValue("infos", "[{\"authType\":\"1\",\"authName\":\"芝麻信用\",\"authStatus\":\"true\",\"authLimit\":\"10\",\"passRate\":\"3\"},{\"authType\":\"2\",\"authName\":\"运营商\",\"authStatus\":\"true\",\"authLimit\":\"30\",\"passRate\":\"30\"}]");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	
	public String editAdvancedForceInfo() {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.editAdvancedForceInfo);
		insertManagerId(ed);
		ed.insertKeyValue("forceInfo", "{\"flag\":\"true\", \"info\":{\"authType\":\"1\",\"authName\":\"芝麻信用\",\"authStatus\":\"true\",\"authLimit\":\"0\",\"passRate\":\"0\"}}");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	
	public String editAdvancedMultiInfo() {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.editAdvancedMultiInfo);
		insertManagerId(ed);
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
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.getProductList);
		insertManagerId(ed);
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String createProduct() {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.createProduct);
		insertManagerId(ed);
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
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.getProductDetails);
		insertManagerId(ed);
		ed.insertKeyValue("id", "2");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String updateProduct() {
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.updateProduct);
		insertManagerId(ed);
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
		UrlTestDomain ed = eta.getTestUrl(XkMallUrlTestConstant.deleteProduct);
		insertManagerId(ed);
		ed.insertKeyValue("id", "1");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}

	public static void main(String[] args) throws IOException {
		XkMallAuxiliary.setProject(UrlTestConstant.xkMall);

		XkMallUrlTestMain em = new XkMallUrlTestMain();
		System.out.println("login status: " + em.userLogin());
		System.out.println(new XkMallAuxiliary().getToken());

//		 System.out.println(em.addAD(adIdx, image, srcType, src, adType, online));
//		System.out.println(em.addAD(1, "http://img0.imgtn.bdimg.com/it/u=1886452188,480973557&fm=27&gp=0.jpg", 2,
//				"http://img0.imgtn.bdimg.com/it/u=1886452188,480973557&fm=27&gp=0.jpg", 1, false));
//		System.out.println(em.addAD(1, "http://img0.imgtn.bdimg.com/it/u=1886452188,480973557&fm=27&gp=0.jpg", 2,
//				"http://img0.imgtn.bdimg.com/it/u=1886452188,480973557&fm=27&gp=0.jpg", 2, true));
//		System.out.println(em.addAD(1, "http://img0.imgtn.bdimg.com/it/u=1886452188,480973557&fm=27&gp=0.jpg", 2,
//				"http://img0.imgtn.bdimg.com/it/u=1886452188,480973557&fm=27&gp=0.jpg", 3, true));

		// em.editAD(adId, adIdx, image, srcType, src, adType, online)
//		System.out.println(
//				em.editAD(2, 2, "http://img0.imgtn.bdimg.com/0.jpg", 2, "http://img0.imgtn.bdimg.com/0.jpg", 3, false)); // ok

		// System.out.println(em.getADList(pageNo, pageSize, adType));
//		System.out.println(em.getADList(1, 30, 1)); // ok
		// System.out.println(em.getADList(1, 10, 2));
		// System.out.println(em.getADList(1, 10, 3));
		// System.out.println(em.getADList(1, 10, 4));

//		System.out.println(em.deleteAD(1, 1)); // ok

//		 FileUtilCustom io = new FileUtilCustom();
//		 byte[] picture = io.encodeFileToBase64("d:/auxiliary/east/01.jpg");
//		 System.out.println(em.uploadADPicture(picture)); // ok

//		System.out.println(em.getADInfo(2, 3)); // ok

//		System.out.println(em.addRedPacket()); // ok

//		System.out.println(em.getRedPacketDetail(2L)); // ok
//		System.out.println(em.getRedPacketDetail(7L)); // ok
//		System.out.println(em.redPacketList()); // ok
//		System.out.println(em.sendRedPacket()); // ok
//		System.out.println(em.sendRedPacketRecordList()); // ok

//		System.out.println(em.editRedPacket()); // ok
		
//		System.out.println(em.editAdvancedSwitch()); // ok 
//		System.out.println(em.editAdvancedLimit()); // ok
//		System.out.println(em.editAdvancedForceInfo()); // ok
//		System.out.println(em.editAdvancedMultiInfo()); // ok
//		System.out.println(em.getAdvancedManageList()); // ok
		
//		System.out.println(em.createProduct()); // ok
//		System.out.println(em.updateProduct()); // ok
//		System.out.println(em.getProductDetails()); // ok
//		System.out.println(em.deleteProduct()); // ok
//		System.out.println(em.getProductList()); // ok
	}

}
