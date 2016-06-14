package com.qs.tags;

import com.qs.bean.Pagination;


public class PaginJsTag {
	public String doStartTag(Pagination pager){

			StringBuffer urlStr = new StringBuffer();
			
			if (!StrUtil.isNull(pager)) {
				urlStr.append("共"+pager.getTotalResult()+"条&nbsp;");		
				if (StrUtil.isEqual(String.valueOf(pager.getHasPreviousPage()),
						"true")) {
					urlStr.append("<a href=\"javascript:void(0);\"");
					urlStr.append(" onclick=\"getResult('1','"+pager.getTotalPageNum()+"','');\">");
					urlStr.append("首页</a>");
					
					urlStr.append("<a href=\"javascript:void(0);\"");
					urlStr.append(" onclick=\"getResult('"+pager.getCurrentPage()+"','"+pager.getTotalPageNum()+"','previous');\">");
					urlStr.append("上一页</a>");
				}
				urlStr.append(pager.getStrUrl());
				if (StrUtil.isEqual(String.valueOf(pager.getHasNextPage()),
						"true")) {
					urlStr.append("<a href=\"javascript:void(0);\"");
					urlStr.append(" onclick=\"getResult('"+pager.getCurrentPage()+"','"+pager.getTotalPageNum()+"','next');\">");
					urlStr.append("下一页</a>");
					
					urlStr.append("<a href=\"javascript:void(0);\"");
					urlStr.append(" onclick=\"getResult('"+pager.getTotalPageNum()+"','"+pager.getTotalPageNum()+"','');\">");
					urlStr.append("尾页</a>");
				}
			}
		return urlStr.toString();
	}
}
