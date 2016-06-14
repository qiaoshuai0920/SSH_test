package com.qs.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("dtss-default")
@Scope("prototype")
public class BaseAction extends ActionSupport implements SessionAware,
		RequestAware, ServletResponseAware {

	private static final long serialVersionUID = -8757701205867627690L;
	protected Map<String, Object> session;
	protected Map<String, Object> request;
	protected HttpServletResponse response;

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
