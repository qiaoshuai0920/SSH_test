package com.qs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.qs.bean.SessionBean;
import com.qs.enums.FilterEnums.WebType;
/**
 * jsp action页面过滤器(除去验证码)
 * @createDate 2013.5.21
 * 
 **/
public class LoginFilter extends HttpServlet implements Filter {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5618746570740546841L;
	
	public void destroy() {
	}

	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpSession session = request.getSession();
		String url = request.getServletPath();
		
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		
		
		
		boolean flag=false;
		
		for (int i = 0; i < WebType.values().length; i++) {
			
			if(url.indexOf("/json")!= -1){
				flag = true;
			}else if(url.indexOf("/front")!= -1) {
				
				flag = true;
				
			} else if (url.indexOf(WebType.valueOf(i).getPackageName()+WebType.valueOf(i)+WebType.valueOf(i).getType())!= -1) {
				flag = true;
			}
		}
		if(!flag){
			
			if (null!=sessionBean&&StringUtils.isNotBlank(sessionBean.getUserId())) {
				
				
				System.out.println("验证用户有效");
			} else {
				//request.getRequestDispatcher("/backend/index.jsp").forward(sRequest, sResponse);
				//return;
				flag = true;
			}
		}
		
		filterChain.doFilter(sRequest, sResponse);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
}
