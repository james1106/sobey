package com.magic.sangha.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.magic.sangha.bean.CompanyBean;
import com.magic.sangha.bean.CutPageBean;
import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.UserBean;
import com.magic.sangha.mapper.IGroupUserMapper;
import com.magic.sangha.mapper.IUserMapper;
import com.magic.sangha.service.ICompanyService;
import com.magic.sangha.service.IGroupUserService;
import com.magic.sangha.util.LoginHelper;
import com.magic.sangha.util.MemcachedUtil;
import com.magic.sangha.util.RoleConstant;
import com.magic.sangha.util.StatusConstant;

@Service
public class GroupUserServiceImpl implements IGroupUserService {
	
	@Resource
	private IGroupUserMapper groupUserMapper;
	@Resource
	private IUserMapper userMapper;
	@Resource
	private ICompanyService companyServiceImpl;

	@Override
	public GroupUser login(String mobile, String password) {
		return groupUserMapper.login(mobile, password);
	}

	@Override
	public Integer register(GroupUser user) {
		return groupUserMapper.register(user);
	}

	@Override
	public GroupUser findByToken(String token) {
		return groupUserMapper.findByToken(token);
	}

	@Override
	public Integer updateToken(String token, Integer id,Date date) {
		return groupUserMapper.updateToken(token, id,date);
	}

	@Override
	public Integer verifiMobile(String mobile) {
		return groupUserMapper.verifiMobile(mobile);
	}

	@Override
	public Integer updateGroupUser(GroupUser user) {
		return groupUserMapper.updateGroupUser(user);
	}

	@Override
	public Integer updateGroupUserIsEmail(Integer id, Integer isEmail) {
		return groupUserMapper.updateGroupUserIsEmail(id, isEmail);
	}

	@Override
	public GroupUser findByMobile(String mobile) {
		return groupUserMapper.findByMobile(mobile);
	}

	@Override
	public GroupUser findByIdToName(Integer id) {
		return groupUserMapper.findByIdToName(id);
	}

	@Override
	public void sendEmailToGroupUser(Integer officeId,UserBean user,HttpServletRequest req) {
		
		
	}

	@Override
	public List<GroupUser> findByUserId(Integer userId,Integer roleId) {
		
		 List<GroupUser> headTech = groupUserMapper.findHeadTechByRoleId(RoleConstant.HEADCOMTECH);
		 List<GroupUser> tscs = groupUserMapper.findByUserId(userId,roleId);
		 List<GroupUser> data = new ArrayList<GroupUser>();
		 for (GroupUser groupUser : headTech) {
			 data.add(groupUser);
		}
		 for (GroupUser groupUser : tscs) {
			 data.add(groupUser);
		}
		return data;
	}

	@Override
	public List<GroupUser> findHeadTech(Integer roleId,Integer companyId) {
		// TODO Auto-generated method stub
		return groupUserMapper.findHeadTech(roleId,companyId);
	}

	@Override
	public Integer updateDeviceTypeAndToken(Integer id, Integer deviceType,
			String deviceToken) {
		// TODO Auto-generated method stub
		return groupUserMapper.updateDeviceTypeAndToken(id, deviceType, deviceToken);
	}

	@Override
	public List<GroupUser> findGroupUserToManyRoleId(Integer companyId) {
		// TODO Auto-generated method stub
		return groupUserMapper.findGroupUserToManyRoleId(companyId);
	}

	@Override
	public List<GroupUser> findGroupUserByOfficeAndRole(Integer officeId,
			Integer roleId) {
		// TODO Auto-generated method stub
		return groupUserMapper.findGroupUserByOfficeAndRole(officeId, roleId);
	}

	@Override
	public GroupUser findTypeTokeById(Integer id) {
		// TODO Auto-generated method stub
		return groupUserMapper.findTypeTokeById(id);
	}

	@Override
	public List<GroupUser> findByUserId(Integer userId) {
		return groupUserMapper.findByUserId(userId, RoleConstant.FILIALETECH);
	}

	

	@Override
	public CutPageBean<GroupUser> findByAudit(Integer roleId,Integer status, Integer pageNO,Integer pageSize,String userName) {
		
		List<GroupUser> dataList = groupUserMapper.findByAudit(roleId,(pageNO-1)*pageSize, pageSize, status,userName);
		
		Integer count = groupUserMapper.countByStatus(status,roleId,userName);
		Integer totalPage = 0;
		if(count % pageSize  == 0){
			totalPage = count / pageSize;
		}else{
			totalPage = (count / pageSize) + 1;
		}
		CutPageBean<GroupUser> page = new CutPageBean<GroupUser>(dataList, count, totalPage);
		return page;
	}

	@Override
	public Integer updateStatusAndRole(Integer id, Integer status,Integer roleId) throws Exception {
		
		if(StatusConstant.FROZEN.equals(status)){
			GroupUser user = groupUserMapper.findById(id);
			if(user.getToken() != null){
				Object obj = LoginHelper.getCurrentUser(user.getToken());
				if(null != obj){
					if(obj instanceof GroupUser){
						GroupUser userObj = (GroupUser)obj;
						userObj.setStatus(StatusConstant.FROZEN);
						MemcachedUtil.getInstance().replace(userObj.getToken(), userObj);
					}
				}
			}
		}
		return groupUserMapper.updateStatusAndRole(id, status, roleId);
	}

	@Override
	public boolean checkEmailIsExist(String email) {
		List<GroupUser> groupUser = groupUserMapper.findByEmail(email);
		if(groupUser.size() == 0){
			List<UserBean> users = userMapper.findByEmail(email);
			if(users.size() == 0){
				return false;
			}
			return true;
		}
		return true;
	}

	@Override
	public GroupUser findById(Integer userId) {
		GroupUser user = groupUserMapper.findById(userId);
		if(user != null){
			
			if(user.getOfficeId() != null){
				user.setCompanyType(StatusConstant.OFFICE_COMPANY);
				if(null != user.getOffice()){
					user.setOfficeName(user.getOffice().getOfficeName());
				}
			}else if(user.getCompanyId() != null){
				CompanyBean company = companyServiceImpl.findById(user.getCompanyId());
				user.setCompanyType(company.getType());
				user.setOfficeName(company.getCompanyName());
			}
		}
		return user;
	}
	
	@Override
	public List<GroupUser> findAllTechNOTFeibian(Integer userId) {
		return groupUserMapper.findAllTechNOTFeibian(userId);
	}
	@Override
	public List<GroupUser> findAllTechFeibian(Integer userId, Integer categoryId) {
		return groupUserMapper.findAllTechFeibian(userId, categoryId);
	}

	@Override
	public List<GroupUser> findSaleByTvId(Integer tvId) {
		return groupUserMapper.findSaleByTVId(tvId);
	}

	@Override
	public void updateUser(GroupUser user) {
		groupUserMapper.updateUser(user);
	}

	@Override
	public List<GroupUser> findOfficeSaleByTvId(Integer tvId) {
		// TODO Auto-generated method stub
		return groupUserMapper.findOfficeSaleByTvId(tvId);
	}
	
	@Override
	public List<GroupUser> batchQueryGroupUser(List<Integer> ids) {
		// TODO Auto-generated method stub
		return groupUserMapper.batchQueryGroupUser(ids);
	}

	@Override
	public CutPageBean<GroupUser> queryPageByRole(String realName,Integer pageNO,Integer pageSize, Integer roleId) {
		List<GroupUser> dataList = groupUserMapper.findPageByRole(realName,(pageNO - 1) * pageSize, pageSize,roleId );
		Integer count = groupUserMapper.countByRole(realName,roleId);
		Integer totalPage = count % pageSize == 0? count / pageSize : count / pageSize + 1;
		CutPageBean<GroupUser> page  = new CutPageBean<GroupUser>(dataList, count, totalPage);
		return page;
	}

	@Override
	public List<GroupUser> findAllToToken() {
		return groupUserMapper.findAllToToken();
	}

	@Override
	public Map<String, Integer> countByStatus() {
		Integer nonPass = groupUserMapper.countByStatus(StatusConstant.NONPASS,null,null);
		Integer all = groupUserMapper.countByStatus(null,null,null);
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("nonPass", nonPass);
		data.put("all", all);
		return data;
	}

	@Override
	public Integer countOnLineByService() {
		// TODO Auto-generated method stub
		return groupUserMapper.countOnLineByService();
	}

@Override
public CutPageBean<GroupUser> queryPageLikeNameByRole(Integer pageNO,Integer pageSize, String realName) {
	
	List<GroupUser> dataList = groupUserMapper.findPageTech((pageNO - 1) * pageSize, pageSize, realName);
	Integer count = groupUserMapper.countPageTech(realName);
	Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
	CutPageBean<GroupUser> page = new CutPageBean<GroupUser>(dataList, count, totalPage);
	return page;
}

@Override
public CutPageBean<GroupUser> queryPageByUser(Integer pageNO, Integer pageSize,String realName) {
	List<GroupUser> dataList = groupUserMapper.queryCountSign((pageNO - 1) * pageSize, pageSize, realName);
	Integer count = groupUserMapper.countSign(realName);
	Integer totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
	CutPageBean<GroupUser> page = new CutPageBean<GroupUser>(dataList, count, totalPage);
	return page;
}
	
@Override
public List<GroupUser> findAllDevelopFeibian(Integer categoryId) {
	return groupUserMapper.findAllDevelopFeibian(categoryId);
}

@Override
public void delGroupUser(Integer id) {
	groupUserMapper.delGroupUser(id);
}

@Override
public List<GroupUser> queryByCompanyOrOffice(Integer companyId,Integer officeId) {
	List<GroupUser> users = new ArrayList<GroupUser>();
	if(null ==officeId && null != companyId ){
		users = groupUserMapper.queryByCompany(companyId);
	}else if(null != officeId){
		users = groupUserMapper.findByOfficeId(officeId, null);
	}else if(null == companyId && null == officeId ){
		users = groupUserMapper.queryAll();
	}
	return users;
}
	
	
	

}
