package job_test.east.urlTest.ttqd;

import java.util.List;

import job_test.east.urlTest.commonAuxiliary.Auxiliary;
import job_test.east.urlTest.commonAuxiliary.UrlTestDomain;

/**
 * 东方星空,url测试工具 基础数据, 基础逻辑部分, 通用变量部分
 */
public class TtqdAuxiliary extends Auxiliary {

	public static List<UrlTestDomain> loadTtqdUrlDomains() {
		UrlTestDomain domain;

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getStartupAd);
		domain.setUrl(mainHost + "api/app/v1/pub/getStartupAd");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getGuidePageList);
		domain.setUrl(mainHost + "api/app/v1/pub/getGuidePageList");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.checkVersionUpdate);
		domain.setUrl(mainHost + "api/app/v1/pub/checkVersionUpdate");
		domain.setParamData("{\"version\":\"0\", \"platform\":\"1\", \"status\":\"0\", \"des\":\"\"}");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getAppVersionFunction);
		domain.setUrl(mainHost + "api/app/v1/pub/getAppVersionFunction");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getAppVersionType);
		domain.setUrl(mainHost + "api/app/v1/pub/getAppVersionType");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.login);
		domain.setUrl(mainHost + "api/app/v1/user/login");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.overview);
		domain.setUrl(mainHost + "api/app/v1/user/overview");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getInfo);
		domain.setUrl(mainHost + "api/app/v1/user/getInfo");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.saveDetail);
		domain.insertKeyValue("detail",
				"{\"idCard\":\"440111199001010006\",\"address\":\"测试地址符合长度\",\"educationId\":\"1\",\"careerId\":\"1\"}");
		domain.setUrl(mainHost + "api/app/v1/user/saveDetail");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.logout);
		domain.setUrl(mainHost + "api/app/v1/user/logout");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.sendLoginSms);
		domain.setParamData("{\\\"mobile\\\":\\\"\" + DEFAULTUSERMOBILE + \"\\\", \\\"type\\\":\\\"1\\\"}");
		domain.setUrl(mainHost + "api/app/v1/pub/sendLoginSms");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getHelpUrl);
		domain.setUrl(mainHost + "api/app/v1/pub/getHelpUrl");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getAboutUs);
		domain.setUrl(mainHost + "api/app/v1/pub/getAboutUs");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getEducationList);
		domain.setUrl(mainHost + "api/app/v1/pub/getEducationList");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getCareerList);
		domain.setUrl(mainHost + "api/app/v1/pub/getCareerList");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.statisticsInstall);
		domain.setUrl(mainHost + "api/app/v1/pub/statisticsInstall");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getIndexAdTagList);
		domain.setUrl(mainHost + "api/app/v1/product/getIndexAdTagList");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getIndexList);
		domain.setUrl(mainHost + "api/app/v1/product/getIndexList");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getThemeInfo);
		domain.setUrl(mainHost + "api/app/v1/product/getThemeInfo");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getDetailInfo);
		domain.setUrl(mainHost + "api/app/v1/product/getDetailInfo");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getApplyUrl);
		domain.setUrl(mainHost + "api/app/v1/product/getApplyUrl");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.collect);
		domain.setUrl(mainHost + "api/app/v1/product/collect");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getSearchParam);
		domain.setUrl(mainHost + "api/app/v1/product/getSearchParam");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.search);
		domain.setUrl(mainHost + "api/app/v1/product/search");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getRecommendList);
		domain.setUrl(mainHost + "api/app/v1/product/getRecommendList");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getMyCollectList);
		domain.setUrl(mainHost + "api/app/v1/product/getMyCollectList");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.getAllTagProductList);
		domain.setUrl(mainHost + "api/app/v1/product/getAllTagProductList");
		testUrlDomains.add(domain);

		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.redirectToApplyUrl);
		domain.setUrl(mainHost + "api/app/v1/product/redirectToApplyUrl");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.uploadPayoutPicture);
		domain.setUrl(mainHost + "api/app/v1/payout/uploadPayoutApplyImage");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.findInformationMainTagOrderByWeights);
		domain.setUrl(mainHost + "api/app/v1/information/findInformationMainTagOrderByWeights");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.findInformationSubTagByInformationMainTagIds);
		domain.setUrl(mainHost + "api/app/v1/information/findInformationSubTagByInformationMainTagIds");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.findInformationVOMapByMainTagId);
		domain.setUrl(mainHost + "api/app/v1/information/findInformationVOMapByMainTagId");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.findInformationHot);
		domain.setUrl(mainHost + "api/app/v1/information/findInformationHot");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.findInformationVOMap);
		domain.setUrl(mainHost + "api/app/v1/information/findInformationVOMap");
		testUrlDomains.add(domain);
		
		domain = new UrlTestDomain();
		domain.setDomainName(TtqdUrlTestConstant.updateShowDC);
		domain.setUrl(mainHost + "api/app/v1/information/updateShowDC");
		testUrlDomains.add(domain);

		return testUrlDomains;
	}

	static {
	}


}
