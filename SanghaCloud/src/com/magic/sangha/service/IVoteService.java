package com.magic.sangha.service;

import java.util.List;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.VoteBean;

public interface IVoteService {
	
	CutPageBean<VoteBean> queryPage(Integer pageNO,Integer pageSize,String title);

	/**统计该新闻下 所有的投票列 和 投票总数*/
	List<VoteBean> queryVoteByNewsId(Integer newsId);
}
