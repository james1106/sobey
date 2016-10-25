package com.magic.sangha.util;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DataView {
	
	private Integer flag;
	
	private Integer code;
	
	private String msg;
	
	private Object data;
	
	
	
	

	public DataView(Integer flag, Integer code, String msg, Object data) {
		super();
		this.flag = flag;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public DataView(Integer flag, Integer code, String msg) {
		super();
		this.flag = flag;
		this.code = code;
		this.msg = msg;
		this.data = new Object();
	}

	public DataView() {
		super();
	}
	
	public DataView buildDataView(Integer flag,Integer code,String msg,Object data){
		DataView view = new DataView();
		view.setFlag(flag);
		view.setCode(code);
		view.setMsg(msg);
		if(null == data){
			data = new Object();
		}
		view.setData(data);
		return view;
	}
	
	public DataView buildSuccessJson(Integer flag,Integer code,String msg,Object data){
		return buildDataView( flag, code, msg, data);
	}
	

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	

}
