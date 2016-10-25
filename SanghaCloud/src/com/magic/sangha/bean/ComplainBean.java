package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  Ͷ��
 * @author QimouXie
 *
 */

public class ComplainBean implements Serializable {

	private static final long serialVersionUID = -303478179145197220L;
	
	/**����*/
	private Integer id;
	
	/**Ͷ������  0: ��Ͷ�߶���  1��Ͷ�߿ͷ�  2 ��Ͷ�߼���   3 Ͷ������*/
	private Integer type;
	
	private Integer orderId;
	
	private String content;
	
	private Date createTime = new Date();
	
	private String orderNumber;

	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
