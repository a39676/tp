package job_test.east.urlTest.xkMall;

import java.util.List;

import job_test.east.urlTest.commonAuxiliary.Auxiliary;
import job_test.east.urlTest.commonAuxiliary.UrlTestDomain;

/**
 * 东方星空,url测试工具
 * 基础数据, 基础逻辑部分, 通用变量部分
 */
public class XkMallAuxiliary extends Auxiliary{
	
	public static List<UrlTestDomain> loadXkMallUrlDomains() {
		UrlTestDomain domain = new UrlTestDomain();
		
		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.login);
		domain.setUrl(mainHost + "system/login/");
		domain.setParamData("{\"username\":\"admin\", \"password\":\"123456\"}");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.test);
		domain.setUrl(mainHost + "test/");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.getADList);
		domain.setUrl(mainHost + "operate/getADList/");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.addAD);
		domain.setUrl(mainHost + "operate/addAD");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.uploadADPicture);
		domain.setUrl(mainHost + "operate/uploadADPicture");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.getADInfo);
		domain.setUrl(mainHost + "operate/getADInfo");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.editAD);
		domain.setUrl(mainHost + "operate/editAD");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.deleteAD);
		domain.setUrl(mainHost + "operate/deleteAD");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.getRedPacketList);
		domain.setUrl(mainHost + "operate/getRedPacketList");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.addRedPacket);
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
		domain.setDomainName(XkMallUrlTestConstant.getRedPacketDetail);
		domain.setUrl(mainHost + "operate/getRedPacketDetail");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.editRedPacket);
		domain.setUrl(mainHost + "operate/editRedPacket");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.redPacketList);
		domain.setUrl(mainHost + "operate/redPacketList");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.sendRedPacket);
		domain.setUrl(mainHost + "operate/sendRedPacket");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.sendRedPacketRecordList);
		domain.setUrl(mainHost + "operate/sendRedPacketRecordList");
		testUrlDomains.add(domain);
		
		// 高级认证部分
		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.getAdvancedManageList);
		domain.setUrl(mainHost + "risk/getAdvancedManageList");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.editAdvancedSwitch);
		domain.setUrl(mainHost + "risk/editAdvancedSwitch");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.editAdvancedLimit);
		domain.setUrl(mainHost + "risk/editAdvancedLimit");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.editAdvancedForceInfo);
		domain.setUrl(mainHost + "risk/editAdvancedForceInfo");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.editAdvancedMultiInfo);
		domain.setUrl(mainHost + "risk/editAdvancedMultiInfo");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.getProductList);
		domain.setUrl(mainHost + "operate/getProductList");
		domain.insertKeyValue("pageNo", "1").insertKeyValue("pageSize", "10");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.createProduct);
		domain.setUrl(mainHost + "operate/createProduct");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.getProductDetails);
		domain.setUrl(mainHost + "operate/getProductDetails");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.updateProduct);
		domain.setUrl(mainHost + "operate/updateProduct");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(XkMallUrlTestConstant.deleteProduct);
		domain.setUrl(mainHost + "operate/deleteProduct");
		testUrlDomains.add(domain);

		return testUrlDomains;
	}
	
	static {{
	}}
	
}
