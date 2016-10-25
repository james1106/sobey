package com.magic.sangha.bean;

import java.io.Serializable;

/**
 *  技术发起 总部技术支持的时候，需要抄送给 领导或者销售
 * @author QimouXie
 *
 */
public class CCGroupUserBean implements Serializable {

	private static final long serialVersionUID = 8011441595778655531L;
	
	/**主键*/
	private Integer id;
	
	/**被抄送者*/
	private Integer groupuserId;
	
	/**抄送的订单*/
	private Integer orderId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupuserId() {
		return groupuserId;
	}

	public void setGroupuserId(Integer groupuserId) {
		this.groupuserId = groupuserId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
