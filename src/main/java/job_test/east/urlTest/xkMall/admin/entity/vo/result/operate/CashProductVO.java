package job_test.east.urlTest.xkMall.admin.entity.vo.result.operate;

import job_test.east.urlTest.xkMall.common.entity.type.app.product.ProductLimitType;

public class CashProductVO {
	/** 产品id */
	private Long id;
	/** 产品排序号 */
	private Integer productIndex;
	/** 产品名称 */
	private String productName;
	/** 产品描述 */
	private String productDes;
	/** 日利率(比率,百万分之) */
	private Integer interestPercentage;
	/** 日利率(固定)(分) */
	private Integer interestFixation;
	/** 账号管理费(比率,百万分之) */
	private Integer managerPercentage;
	/** 账号管理费(固定)(分) */
	private Integer managerFixation;
	/** 征信服务费(比率,百万分之) */
	private Integer factoragePercentage;
	/** 征信服务费(固定)(分) */
	private Integer factorageFixation;
	/** 快速信审费(比率,百万分之) */
	private Integer fastPercentage;
	/** 快速信审费(固定)(分) */
	private Integer fastFixation;
	/** 日滞纳金(比率,百万分之) */
	private Integer overduePercentage;
	/** 日滞纳金(固定)(分) */
	private Integer overdueFixation;
	/** 提前还款当期减免利息(比率,万分之) */
	private Integer derateCurrentPercentage;
	/** 提前还款当期减免利息(固定)(分) */
	private Integer derateCurrentFixation;
	/** 提前还款多期减免利息(比率,万分之) */
	private Integer derateMultiPercentage;
	/** 提前还款多期减免利息(固定)(分) */
	private Integer derateMultiFixation;
	/** 固定天数 */
	private Integer days;
	/** 周期数 */
	private Integer periods;
	/** 产品类型:1:固定天数,2:自然月 {@link ProductType} */
	private Integer productType;
	/** 还款类型 */
	private Integer repayType;
	/** 产品状态:false:未上线,true:上线 */
	private Boolean productStatus;
	/** 额度类型 {@link ProductLimitType} */
	private Integer productLimitType;
	/** 定时上线时间 */
	private Long onlineDate;
	/** 定时下线时间 */
	private Long offlineDate;
	/** 产品服务对象:{link ProductServiceObjectType} */
	private Integer productServiceObject;
	/** 老用户借款次数下限 */
	private Integer loanCountMin;
	/** 老用户借款次数上限 */
	private Integer loanCountMax;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getProductIndex() {
		return productIndex;
	}

	public void setProductIndex(Integer productIndex) {
		this.productIndex = productIndex;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDes() {
		return productDes;
	}

	public void setProductDes(String productDes) {
		this.productDes = productDes;
	}

	public Integer getInterestPercentage() {
		return interestPercentage;
	}

	public void setInterestPercentage(Integer interestPercentage) {
		this.interestPercentage = interestPercentage;
	}

	public Integer getInterestFixation() {
		return interestFixation;
	}

	public void setInterestFixation(Integer interestFixation) {
		this.interestFixation = interestFixation;
	}

	public Integer getManagerPercentage() {
		return managerPercentage;
	}

	public void setManagerPercentage(Integer managerPercentage) {
		this.managerPercentage = managerPercentage;
	}

	public Integer getManagerFixation() {
		return managerFixation;
	}

	public void setManagerFixation(Integer managerFixation) {
		this.managerFixation = managerFixation;
	}

	public Integer getFactoragePercentage() {
		return factoragePercentage;
	}

	public void setFactoragePercentage(Integer factoragePercentage) {
		this.factoragePercentage = factoragePercentage;
	}

	public Integer getFactorageFixation() {
		return factorageFixation;
	}

	public void setFactorageFixation(Integer factorageFixation) {
		this.factorageFixation = factorageFixation;
	}

	public Integer getFastPercentage() {
		return fastPercentage;
	}

	public void setFastPercentage(Integer fastPercentage) {
		this.fastPercentage = fastPercentage;
	}

	public Integer getFastFixation() {
		return fastFixation;
	}

	public void setFastFixation(Integer fastFixation) {
		this.fastFixation = fastFixation;
	}

	public Integer getOverduePercentage() {
		return overduePercentage;
	}

	public void setOverduePercentage(Integer overduePercentage) {
		this.overduePercentage = overduePercentage;
	}

	public Integer getOverdueFixation() {
		return overdueFixation;
	}

	public void setOverdueFixation(Integer overdueFixation) {
		this.overdueFixation = overdueFixation;
	}

	public Integer getDerateCurrentPercentage() {
		return derateCurrentPercentage;
	}

	public void setDerateCurrentPercentage(Integer derateCurrentPercentage) {
		this.derateCurrentPercentage = derateCurrentPercentage;
	}

	public Integer getDerateCurrentFixation() {
		return derateCurrentFixation;
	}

	public void setDerateCurrentFixation(Integer derateCurrentFixation) {
		this.derateCurrentFixation = derateCurrentFixation;
	}

	public Integer getDerateMultiPercentage() {
		return derateMultiPercentage;
	}

	public void setDerateMultiPercentage(Integer derateMultiPercentage) {
		this.derateMultiPercentage = derateMultiPercentage;
	}

	public Integer getDerateMultiFixation() {
		return derateMultiFixation;
	}

	public void setDerateMultiFixation(Integer derateMultiFixation) {
		this.derateMultiFixation = derateMultiFixation;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getRepayType() {
		return repayType;
	}

	public void setRepayType(Integer repayType) {
		this.repayType = repayType;
	}

	public Boolean getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Boolean productStatus) {
		this.productStatus = productStatus;
	}

	public Integer getProductLimitType() {
		return productLimitType;
	}

	public void setProductLimitType(Integer productLimitType) {
		this.productLimitType = productLimitType;
	}

	public Long getOnlineDate() {
		return onlineDate;
	}

	public void setOnlineDate(Long onlineDate) {
		this.onlineDate = onlineDate;
	}

	public Long getOfflineDate() {
		return offlineDate;
	}

	public void setOfflineDate(Long offlineDate) {
		this.offlineDate = offlineDate;
	}

	public Integer getProductServiceObject() {
		return productServiceObject;
	}

	public void setProductServiceObject(Integer productServiceObject) {
		this.productServiceObject = productServiceObject;
	}

	public Integer getLoanCountMin() {
		return loanCountMin;
	}

	public void setLoanCountMin(Integer loanCountMin) {
		this.loanCountMin = loanCountMin;
	}

	public Integer getLoanCountMax() {
		return loanCountMax;
	}

	public void setLoanCountMax(Integer loanCountMax) {
		this.loanCountMax = loanCountMax;
	}

}
