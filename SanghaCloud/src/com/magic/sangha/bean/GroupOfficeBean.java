package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.List;

import com.magic.sangha.util.StatusConstant;

/**
 * 办事处
 * @author QimouXie
 *
 */
public class GroupOfficeBean implements Serializable {

	private static final long serialVersionUID = 8344821585684742946L;
	
	/**主键ID*/
	private Integer id;
	
	/**办事处名称*/
	private String officeName;
	
	/**办事处名字 为了后台取值*/
	private String companyName;
	
	/**所属公司*/
	private CompanyBean company;
	
	/**所属公司ID*/
	private Integer companyId;
	
	private Integer type = StatusConstant.OFFICE_COMPANY;
	
	/**旗下管理的电视台*/
	private List<TVBean> tvs;
	
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
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public CompanyBean getCompany() {
		return company;
	}
	public void setCompany(CompanyBean company) {
		this.company = company;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public List<TVBean> getTvs() {
		return tvs;
	}
	public void setTvs(List<TVBean> tvs) {
		this.tvs = tvs;
	}
	
	
	

}
