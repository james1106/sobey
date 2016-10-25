package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.List;

import com.magic.sangha.util.StatusConstant;

/**
 * ���´�
 * @author QimouXie
 *
 */
public class GroupOfficeBean implements Serializable {

	private static final long serialVersionUID = 8344821585684742946L;
	
	/**����ID*/
	private Integer id;
	
	/**���´�����*/
	private String officeName;
	
	/**���´����� Ϊ�˺�̨ȡֵ*/
	private String companyName;
	
	/**������˾*/
	private CompanyBean company;
	
	/**������˾ID*/
	private Integer companyId;
	
	private Integer type = StatusConstant.OFFICE_COMPANY;
	
	/**���¹���ĵ���̨*/
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
