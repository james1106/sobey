package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *   ����������Ϣ ʵ��---��Ϣ����
 * @author QimouXie
 *
 */
public class OrderInfoBean implements Serializable {

	private static final long serialVersionUID = 5207382019951726103L;

	/**����ID*/
	private Integer id;
	
	/**����ID*/
	private Integer orderId;
	
	/**�Ƹ��û�*/
	private Integer toUserId;
	
	/**�Ƹ�Ա��*/
	private Integer toGroupUserId;
	
	/**���͵���Ϣ*/
	private String infoMsg;
	
	/**������*/
	private String orderNumber;
	
	/**����ʱ��*/
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
