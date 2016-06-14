package com.qs.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.qs.bean.Pagination;

public class PageUtil {
	
	/**
	 * 生成分页
	 * 
	 * 
	 * @param totalNum 总数
	 * @param ACTIONURL 分页路径
	 * @param pageSize 单页显示条数，为空则显示默认条数
	 * @createTime 2013.9.16
	 **/
	public static Pagination getPagination(int totalResult,String ACTIONURL,Integer pageSize) {
	     
		Pagination pagination=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		if (StringUtils.isBlank(request.getParameter("currentPage"))) { // 初始化页面数据，默认显示10条
			pagination = new Pagination(totalResult, ACTIONURL,pageSize);
				
		} else {
			
			pagination = Pagination.getPager(request, ACTIONURL,pageSize);
			pagination.setTotalResult(totalResult);
		}
		return pagination;
	}

	/**
	 * （js）生成分页
	 * createTime 2013.9.16
	 * @param totalNum 总数
	 * @param ACTIONURL 分页路径
	 **/
	public static Pagination getJsPage(int totalResult,String ACTIONURL) {
		Pagination pagination = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		if (StringUtils.isBlank(request.getParameter("currentPage"))) { // 初始化页面数据，默认显示10条
			pagination = new Pagination(totalResult, ACTIONURL, "js");
		} else {
			pagination = Pagination.getJsPager(request, ACTIONURL);
			pagination.setTotalResult(totalResult);
		}
		return pagination;
	}
	
}
