package com.magic.sangha.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *  资讯类型
 * @author QimouXie
 *
 */
public class NewsType implements Serializable {

	private static final long serialVersionUID = -3030292077342836016L;
	
	/**Key*/
	private Integer id;
	
	/**News Type Name*/
	private String typeName;
	
	/**Create Time, Default Current Date*/
	private Date createTime = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
