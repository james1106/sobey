package com.magic.sangha.bean;

import java.io.Serializable;

/**
 *  �������� �ܲ�����֧�ֵ�ʱ����Ҫ���͸� �쵼��������
 * @author QimouXie
 *
 */
public class CCGroupUserBean implements Serializable {

	private static final long serialVersionUID = 8011441595778655531L;
	
	/**����*/
	private Integer id;
	
	/**��������*/
	private Integer groupuserId;
	
	/**���͵Ķ���*/
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
