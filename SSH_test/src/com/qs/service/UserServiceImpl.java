package com.qs.service;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qs.entity.User;
import com.qs.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public List<User> listUser() {
		// TODO Auto-generated method stub
		return userDao.listAll();
	}

	@Transactional
	public void delUser(String Id) {
		// TODO Auto-generated method stub
		User user = userDao.getById(Id);
		userDao.delete(user);
	}

	@Transactional
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.saveOrUpdate(user);
	}

}
