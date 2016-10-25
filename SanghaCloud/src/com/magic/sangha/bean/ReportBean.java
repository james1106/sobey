package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  ¸æ¾¯ÐÅÏ¢
 * @author QimouXie
 *
 */
public class ReportBean implements Serializable {

	private static final long serialVersionUID = 5603946639591829300L;
	
	private Integer id;
	
	private String eventID;
	
	private String typeID;
	
	private String level;
	
	private String resourceKey;
	
	private String stationCode;
	
	private String hostKey;
	
	private Date time;
	
	private String solutionTime;
	
	private String reason;
	
	private String processingResult;
	
	private String getTime;
	
	private Date createTime;
	
	private String interIPAddress;
	
	private String hostName;

	
	public String getInterIPAddress() {
		return interIPAddress;
	}

	public void setInterIPAddress(String interIPAddress) {
		this.interIPAddress = interIPAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getTypeID() {
		return typeID;
	}

	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getResourceKey() {
		return resourceKey;
	}

	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getHostKey() {
		return hostKey;
	}

	public void setHostKey(String hostKey) {
		this.hostKey = hostKey;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getSolutionTime() {
		return solutionTime;
	}

	public void setSolutionTime(String solutionTime) {
		this.solutionTime = solutionTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getProcessingResult() {
		return processingResult;
	}

	public void setProcessingResult(String processingResult) {
		this.processingResult = processingResult;
	}

	public String getGetTime() {
		return getTime;
	}

	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
