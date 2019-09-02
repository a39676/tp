package job_test.east.urlTest.ttqd;

import java.io.UnsupportedEncodingException;
import java.util.List;

import job_test.east.EastHttpTool;
import job_test.east.urlTest.commonAuxiliary.UrlTestConstant;
import job_test.east.urlTest.commonAuxiliary.UrlTestDomain;
import net.sf.json.JSONObject;

public class TtqdUrlTestMain {
	
	public static TtqdAuxiliary eta = new TtqdAuxiliary();
	public static EastHttpTool eHttpTool = new EastHttpTool();
	
	public boolean sendLoginSms() {
		UrlTestDomain et = eta.getTestUrl(TtqdUrlTestConstant.sendLoginSms);
		String result = sendPost(et);
		return eta.isStatus0(result);
	}
	
	public boolean userLogin() throws UnsupportedEncodingException {
		if (!sendLoginSms()) {
			return false;
		}
		
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.login);
		insertKeyValue(ed, "mobile", UrlTestConstant.DEFAULTUSERMOBILE);
		insertKeyValue(ed, "vcode", UrlTestConstant.DEFAULTVCODE);
		String result = sendPost(ed);
		eta.saveIdToken(result);
		
		return eta.isStatus0(result);
	}

	public String getIndexAdTagList() {
		UrlTestDomain et = eta.getTestUrl(TtqdUrlTestConstant.getIndexAdTagList);
		insertUserId(et);
		return sendPost(et);
	}
	
	public String getDetailInfo(Long productId) {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.getDetailInfo);
		insertKeyValue(ed, "productId", productId.toString());
		return sendPost(ed);
	}
	
	public String getApplyUrl(Long productId) {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.getApplyUrl);
		insertUserId(ed);
		insertKeyValue(ed, "productId", productId.toString());
		return sendPost(ed);
	}
	
	/**
	 * 收藏/取消收藏产品
	 * @param productId
	 * @param type 1:收藏, 2:取消收藏
	 * @return
	 */
	public String collect(Long productId, Integer type) {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.collect);
		insertUserId(ed);
		insertKeyValue(ed, "productId", productId.toString());
		insertKeyValue(ed, "type", type.toString());
		return sendPost(ed);
	}

	public String getStartupAd() {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.getStartupAd);
		return sendGet(ed);
	}
	
	public String uploadADPicture(byte[] pictureBase64) {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.uploadPayoutPicture);
//		insertUserId(ed);
		ed.insertKeyValue("image", new String(pictureBase64))
		.insertKeyValue("phone", "13800138001")
		.insertKeyValue("platformName", "平台1");
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String updateShowDC(boolean flag) {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.updateShowDC);
		if(flag) {
			ed.insertKeyValue("showDC", "true");
		} else {
			ed.insertKeyValue("showDC", "false");
		}
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String findInformationMainTagOrderByWeights() {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.findInformationMainTagOrderByWeights);
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String findInformationSubTagByInformationMainTagIds(List<String> searchIds, String isOnlie) {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.findInformationSubTagByInformationMainTagIds);
		ed.insertKeyValue("pageNo", "1")
		.insertKeyValue("pageSize", "10")
		.insertKeyValue("searchIds", searchIds.toString())
		.insertKeyValue("isOnline", isOnlie);
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String findInformationVOMapByMainTagId(Integer mainTagId, Integer pageNo, Integer pageSize) {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.findInformationVOMapByMainTagId);
		ed.insertKeyValue("pageNo", pageNo.toString())
		.insertKeyValue("pageSize", pageSize.toString())
		.insertKeyValue("id", String.valueOf(mainTagId));
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String findInformationHot() {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.findInformationHot);
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public String findInformationVOMap(Long id) {
		UrlTestDomain ed = eta.getTestUrl(TtqdUrlTestConstant.findInformationVOMap);
		ed.insertKeyValue("id", id.toString());
		System.out.println(ed.getUrl());
		return sendPost(ed);
	}
	
	public void insertUserId(UrlTestDomain ed) {
		JSONObject json = JSONObject.fromObject(ed.getParamData());
		json.put("userId", eta.getId());
		json.put("token", eta.getToken());
		ed.setParamData(json.toString());
	}
	
	public void insertKeyValue(UrlTestDomain ed, String key, String value) {
		JSONObject json = JSONObject.fromObject(ed.getParamData());
		json.put(key, value);
		ed.setParamData(json.toString());
	}
	
	public String sendPost(UrlTestDomain ed) {
		return eHttpTool.sendPost(ed.getUrl(), ed.getParamData());
	}
	
	public String sendGet(UrlTestDomain ed) {
		return eHttpTool.sendGet(ed.getUrl());
	}
	
	
	public static void main(String[] args) throws Exception {
		TtqdAuxiliary.setProject(UrlTestConstant.ttqd);
		TtqdUrlTestMain em = new TtqdUrlTestMain();
//		em.userLogin();
////		
//		System.out.println(em.collect(2L, 2));
//		System.out.println(em.getApplyUrl(1L));
//		System.out.println(em.getStartupAd());
//		
//		EastUrlTestDomain ed = new EastUrlTestDomain();
//		ed.setUrl("http://localhost:8080/operation/task/getTaskList.action");
//		ed.setParamData("{\"pageNo\":\"1\", \"pageSize\":\"10\"}");
//		em.insertUserId(ed);
		
		
//		FileUtilCustom iou = new FileUtilCustom();
//		byte[] fileByte = iou.getByteFromFile("D:\\auxiliary\\tmp\\icon.jpg");
//		System.out.println(em.uploadADPicture(Base64.encodeBase64(fileByte)));
		
//		System.out.println(em.updateShowDC(true));
//		System.out.println(em.updateShowDC(false));
//		System.out.println(em.findInformationMainTagOrderByWeights());
//		System.out.println(em.findInformationSubTagByInformationMainTagIds(Arrays.asList("1","2","3"), "1"));
		System.out.println(em.findInformationVOMapByMainTagId(2, 1, 10));
//		System.out.println(em.findInformationHot());
//		System.out.println(em.findInformationVOMap(2L));
	} 

}
