package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *   订单推送消息 实体---消息中心
 * @author QimouXie
 *
 */
public class OrderInfoBean implements Serializable {

	private static final long serialVersionUID = 5207382019951726103L;

	/**主键ID*/
	private Integer id;
	
	/**订单ID*/
	private Integer orderId;
	
	/**推给用户*/
	private Integer toUserId;
	
	/**推给员工*/
	private Integer toGroupUserId;
	
	/**推送的消息*/
	private String infoMsg;
	
	/**订单号*/
	private String orderNumber;
	
	/**推送时间*/
	private Date createTime = new Date();
	
	

	public OrderInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderInfoBean(Integer orderId, Integer toUserId,
			Integer toGroupUserId, String infoMsg, String orderNumber) {
		super();
		this.orderId = orderId;
		this.toUserId = toUserId;
		this.toGroupUserId = toGroupUserId;
		this.infoMsg = infoMsg;
		this.orderNumber = orderNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public Integer getToGroupUserId() {
		return toGroupUserId;
	}

	public void setToGroupUserId(Integer toGroupUserId) {
		this.toGroupUserId = toGroupUserId;
	}

	public String getInfoMsg() {
		return infoMsg;
	}

	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
