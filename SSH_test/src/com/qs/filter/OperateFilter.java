package com.qs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * jsp action页面操作获取当前页面权限
 * @createDate 2013.5.21
 * 
 **/
public class OperateFilter implements Filter {	
	
	private static final long serialVersionUID = -5618746570740546841L;
	private static final String J_USERTYPE = "userType";
	public void init(FilterConfig arg0) throws ServletException {

	}

	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
			FilterChain filterChain) throws IOException, ServletException {
	
		sRequest.setCharacterEncoding("utf-8");
		String userType = sRequest.getParameter(J_USERTYPE);
		System.out.println(userType);
//		if(StringUtils.isNotBlank(functionFatherId)){
//			SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
//			listPrivilege=privilegeService.findActionPrivilege(sessionBean.getUserId(), functionFatherId, ActionType.BUTTON);
//		}
				
		filterChain.doFilter(sRequest, sResponse);
	}

	public void destroy() {	
	}

	
}
