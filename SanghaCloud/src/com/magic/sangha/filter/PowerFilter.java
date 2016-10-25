package com.magic.sangha.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.magic.sangha.bean.GroupUser;
import com.magic.sangha.bean.PermissionBean;
import com.magic.sangha.bean.RolePowerBean;
import com.magic.sangha.util.StatusConstant;

@WebFilter("/admin/pc/*")
public class PowerFilter implements Filter {
	

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) arg0;
		GroupUser user = (GroupUser) arg0.getAttribute("user");
		
		if(user == null){
			req.getRequestDispatcher("/login.jsp").forward(arg0, arg1);
			return;
			
		}
		
		List<RolePowerBean> powers = user.getRole().getPowers();
		String reqURL = req.getRequestURL().toString();
		if (null != reqURL) {
			for (RolePowerBean power : powers) {
				PermissionBean per = power.getPower();
				if(reqURL.indexOf(per.getUrl()) != -1){
					arg0.setAttribute("isPower", StatusConstant.ALLOW);
					arg2.doFilter(arg0, arg1);
					return;
				}
			}
			arg0.setAttribute("isPower", StatusConstant.NON_ALLOW);
			arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
