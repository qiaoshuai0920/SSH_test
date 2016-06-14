package com.qs.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.qs.bean.Pagination;

public class PaginationTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			pageContext.getResponse().setContentType("text/html;charset=UTF-8");
			JspWriter out = pageContext.getOut();
			StringBuffer urlStr = new StringBuffer();
			Pagination pager = (Pagination) request.getAttribute("pagination");
			if (!StrUtil.isNull(pager)) {
				urlStr.append("共"+pager.getTotalResult()+"条记录&nbsp;");		
				if (StrUtil.isEqual(String.valueOf(pager.getHasPreviousPage()),"true")) {
					urlStr.append("<a href='");
					urlStr.append(pager.getAction());
					urlStr.append("currentPage=1&totalPages="+ pager.getTotalPageNum() + "'>首页&nbsp;</a>");
					
					urlStr.append("<a href='");
					urlStr.append(pager.getAction());
					urlStr.append("pageroperation=previous&currentPage="
							+ pager.getCurrentPage() + "&totalPages="
							+ pager.getTotalPageNum() + "'>上一页&nbsp;</a>");
				}
				urlStr.append(pager.getStrUrl());
				if (StrUtil.isEqual(String.valueOf(pager.getHasNextPage()),
						"true")) {
					urlStr.append("<a href='");
					urlStr.append(pager.getAction());
					urlStr.append("pageroperation=next&currentPage="
							+ pager.getCurrentPage() + "&totalPages="
							+ pager.getTotalPageNum() + "'>&nbsp;下一页</a>");
					
					urlStr.append("<a href='");
					urlStr.append(pager.getAction());
					urlStr.append("currentPage="+pager.getTotalPageNum()+"&totalPages="+ pager.getTotalPageNum() + "'>&nbsp;尾页</a>");
				}
			}
			out.print(urlStr);
		} catch (java.io.IOException e) {
			throw new JspTagException(e.getMessage());
		}

		return SKIP_BODY;
	}
}
