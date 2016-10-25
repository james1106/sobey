package com.magic.sangha.service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.SuggestBean;

/**
 *  建议反馈 业务层接口
 * @author QimouXie
 *
 */
public interface ISuggestService {

	void add(SuggestBean suggest);
	
	CutPageBean<SuggestBean> queryPage (Integer pageNO,Integer pageSize);
}
