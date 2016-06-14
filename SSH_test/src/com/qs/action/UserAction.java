package com.qs.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qs.entity.User;
import com.qs.service.UserService;

/**
 * 用户控制层
 * 
 * @author qs
 **/
@Controller
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 819379728994045222L;
	@Autowired
	private UserService userService;

	private List<User> listUser;
	private User user;

	@Action(value = "/user/listUser", results = { @Result(name = "success", location = "/listUser.jsp") })
	public String listUser() {
		listUser = userService.listUser();
		return SUCCESS;
	}

	@Action(value = "/user/delUser", results = { @Result(name = "success", location = "/user/listUser.action", type = "redirect") })
	public String delUser() {
		userService.delUser(user.getUserId());
		return SUCCESS;
	}

	@Action(value = "/user/addUser", results = { @Result(name = "success", location = "/user/listUser.action", type = "redirect") })
	public String addUser() {
		userService.addUser(user);
		return SUCCESS;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
