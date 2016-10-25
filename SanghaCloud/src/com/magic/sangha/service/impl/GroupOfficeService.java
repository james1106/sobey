package com.magic.sangha.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupOfficeBean;
import com.magic.sangha.mapper.IGroupOfficeMapper;
import com.magic.sangha.service.IGroupOfficeService;

/**
 *   公司业务层接口实现
 * @author QimouXie
 *
 */
@Service
public class GroupOfficeService implements IGroupOfficeService {
	
	@Resource
	private IGroupOfficeMapper groupOfficeMapper;

	@Override
	public Integer addOffice(GroupOfficeBean office) {
		return groupOfficeMapper.addOffice(office);
	}

	@Override
	public List<GroupOfficeBean> findByComplanyId(Integer companyId) {
		return groupOfficeMapper.findByComplanyId(companyId);
	}


	@Override
	public CutPageBean<GroupOfficeBean> findLikeOfficeName(String officeName,Integer pageSize,Integer pageNO) {
		
		List<GroupOfficeBean> dataList =null;
		int count = 0;
		int totalPage = 0;
				
		if(null ==pageSize  || null == pageNO){
			dataList = groupOfficeMapper.findLikeByOfficeName(officeName,null, null);
		}else{
			dataList = groupOfficeMapper.findLikeByOfficeName(officeName, (pageNO-1)*pageSize, pageSize);
			count = groupOfficeMapper.countOffice(officeName);
			if(count % pageSize == 0){
				totalPage = count / pageSize;
			}else{
				totalPage = count / pageSize + 1;
			}
		}
		if(dataList == null){
			return null;
		}
		CutPageBean<GroupOfficeBean> page = new CutPageBean<GroupOfficeBean>(dataList, count, totalPage);
		return page;
	}

	@Override
	public GroupOfficeBean findById(Integer id) {
		// TODO Auto-generated method stub
		return groupOfficeMapper.findById(id);
	}

	@Override
	public GroupOfficeBean findByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return groupOfficeMapper.findByUserId(userId);
	}

	@Override
	public Integer update(GroupOfficeBean office) {
		return groupOfficeMapper.update(office);
	}

	@Override
	public Integer queryByOfficeName(String officeName) {
		return groupOfficeMapper.queryByOfficeName(officeName);
	}
}
