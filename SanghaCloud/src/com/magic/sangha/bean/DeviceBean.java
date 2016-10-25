package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 *  从 Sobey 更新下来的设备 实体
 * @author QimouXie
 *
 */
public class DeviceBean implements Serializable {

	private static final long serialVersionUID = -6777704455813451310L;
	
	private Integer id;
	
	private String hostKey;
	
	private String hostName;
	
	private String interIPAddress;
	
	private String stationCode;
	
	private String groupCode;
	
	private String remoteIP;
	
	private String status;
	
	private String systemInfo;
	
	private JSONObject JSONSystemInfo;
	
	private String loadInfo;
	
	private JSONObject JSONLoadInfo;
	
	private Date createTime = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHostKey() {
		return hostKey;
	}

	public void setHostKey(String hostKey) {
		this.hostKey = hostKey;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getInterIPAddress() {
		return interIPAddress;
	}

	public void setInterIPAddress(String interIPAddress) {
		this.interIPAddress = interIPAddress;
	}

	public String getRemoteIP() {
		return remoteIP;
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSystemInfo() {
		return systemInfo;
	}

	public void setSystemInfo(String systemInfo) {
		this.systemInfo = systemInfo;
	}

	public JSONObject getJSONSystemInfo() {
		return JSONSystemInfo;
	}

	public void setJSONSystemInfo(JSONObject jSONSystemInfo) {
		JSONSystemInfo = jSONSystemInfo;
	}

	public String getLoadInfo() {
		return loadInfo;
	}

	public void setLoadInfo(String loadInfo) {
		this.loadInfo = loadInfo;
	}

	public JSONObject getJSONLoadInfo() {
		return JSONLoadInfo;
	}

	public void setJSONLoadInfo(JSONObject jSONLoadInfo) {
		JSONLoadInfo = jSONLoadInfo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hostKey == null) ? 0 : hostKey.hashCode());
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
		DeviceBean other = (DeviceBean) obj;
		if (hostKey == null) {
			if (other.hostKey != null)
				return false;
		} else if (!hostKey.equals(other.hostKey))
			return false;
		return true;
	}
	
	
	

}
