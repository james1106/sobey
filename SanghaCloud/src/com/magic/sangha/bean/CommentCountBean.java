package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  评论统计类 业务实体
 * @author QimouXie
 *
 */
public class CommentCountBean implements Serializable {

	private static final long serialVersionUID = -7440289247849650563L;
	
	/**员工ID*/
	private Integer groupUserId;
	
	/**员工姓名*/
	private String userName;
	
	/**公司ID*/
	private Integer companyId;
	
	/**公司名称*/
	private String companyName;
	
	/**办事处ID*/
	private Integer officeId;
	
	/**办事处名称*/
	private String officeName;
	
	/**订单总数*/
	private Integer orderCount;
	
	/**总评论得分*/
	private Integer bonusCount;
	
	/**处理速度分*/
	private Integer disposeSpeedCount;
	
	/**服务态度分*/
	private Integer serviceAttitudeCount;
	
	/**客服 服务态度得分*/
	private Integer serviceServerAttitudeCount;
	/**客服 处理速度得分*/
	private Integer serviceDisposeSpeedCount;
	
	/**TSC 服务态度得分*/
	private Integer tscServerAttitudeCount;
	/**TSC 处理速度得分*/
	private Integer tscDisposeSpeedCount;
	/**产品得分*/
	private Integer productCount;
	/**总部技术 服务态度*/
	private Integer headTechServerAttitudeCount;
	/**总部技术 处理速度*/
	private Integer headTechDisposeSpeedCount;
	/**研发服务态度*/
	private Integer developServerAttitudeCount;
	/**研发处理速度*/
	private Integer developDisposeSpeedCount;

	/**订单ID*/
	private Integer orderId;
	
	/**标签*/
	private List<LableBean> lables;
	
	/**客服标签*/
	private List<LableBean> serviceLables;
	/**TSC标签*/
	private List<LableBean> tscLables;
	
	/**订单号*/
	private String orderNumber;
	
	/**评价内容*/
	private String content;
	
	/**评价时间*/
	private Date createDate;
	
	/**用户ID*/
	private Integer userId;
	
	public Integer getHeadTechServerAttitudeCount() {
		return headTechServerAttitudeCount;
	}

	public void setHeadTechServerAttitudeCount(Integer headTechServerAttitudeCount) {
		this.headTechServerAttitudeCount = headTechServerAttitudeCount;
	}

	public Integer getHeadTechDisposeSpeedCount() {
		return headTechDisposeSpeedCount;
	}

	public void setHeadTechDisposeSpeedCount(Integer headTechDisposeSpeedCount) {
		this.headTechDisposeSpeedCount = headTechDisposeSpeedCount;
	}

	public Integer getDevelopServerAttitudeCount() {
		return developServerAttitudeCount;
	}

	public void setDevelopServerAttitudeCount(Integer developServerAttitudeCount) {
		this.developServerAttitudeCount = developServerAttitudeCount;
	}

	public Integer getDevelopDisposeSpeedCount() {
		return developDisposeSpeedCount;
	}

	public void setDevelopDisposeSpeedCount(Integer developDisposeSpeedCount) {
		this.developDisposeSpeedCount = developDisposeSpeedCount;
	}

	public List<LableBean> getServiceLables() {
		return serviceLables;
	}

	public void setServiceLables(List<LableBean> serviceLables) {
		this.serviceLables = serviceLables;
	}

	public List<LableBean> getTscLables() {
		return tscLables;
	}

	public void setTscLables(List<LableBean> tscLables) {
		this.tscLables = tscLables;
	}

	public Integer getServiceServerAttitudeCount() {
		return serviceServerAttitudeCount;
	}

	public void setServiceServerAttitudeCount(Integer serviceServerAttitudeCount) {
		this.serviceServerAttitudeCount = serviceServerAttitudeCount;
	}

	public Integer getServiceDisposeSpeedCount() {
		return serviceDisposeSpeedCount;
	}

	public void setServiceDisposeSpeedCount(Integer serviceDisposeSpeedCount) {
		this.serviceDisposeSpeedCount = serviceDisposeSpeedCount;
	}

	public Integer getTscServerAttitudeCount() {
		return tscServerAttitudeCount;
	}

	public void setTscServerAttitudeCount(Integer tscServerAttitudeCount) {
		this.tscServerAttitudeCount = tscServerAttitudeCount;
	}

	public Integer getTscDisposeSpeedCount() {
		return tscDisposeSpeedCount;
	}

	public void setTscDisposeSpeedCount(Integer tscDisposeSpeedCount) {
		this.tscDisposeSpeedCount = tscDisposeSpeedCount;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<LableBean> getLables() {
		return lables;
	}

	public void setLables(List<LableBean> lables) {
		this.lables = lables;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getDisposeSpeedCount() {
		return disposeSpeedCount;
	}

	public void setDisposeSpeedCount(Integer disposeSpeedCount) {
		this.disposeSpeedCount = disposeSpeedCount;
	}

	public Integer getServiceAttitudeCount() {
		return serviceAttitudeCount;
	}

	public void setServiceAttitudeCount(Integer serviceAttitudeCount) {
		this.serviceAttitudeCount = serviceAttitudeCount;
	}

	public Integer getGroupUserId() {
		return groupUserId;
	}

	public void setGroupUserId(Integer groupUserId) {
		this.groupUserId = groupUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getBonusCount() {
		return bonusCount;
	}

	public void setBonusCount(Integer bonusCount) {
		this.bonusCount = bonusCount;
	}
	
	

}
