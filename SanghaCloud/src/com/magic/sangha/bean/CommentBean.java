package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 *  ����Bean
 * @author QimouXie
 *
 */
public class CommentBean implements Serializable {

	private static final long serialVersionUID = -6466175162938586199L;
	
	/**����ID*/
	private Integer id;
	
	/**�ͷ�����*/
	private String serviceData;
	/**�ͷ�JSON����*/
	private JSONObject jsServiceData;
	
	/**TSC����*/
	private String TSCData;
	/**TSC JSON����*/
	private JSONObject jsTSCData;
	
	/**�ܹ�˾��������*/
	private String headTechData;
	/**�ܹ�˾����JSON����*/
	private JSONObject jsHeadTechData;
	
	/**�ܲ��з�����*/
	private String headDevelopData;
	/**�ܲ��з�JSON����*/
	private JSONObject jsHeadDevelopData;
	
	/**�û�����JSON����*/
	private JSONObject jsUserServiceData;
	/**�û�����JSON����*/
	private JSONObject jsUserTechData;
	
	/**����ID����*/
	private Integer orderId;
	
	/**������������ 0 ��ʾ�ڲ�����   1 ��ʾ �û�����*/
	private Integer type;
	
	/**��������*/
	private String commentContent;
	
	/**����ʱ��*/
	private Date createTime = new Date();
	
	/**������*/
	private String orderNumber;
	/**�û���ʵ����*/
	private String userName;

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getServiceData() {
		return serviceData;
	}

	public void setServiceData(String serviceData) {
		this.serviceData = serviceData;
	}

	public String getTSCData() {
		return TSCData;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public JSONObject getJsUserServiceData() {
		return jsUserServiceData;
	}

	public void setJsUserServiceData(JSONObject jsUserServiceData) {
		this.jsUserServiceData = jsUserServiceData;
	}

	public JSONObject getJsUserTechData() {
		return jsUserTechData;
	}

	public void setJsUserTechData(JSONObject jsUserTechData) {
		this.jsUserTechData = jsUserTechData;
	}

	public void setTSCData(String tSCData) {
		TSCData = tSCData;
	}

	public String getHeadTechData() {
		return headTechData;
	}

	public void setHeadTechData(String headTechData) {
		this.headTechData = headTechData;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHeadDevelopData() {
		return headDevelopData;
	}

	public void setHeadDevelopData(String headDevelopData) {
		this.headDevelopData = headDevelopData;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public JSONObject getJsServiceData() {
		return jsServiceData;
	}

	public void setJsServiceData(JSONObject jsServiceData) {
		this.jsServiceData = jsServiceData;
	}

	public JSONObject getJsTSCData() {
		return jsTSCData;
	}

	public void setJsTSCData(JSONObject jsTSCData) {
		this.jsTSCData = jsTSCData;
	}

	public JSONObject getJsHeadTechData() {
		return jsHeadTechData;
	}

	public void setJsHeadTechData(JSONObject jsHeadTechData) {
		this.jsHeadTechData = jsHeadTechData;
	}

	public JSONObject getJsHeadDevelopData() {
		return jsHeadDevelopData;
	}

	public void setJsHeadDevelopData(JSONObject jsHeadDevelopData) {
		this.jsHeadDevelopData = jsHeadDevelopData;
	}
	
	
	

}
