package com.magic.sangha.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.UserBean;


public class LoginHelper {
	
	public static final String TOKEN = "token";
	private static final String SESSION_USER = "session_user";
	public static boolean isLogin=false;
	public static final String LOGIN_PATH="login.jsp";
	
	public static final String USER_SESSION = "user";
	
	
	public static Object getCurrentUser(){
		HttpServletRequest req = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
		Object obj = req.getSession().getAttribute(SESSION_USER);
		if(null == obj){
			String token = req.getHeader("token");
			Object tempObj = MemcachedUtil.getInstance().get(token);
			if(null == tempObj){
				return null;
			}else{
				return tempObj;
			}
		}
		return obj;
	}
	
	public static Object getCurrentUser(String token){
		Object tempObj = MemcachedUtil.getInstance().get(token);
		return tempObj;
	}
	
	public static void clearToken(){
		HttpServletRequest req = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
		Object obj = req.getSession().getAttribute(SESSION_USER);
		if(null != obj ){
			req.getSession().invalidate();
		}
		String token = req.getHeader("token");
		MemcachedUtil.getInstance().delObj(token);
	}
	
	public static void delObjByToken(String token){
		if(token != null && token.trim().length() != 0){
			MemcachedUtil.getInstance().delObj(token);
		}
	}
	
	public static String addToken(UserBean user){
//		HttpServletRequest req = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
//		req.getSession().setAttribute(SESSION_USER, user);
		if(user.getToken() != null){
			Object obj = MemcachedUtil.getInstance().get(user.getToken());
			if(null != obj){
				MemcachedUtil.getInstance().delObj(user.getToken());
			}
		}
		UUID.randomUUID();
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		user.setToken(token);
		MemcachedUtil.getInstance().add(token, user);
		return token;
	}
	
	public static String addToken(GroupUser user){
//		HttpServletRequest req = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
//		req.getSession().setAttribute(SESSION_USER, user);
		if(user.getToken() != null){
			Object obj = MemcachedUtil.getInstance().get(user.getToken());
			if(null != obj){
				MemcachedUtil.getInstance().delObj(user.getToken());
			}
		}
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		user.setToken(token);
		MemcachedUtil.getInstance().add(token, user);
		return token;
	}
	
	public static GroupUser getCurrentAdmin(HttpServletRequest req){
		Object obj = req.getSession().getAttribute(USER_SESSION);
		if(null == obj){
			return null;
		}
		return (GroupUser)obj;
	}
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}
	
}
