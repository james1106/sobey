package com.magic.sangha.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CommentBean;
import com.magic.sangha.bean.CommentCountBean;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.service.ICommentService;
import com.magic.sangha.util.RoleConstant;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  评价统计
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/admin/commentCount")
public class AdminCommentController extends BaseController {
	
//	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private ICommentService commentServiceImpl;
	/**
	 *  获取 评论统计
	 * @return
	 */
	@RequestMapping("/getCommentCount")
	@ResponseBody
	public ViewData getCommentCount(Integer companyId,Integer officeId,String userNameAndRoleAndId){
		Integer roleId = null;
		Integer groupUserId = null;
		String userName = null;
		String mobile = null;
		if(null == companyId || companyId == 0){
			companyId = null;
		}
		if(null == officeId || officeId == 0){
			officeId = null;
		}
		if(null != userNameAndRoleAndId && userNameAndRoleAndId.trim().length() != 0){
			String[] userFileds = userNameAndRoleAndId.split("\\|");
			roleId = Integer.parseInt(userFileds[0]);
			groupUserId =  Integer.parseInt(userFileds[1]);
			userName = userFileds[2];
			mobile = userFileds[3];
		}
		List<CommentCountBean> countList = commentServiceImpl.queryCommentCount(companyId, officeId, roleId, groupUserId, mobile, userName);
		CommentCountBean data = new CommentCountBean();
		Integer disposeSpeed = 0;
		Integer serviceAttitude = 0;
		for (CommentCountBean comment : countList) {
			
			if(RoleConstant.CUSTOMER == roleId){
				//客服
				disposeSpeed += comment.getServiceDisposeSpeedCount();
				serviceAttitude+= comment.getServiceServerAttitudeCount();
			}else if(RoleConstant.FILIALETECH == roleId){
				// 分公司技术
				disposeSpeed += comment.getTscDisposeSpeedCount();
				serviceAttitude += comment.getTscServerAttitudeCount();
			}else if(RoleConstant.HEADCOMTECH == roleId){
				// 总部技术
				disposeSpeed += comment.getHeadTechDisposeSpeedCount();
				serviceAttitude += comment.getHeadTechServerAttitudeCount();
			}else if(RoleConstant.INVENT == roleId){
				// 研发
				disposeSpeed += comment.getDevelopDisposeSpeedCount();
				serviceAttitude += comment.getDevelopServerAttitudeCount();
			}else{
				continue;
			}
			
			
//			disposeSpeed += comment.getDisposeSpeedCount();
//			serviceAttitude += comment.getServiceAttitudeCount();
		}
		data.setDisposeSpeedCount(disposeSpeed);
		data.setServiceAttitudeCount(serviceAttitude);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	
	/***获取评论统计详情*/
	@RequestMapping("/getDetailComment")
	@ResponseBody
	public ViewData getCommentDetail(Integer type,String data,Integer pageSize,Integer pageNO){
		// type 0 分公司 1 办事处  2 员工
		if(null == type || null == data || null == pageSize || null == pageNO || data.trim().length() == 0){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		CutPageBean<CommentCountBean> respData = new CutPageBean<CommentCountBean>();
		if(type == 0){
			// 分公司
			Integer companyId = Integer.parseInt(data);
			respData = commentServiceImpl.queryDetailComment(pageNO, pageSize, companyId, null, null, null);
		}else if(type == 1){
			// 办事处
			Integer officeId = Integer.parseInt(data);
			respData = commentServiceImpl.queryDetailComment(pageNO, pageSize, null, officeId, null, null);
		}else if(type == 2){
			// 员工
			if(null != data && data.trim().length() != 0){
				String[] userFileds = data.split("\\|");
				Integer roleId = Integer.parseInt(userFileds[0]);
				Integer groupUserId =  Integer.parseInt(userFileds[1]);
				respData = commentServiceImpl.queryDetailComment(pageNO, pageSize, null, null, roleId, groupUserId);
			}
		}else{
			return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", respData);
	}
	
	@RequestMapping("/countCommentByUser")
	@ResponseBody
	public ViewData countUserComment(Integer userId){
		if(null == userId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		List<CommentCountBean> countList = commentServiceImpl.queryCountCommentByUser(userId);
		CommentCountBean data = new CommentCountBean();
		Integer disposeSpeed = 0;
		Integer serviceAttitude = 0;
		for (CommentCountBean comment : countList) {
			disposeSpeed += comment.getServiceDisposeSpeedCount() + comment.getTscDisposeSpeedCount();
			serviceAttitude += comment.getServiceServerAttitudeCount() + comment.getTscServerAttitudeCount();
		}
		data.setDisposeSpeedCount(disposeSpeed);
		data.setServiceAttitudeCount(serviceAttitude);
		data.setUserId(userId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	@RequestMapping("/getCommentDetailByUser")
	@ResponseBody
	public ViewData getCommentDetailByUser(Integer pageNO,Integer pageSize,Integer userId){
		if(null == userId || null == pageSize || null == pageNO ){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		CutPageBean<CommentCountBean> data = null;
		if(userId == 0){
			data = new CutPageBean<CommentCountBean>();
		}else{
			data = commentServiceImpl.queryDetailUserComment(pageNO, pageSize, userId);
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
