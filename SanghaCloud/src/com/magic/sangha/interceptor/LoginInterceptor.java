package com.magic.sangha.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.magic.sangha.bean.GroupUser;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
		GroupUser user = (GroupUser)request.getSession().getAttribute("user");
		if(null == user ){
			response.sendRedirect(request.getContextPath() + PowerInterceptor.LOGIN_URL);
			return false;
		}
		
		return true;
	}

}
