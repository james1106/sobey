package com.magic.sangha.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.VoteBean;
import com.magic.sangha.service.IVoteService;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;


@Controller
@RequestMapping("/admin/vote")
public class AdminVoteController extends BaseController {

	@Resource
	private IVoteService voteServiceImpl;
	
	@RequestMapping("/getVotes")
	@ResponseBody
	public ViewData getVote(Integer pageNO,Integer pageSize,String title){
		
		if(null == pageNO || null == pageSize ){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		if(null == title || title.trim().length() == 0){
			title = null;
		}
		CutPageBean<VoteBean> data = voteServiceImpl.queryPage(pageNO, pageSize, title);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", data);
		
	}
	
	@RequestMapping("/getVoteDetail")
	@ResponseBody
	public ViewData getVoteDetail(Integer newsId){
		
		if(null == newsId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "�ֶβ���Ϊ��");
		}
		List<VoteBean> votes = voteServiceImpl.queryVoteByNewsId(newsId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", votes);
	}
	
}
