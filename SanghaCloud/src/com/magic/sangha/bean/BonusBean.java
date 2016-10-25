package com.magic.sangha.bean;

import java.io.Serializable;
/**
 *  »ý·Ö
 * @author QimouXie
 *
 */
public class BonusBean implements Serializable {

	private static final long serialVersionUID = 4305990216089942038L;
	
	private Integer id;
	
	private Integer bonus;
	
	private Integer type;

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

	public Integer getBonus() {
		return bonus;
	}

	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}

}
