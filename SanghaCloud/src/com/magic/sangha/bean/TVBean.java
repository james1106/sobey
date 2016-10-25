package com.magic.sangha.bean;

import java.io.Serializable;

/**
 *  ����̨
 * @author QimouXie
 *
 */
public class TVBean implements Serializable {

	private static final long serialVersionUID = -7293023158013144946L;
	
	/**����*/
	private Integer id;
	
	/**����̨����*/
	private String tvName;
	
	/**�������´�ID*/
	private Integer officeId;
	
	/**�������´�����*/
	private String officeName;
	
	/**�������´�*/
	private GroupOfficeBean office;
	
	/**����̨��Ψһ��ʶ*/
	private String stationCode;
	
	/**����sobey�ӿ�����*/
	private String address;
	
	/**����sobey�ӿ�����*/
	private String province;
	
	/**����sobey�ӿ�����*/
	private String city;
	
	/**����sobey�ӿ�����*/
	private String district;
	
	/**����sobey�ӿ�����*/
	private String street;
	
	/**����sobey�ӿ�����*/
	private String streetNumber;
	
	/**����sobey�ӿ�����*/
	private String lat;
	
	/**����sobey�ӿ�����*/
	private String lng;
	
	private String logo;
	
	/**����̨������ͣ� 0 ���µ� 1 ��̨�ֶ����*/
	private Integer type;

	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTvName() {
		return tvName;
	}

	public void setTvName(String tvName) {
		this.tvName = tvName;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public GroupOfficeBean getOffice() {
		return office;
	}

	public void setOffice(GroupOfficeBean office) {
		this.office = office;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((stationCode == null) ? 0 : stationCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TVBean other = (TVBean) obj;
		if (stationCode == null) {
			if (other.stationCode != null)
				return false;
		} else if (!stationCode.equals(other.stationCode))
			return false;
		return true;
	}

}
