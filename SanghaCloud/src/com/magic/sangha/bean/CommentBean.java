package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 *  评价Bean
 * @author QimouXie
 *
 */
public class CommentBean implements Serializable {

	private static final long serialVersionUID = -6466175162938586199L;
	
	/**主键ID*/
	private Integer id;
	
	/**客服数据*/
	private String serviceData;
	/**客服JSON数据*/
	private JSONObject jsServiceData;
	
	/**TSC数据*/
	private String TSCData;
	/**TSC JSON数据*/
	private JSONObject jsTSCData;
	
	/**总公司技术数据*/
	private String headTechData;
	/**总公司技术JSON数据*/
	private JSONObject jsHeadTechData;
	
	/**总部研发数据*/
	private String headDevelopData;
	/**总部研发JSON数据*/
	private JSONObject jsHeadDevelopData;
	
	/**用户评价JSON数据*/
	private JSONObject jsUserServiceData;
	/**用户评价JSON数据*/
	private JSONObject jsUserTechData;
	
	/**订单ID数据*/
	private Integer orderId;
	
	/**评价数据类型 0 表示内部评价   1 表示 用户评价*/
	private Integer type;
	
	/**评价内容*/
	private String commentContent;
	
	/**评价时间*/
	private Date createTime = new Date();
	
	/**订单号*/
	private String orderNumber;
	/**用户真实姓名*/
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
