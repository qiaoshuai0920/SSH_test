package com.qs.service;

import java.util.List;

import com.qs.entity.User;

public interface UserService {

	public List<User> listUser();

	public void delUser(String Id);

	public void addUser(User user);

}
