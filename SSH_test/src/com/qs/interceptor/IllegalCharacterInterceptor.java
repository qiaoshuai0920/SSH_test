package com.qs.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Created by IntelliJ IDEA. User: gaohuichao Date: 2014-6-4 Time: 10:14:40 To
 * change this template use File | Settings | File Templates.
 */
public class IllegalCharacterInterceptor extends AbstractInterceptor {

	/**
	 * @author qiaoshuai
	 * @createDate 2016-3-23
	 */
	private static final long serialVersionUID = -7084201694826242886L;
	private static Pattern SCRIPT_PATTERN = Pattern
			.compile("<script.*>.*<\\/script\\s*>");
	private static Pattern HTML_PATTERN = Pattern.compile("<[^>]+>");

	public String intercept(ActionInvocation invocation) throws Exception {
		final ActionContext context = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);

		String requstUrl = request.getRequestURL().toString();
		if (!requstUrl.contains("travelDaren/superiorProgramAddSave.action")) {
			if (!requstUrl
					.contains("travelDaren/superiorProgramUpdateSave.action")) {

				Map paramMap = request.getParameterMap();

				int flag = 0;
				String lowStr = null;
				Set keSet = paramMap.entrySet();
				for (Iterator itr = keSet.iterator(); itr.hasNext();) {
					Map.Entry me = (Map.Entry) itr.next();
					Object ok = me.getKey();
					Object ov = me.getValue();
					String[] value = new String[1];
					if (ov instanceof String[]) {
						value = (String[]) ov;
					} else {
						value[0] = ov.toString();
					}
					for (int k = 0; k < value.length; k++) {
						lowStr = value[k];
						// 过滤html标签
						Matcher mHtml = HTML_PATTERN.matcher(lowStr);
						if (mHtml.find()) {
							flag = 1;
						}
						// 过滤script脚本
						Matcher m = SCRIPT_PATTERN.matcher(lowStr);
						if (m.find()) {
							flag = 1;
						}
						// 过滤sql转换函数
						if (lowStr.contains("ascii(")
								|| lowStr.contains("ascii (")
								|| lowStr.contains("chr(")
								|| lowStr.contains("chr (")) {
							flag = 1;
						}
						// 过滤sql关键字
						if (lowStr.contains("alter ")
								|| lowStr.contains("create ")
								|| lowStr.contains("truncate ")
								|| lowStr.contains("drop ")
								|| lowStr.contains("lock table")
								|| lowStr.contains("insert ")
								|| lowStr.contains("update ")
								|| lowStr.contains("delete ")
								|| lowStr.contains("select ")
								|| lowStr.contains("grant ")) {
							flag = 1;
						}

					}
				}
				if (flag == 1) {
					response.sendRedirect(request.getContextPath()
							+ "/red_url.jsp?redurl=" + request.getRequestURL());
				}
			}
		}
		return invocation.invoke();

	}

	public void destroy() {
	}

	public void init() {
	}

}