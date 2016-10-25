package com.magic.sangha.bean;

import java.io.Serializable;

/**
 *   ͳ�� ������ɫ ������ͬ״̬�� ҵ��Bean
 * @author QimouXie
 *
 */
public class OrderStatusBean implements Serializable {

	private static final long serialVersionUID = 1829365833540062872L;
	
	/**��������*/
	private Integer backlog;
	
	/**������*/
	private Integer assigning;
	
	/**������*/
	private Integer pengding;
	
	/**������*/
	private Integer handling;
	
	/**������*/
	private Integer accepting;
	
	/**������*/
	private Integer evaluating;
	
	/**������*/
	private Integer happening;
	
	/**�����*/
	private Integer solved;
	

	public Integer getBacklog() {
		return backlog;
	}

	public void setBacklog(Integer backlog) {
		this.backlog = backlog;
	}

	public Integer getAssigning() {
		return assigning;
	}

	public void setAssigning(Integer assigning) {
		this.assigning = assigning;
	}

	public Integer getHappening() {
		return happening;
	}

	public void setHappening(Integer happening) {
		this.happening = happening;
	}

	public Integer getPengding() {
		return pengding;
	}

	public void setPengding(Integer pengding) {
		this.pengding = pengding;
	}

	public Integer getHandling() {
		return handling;
	}

	public void setHandling(Integer handling) {
		this.handling = handling;
	}

	public Integer getAccepting() {
		return accepting;
	}

	public void setAccepting(Integer accepting) {
		this.accepting = accepting;
	}

	public Integer getEvaluating() {
		return evaluating;
	}

	public void setEvaluating(Integer evaluating) {
		this.evaluating = evaluating;
	}

	public Integer getSolved() {
		return solved;
	}

	public void setSolved(Integer solved) {
		this.solved = solved;
	}
	
	

}
