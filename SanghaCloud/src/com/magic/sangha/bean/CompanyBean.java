package com.magic.sangha.bean;

import java.io.Serializable;

/**
 *  公司
 * @author QimouXie
 *
 */
public class CompanyBean implements Serializable {

	private static final long serialVersionUID = 3605919076150345809L;
	
	/**主键ID*/
	private Integer id;
	/**公司名称*/
	private String companyName;
	/**公司类型 0：总公司 1：分公司 */
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
