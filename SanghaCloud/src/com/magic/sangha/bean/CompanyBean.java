package com.magic.sangha.bean;

import java.io.Serializable;

/**
 *  ��˾
 * @author QimouXie
 *
 */
public class CompanyBean implements Serializable {

	private static final long serialVersionUID = 3605919076150345809L;
	
	/**����ID*/
	private Integer id;
	/**��˾����*/
	private String companyName;
	/**��˾���� 0���ܹ�˾ 1���ֹ�˾ */
	private Integer type;
	
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

}
