package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 *  ¶©µ¥×´Ì¬¸ú×Ù ÓÃ»§°æ
 * @author QimouXie
 *
 */
public class OrderTrackBean implements Serializable {

	private static final long serialVersionUID = -2598651186904724624L;
	
	private Integer status;
	
	private String content;
	
	private Date createDate;
	
	

	public OrderTrackBean(Integer status, String content, Date createDate) {
		super();
		this.status = status;
		this.content = content;
		this.createDate = createDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public JSONObject createOrderTrack(){
		JSONObject temp = new JSONObject();
		temp.put("status", status);
		temp.put("content", content);
		temp.put("createtime", createDate.getTime());
//		JSONArray data = new JSONArray();
//		data.add(temp);
		return temp;
	}
	
	

}
