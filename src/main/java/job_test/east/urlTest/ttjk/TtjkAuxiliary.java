package job_test.east.urlTest.ttjk;

import java.util.List;

import job_test.east.urlTest.commonAuxiliary.Auxiliary;
import job_test.east.urlTest.commonAuxiliary.UrlTestDomain;

/**
 * 东方星空,url测试工具
 * 基础数据, 基础逻辑部分, 通用变量部分
 */
public class TtjkAuxiliary extends Auxiliary {
	
	public static List<UrlTestDomain> loadTtjkUrlDomains() {
		UrlTestDomain domain = new UrlTestDomain();
		mainHost = "http://localhost:9002/admin/";
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.login);
		domain.setUrl(mainHost + "system/login/");
		domain.setParamData("{\"username\":\"admin\", \"password\":\"123456\"}");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.test);
		domain.setUrl(mainHost + "operate/test/");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.getADList);
		domain.setUrl(mainHost + "operate/getADList/");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.addAD);
		domain.setUrl(mainHost + "operate/addAD");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.uploadADPicture);
		domain.setUrl(mainHost + "operate/uploadADPicture");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.getADInfo);
		domain.setUrl(mainHost + "operate/getADInfo");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.editAD);
		domain.setUrl(mainHost + "operate/editAD");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.deleteAD);
		domain.setUrl(mainHost + "operate/deleteAD");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.getRedPacketList);
		domain.setUrl(mainHost + "operate/getRedPacketList");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.addRedPacket);
		domain.setUrl(mainHost + "operate/addRedPacket");
		domain.setParamData("\"redPacketInfo\":\"{\r\n" 
			+ "	\"redPacketName\":\"测试红包3\",\r\n" 
			+ "	\"redUseType\":\"1\",\r\n"
			+ "	\"redPacketDes\":\"测试红包3描述\",\r\n" 
			+ "	\"redEffectiveType\":\"2\",\r\n"
			+ "	\"takeDate\":\"1498838400000\",\r\n" 
			+ "	\"loseDate\":\"1540915200000\",\r\n"
			+ "	\"days\":\"5\",\r\n" 
			+ "	\"ticketDerateType\":\"5\",\r\n"
			+ "	\"ticketDeductType\":\"1\",\r\n" 
			+ "	\"ticketMoney\":\"56\",\r\n"
			+ "	\"ticketDiscount\":\"0\",\r\n" 
			+ "	\"ticketPeriodType\":\"1\",\r\n"
			+ "	\"derateDays\":\"3\",\r\n" 
			+ "	\"deratePeriods\":\"1\",\r\n"
			+ "	\"isMoneyLimit\":\"true\",\r\n" 
			+ "	\"limitLower\":\"10\",\r\n"
			+ "	\"limitUpper\":\"900\"\r\n" 
			+ "}");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.getRedPacketDetail);
		domain.setUrl(mainHost + "operate/getRedPacketDetail");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.editRedPacket);
		domain.setUrl(mainHost + "operate/editRedPacket");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.redPacketList);
		domain.setUrl(mainHost + "operate/redPacketList");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.sendRedPacket);
		domain.setUrl(mainHost + "operate/sendRedPacket");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.sendRedPacketRecordList);
		domain.setUrl(mainHost + "operate/sendRedPacketRecordList");
		testUrlDomains.add(domain);
		
		// 高级认证部分
		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.getAdvancedManageList);
		domain.setUrl(mainHost + "risk/getAdvancedManageList");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.editAdvancedSwitch);
		domain.setUrl(mainHost + "risk/editAdvancedSwitch");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.editAdvancedLimit);
		domain.setUrl(mainHost + "risk/editAdvancedLimit");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.editAdvancedForceInfo);
		domain.setUrl(mainHost + "risk/editAdvancedForceInfo");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.editAdvancedMultiInfo);
		domain.setUrl(mainHost + "risk/editAdvancedMultiInfo");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.getProductList);
		domain.setUrl(mainHost + "operate/getProductList");
		domain.insertKeyValue("pageNo", "1").insertKeyValue("pageSize", "10");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.createProduct);
		domain.setUrl(mainHost + "operate/createProduct");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.getProductDetails);
		domain.setUrl(mainHost + "operate/getProductDetails");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.updateProduct);
		domain.setUrl(mainHost + "operate/updateProduct");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.deleteProduct);
		domain.setUrl(mainHost + "operate/deleteProduct");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.addSystemNotice);
		domain.setUrl(mainHost + "operate/addSystemNotice");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.getSystemNoticeList);
		domain.setUrl(mainHost + "operate/getSystemNoticeList");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.systemNoticeDetail);
		domain.setUrl(mainHost + "operate/systemNoticeDetail");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.editSystemNotice);
		domain.setUrl(mainHost + "operate/editSystemNotice");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtjkUrlTestConstant.deleteSystemNotice);
		domain.setUrl(mainHost + "operate/deleteSystemNotice");
		testUrlDomains.add(domain);

		return testUrlDomains;
	}
	
	static {{
	}}
	
	
}
