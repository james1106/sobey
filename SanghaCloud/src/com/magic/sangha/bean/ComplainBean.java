package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  投诉
 * @author QimouXie
 *
 */

public class ComplainBean implements Serializable {

	private static final long serialVersionUID = -303478179145197220L;
	
	/**主键*/
	private Integer id;
	
	/**投诉类型  0: 无投诉对象  1：投诉客服  2 ：投诉技术   3 投诉两者*/
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
