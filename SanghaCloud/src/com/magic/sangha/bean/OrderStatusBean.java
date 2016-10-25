package com.magic.sangha.bean;

import java.io.Serializable;

/**
 *   统计 各个角色 订单不同状态的 业务Bean
 * @author QimouXie
 *
 */
public class OrderStatusBean implements Serializable {

	private static final long serialVersionUID = 1829365833540062872L;
	
	/**待办任务*/
	private Integer backlog;
	
	/**待分配*/
	private Integer assigning;
	
	/**待处理*/
	private Integer pengding;
	
	/**处理中*/
	private Integer handling;
	
	/**待验收*/
	private Integer accepting;
	
	/**待评价*/
	private Integer evaluating;
	
	/**进行中*/
	private Integer happening;
	
	/**已完成*/
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
