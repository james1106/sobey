package com.magic.sangha.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.TVBean;
import com.magic.sangha.service.ITVService;
import com.magic.sangha.util.HttpGetRequest;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.UrlUtils;
import com.magic.sangha.util.ViewData;

/**
 * 电视台请求控制层
 * 
 * @author QimouXie
 *
 */
@Controller
@RequestMapping("/admin/tv")
public class TVController extends BaseController {

	@Resource
	private ITVService tvServiceImpl;

	// private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping("/updateTVList")
	@ResponseBody
	public ViewData updateTVList() {

		String url = UrlUtils.geturl(null, UrlUtils.GETALLTV_URL);
		String str = HttpGetRequest.sendRequest(url);
		JSONObject jsonObj = null;
		JSONArray jsonArray = null;
		List<TVBean> tvData = new ArrayList<TVBean>();
		try {
			jsonObj = JSONObject.fromObject(str);
			if (200 != (jsonObj.getInt("ErrorCode"))) {
				return buildFailureJson(StatusConstant.Fail_CODE, "数据请求失败");
			}
			jsonArray = JSONArray.fromObject(jsonObj.get("StationList"));
			if (null != jsonArray) {

				for (Object obj : jsonArray) {
					JSONObject tempJSON = JSONObject.fromObject(obj);
					JSONObject positionJSON = JSONObject.fromObject(tempJSON
							.get("Position"));
					TVBean tv = new TVBean();
					tv.setStationCode(tempJSON.getString("StationCode"));
					tv.setTvName(tempJSON.getString("StationName"));
					tv.setAddress(positionJSON.getString("Address"));
					tv.setCity(positionJSON.getString("City"));
					tv.setProvince(positionJSON.getString("Province"));
					tv.setDistrict(positionJSON.getString("District"));
					tv.setStreet(positionJSON.getString("Street"));
					tv.setStreetNumber(positionJSON.getString("StreetNumber"));
					tv.setLat(positionJSON.get("Lat").toString());
					tv.setLng(positionJSON.get("Lng").toString());
					tvData.add(tv);
				}
			}
		} catch (Exception e) {
			return buildFailureJson(StatusConstant.Fail_CODE, "数据异常");
		}
		List<TVBean> newData = tvServiceImpl.batchAddTV(tvData);
		if (newData.size() == 0) {
			return buildSuccessCodeJson(StatusConstant.NO_DATA, "没有数据");
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", newData);
	}

	/**
	 * 根据办事处状态 分页获取 电视台列表
	 * 
	 * @return
	 */
	@RequestMapping("/getTVList")
	@ResponseBody
	public ViewData getTVList(Integer pageNO, Integer pageSize,Integer officeStatus ,String tvName) {
		// officeStatus 0 全部 1 已分配 2 待分配
		if (null == officeStatus || null == pageNO || null == pageSize) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if(null == tvName || tvName.trim().length() == 0){
			tvName = null;
		}
		CutPageBean<TVBean> data = tvServiceImpl.findByStatus(pageNO, pageSize,officeStatus,tvName);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

	@RequestMapping("/updateOffice")
	@ResponseBody
	public ViewData updateTVByOffice(Integer officeId, Integer tvId) {
		if (null == officeId || null == tvId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}

		tvServiceImpl.updateTVToOffice(tvId, officeId);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
	}

	/**
	 * 通过办事处 查询电视台
	 * 
	 * @param officeId
	 * @return
	 */
	@RequestMapping("/getTVByOffice")
	@ResponseBody
	public ViewData getTVsByOffice(Integer officeId) {
		if (null == officeId) {
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不存在");
		}
		List<TVBean> tvs = tvServiceImpl.findByOfficeId(officeId);
		if (tvs.size() == 0) {
			return buildSuccessCodeJson(StatusConstant.NO_DATA, "没有数据");
		}
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", tvs);
	}

	@RequestMapping("/dynamicGetTV")
	@ResponseBody
	public ViewData dynamicGetTvs(String tvName) {
		List<TVBean> data = tvServiceImpl.dynamicGetTvs(tvName);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", data);
	}

	/**
	 * 添加电视台
	 * 
	 * @return
	 */
	@RequestMapping("/addTV")
	@ResponseBody
	public ViewData addTv(String tvName, String stationCode, String address,
			Integer office) {

		if (office == 0 || tvName == null || tvName.trim().length() == 0) {
			return buildFailureJson(StatusConstant.Fail_CODE, "数据非法");
		}
		Integer count = tvServiceImpl.queryTVByTVName(tvName);
		if (count != 0) {
			return buildFailureJson(StatusConstant.Fail_CODE, "名称已存在");
		}
		if(null == stationCode || stationCode.trim().length() == 0){
			stationCode = null;
		}
		TVBean tv = new TVBean();
		tv.setTvName(tvName);
		tv.setStationCode(stationCode);
		tv.setOfficeId(office);
		tv.setAddress(address);
		tv.setType(1);
		tvServiceImpl.addTV(tv);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "操作成功");
	}

	@RequestMapping("/getTVsByRole")
	@ResponseBody
	public ViewData getTVs(Integer roleId,Integer userId) {
		if(null == roleId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		List<TVBean> tvs = tvServiceImpl.getTVByUserType(roleId, userId);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", tvs);
	}
	
	
	@RequestMapping("/updateTVStatiocCode")
	@ResponseBody
	public ViewData updateTVStation(Integer tvId,String tvName,String stationCode){
		if(null == tvId){
			return buildFailureJson(StatusConstant.FIELD_NOT_NULL, "字段不能为空");
		}
		if (tvId == 0 || tvName == null || tvName.trim().length() == 0) {
			return buildFailureJson(StatusConstant.Fail_CODE, "数据非法");
		}
		TVBean tvw = tvServiceImpl.findById(tvId);
		if(!tvName.equals(tvw.getTvName())){
			Integer count = tvServiceImpl.queryTVByTVName(tvName);
			if (count != 0) {
				return buildFailureJson(StatusConstant.Fail_CODE, "名称已存在");
			}
		}
		TVBean tv = new TVBean();
		tv.setId(tvId);
		tv.setTvName(tvName);
		tv.setStationCode(stationCode);
		tvServiceImpl.updateTV(tv);
		return buildSuccessCodeJson(StatusConstant.SUCCESS_CODE, "修改成功");
	}
	

}
