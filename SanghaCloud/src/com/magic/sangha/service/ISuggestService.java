package com.magic.sangha.service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.SuggestBean;

/**
 *  ���鷴�� ҵ���ӿ�
 * @author QimouXie
 *
 */
public interface ISuggestService {

	void add(SuggestBean suggest);
	
	CutPageBean<SuggestBean> queryPage (Integer pageNO,Integer pageSize);
}
