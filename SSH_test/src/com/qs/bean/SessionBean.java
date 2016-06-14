package com.qs.bean;

import com.qs.entity.User;

/**
 * 存放session参数用于全局使用
 * 
 * @author learrings
 * @createTime 2014.01.10
 **/
public class SessionBean {

	private String userId; 		// 系统操作用户ID
	private String userName;	// 用户名称
	private String userSelfId; 		// 登录用户ID
	private int ifPrimaryUser;
	private String url;
	// 初始化对象
	public SessionBean() {
	}

	// 实例化对象
	public SessionBean(User user) {
		this.setUserId(user.getUserId());
		this.setUserName(user.getUserName());
		//this.setUserType(user.getUserType());
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserSelfId() {
		return userSelfId;
	}

	public void setUserSelfId(String userSelfId) {
		this.userSelfId = userSelfId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIfPrimaryUser() {
		return ifPrimaryUser;
	}

	public void setIfPrimaryUser(int ifPrimaryUser) {
		this.ifPrimaryUser = ifPrimaryUser;
	}
	
	
}
