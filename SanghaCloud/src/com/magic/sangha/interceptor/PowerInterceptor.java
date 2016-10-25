package com.magic.sangha.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.PermissionBean;
import com.magic.sangha.bean.RolePowerBean;

public class PowerInterceptor extends HandlerInterceptorAdapter {
	
	public static final String LOGIN_URL = "/login.jsp";
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		GroupUser user = (GroupUser)session.getAttribute("user");
		if(null == user){
			response.sendRedirect(request.getContextPath() + LOGIN_URL);
			return false;
		}
		
		List<RolePowerBean> powers = user.getRole().getPowers();
		String reqURL = request.getRequestURL().toString();
		if (null != reqURL) {
			for (RolePowerBean power : powers) {
				PermissionBean per = power.getPower();
				if(reqURL.indexOf(per.getUrl()) != -1){
					return true;
				}
			}
		}
		response.sendRedirect(request.getContextPath() + LOGIN_URL);
		return false;
	}
	
	
	
	
}
