package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.VoteBean;

public interface IVoteService {
	
	CutPageBean<VoteBean> queryPage(Integer pageNO,Integer pageSize,String title);

	/**ͳ�Ƹ������� ���е�ͶƱ�� �� ͶƱ����*/
	List<VoteBean> queryVoteByNewsId(Integer newsId);
}
