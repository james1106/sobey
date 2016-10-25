package com.magic.sangha.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ҳBean
 * @author QimouXie
 *
 */
public class CutPageBean<T> {

	/** ��ǰҳ��Ҫ��ʾ������ */
	private List<T> dataList = new ArrayList<T>();
	/** �ܵļ�¼�� */
	private int count;
	/** ��ҳ�� */
	private int totalPage;

	public CutPageBean() {
		super();
	}

	public CutPageBean(List<T> dataList, int count, int totalPage) {
		super();
		this.dataList = dataList;
		this.count = count;
		this.totalPage = totalPage;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
