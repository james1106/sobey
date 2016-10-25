package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  ����ͳ���� ҵ��ʵ��
 * @author QimouXie
 *
 */
public class CommentCountBean implements Serializable {

	private static final long serialVersionUID = -7440289247849650563L;
	
	/**Ա��ID*/
	private Integer groupUserId;
	
	/**Ա������*/
	private String userName;
	
	/**��˾ID*/
	private Integer companyId;
	
	/**��˾����*/
	private String companyName;
	
	/**���´�ID*/
	private Integer officeId;
	
	/**���´�����*/
	private String officeName;
	
	/**��������*/
	private Integer orderCount;
	
	/**�����۵÷�*/
	private Integer bonusCount;
	
	/**�����ٶȷ�*/
	private Integer disposeSpeedCount;
	
	/**����̬�ȷ�*/
	private Integer serviceAttitudeCount;
	
	/**�ͷ� ����̬�ȵ÷�*/
	private Integer serviceServerAttitudeCount;
	/**�ͷ� �����ٶȵ÷�*/
	private Integer serviceDisposeSpeedCount;
	
	/**TSC ����̬�ȵ÷�*/
	private Integer tscServerAttitudeCount;
	/**TSC �����ٶȵ÷�*/
	private Integer tscDisposeSpeedCount;
	/**��Ʒ�÷�*/
	private Integer productCount;
	/**�ܲ����� ����̬��*/
	private Integer headTechServerAttitudeCount;
	/**�ܲ����� �����ٶ�*/
	private Integer headTechDisposeSpeedCount;
	/**�з�����̬��*/
	private Integer developServerAttitudeCount;
	/**�з������ٶ�*/
	private Integer developDisposeSpeedCount;

	/**����ID*/
	private Integer orderId;
	
	/**��ǩ*/
	private List<LableBean> lables;
	
	/**�ͷ���ǩ*/
	private List<LableBean> serviceLables;
	/**TSC��ǩ*/
	private List<LableBean> tscLables;
	
	/**������*/
	private String orderNumber;
	
	/**��������*/
	private String content;
	
	/**����ʱ��*/
	private Date createDate;
	
	/**�û�ID*/
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
