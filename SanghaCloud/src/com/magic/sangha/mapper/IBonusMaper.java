package com.magic.sangha.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.magic.sangha.bean.BonusBean;

/**
 *  »ý·Ö
 * @author QimouXie
 *
 */
public interface IBonusMaper {
	
	BonusBean findByType(@Param("type")Integer type);
	
	@Update("update t_bonus set bonus=#{bonus} where id=#{id}")
	Integer updateBonus(@Param("id")Integer id,@Param("bonus")Integer bonus);
	
}
