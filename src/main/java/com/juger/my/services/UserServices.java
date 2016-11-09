package com.juger.my.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juger.my.dao.UserDao;
import com.juger.my.entity.User;

@Service
public class UserServices {

	@Autowired
	@Qualifier("pers")
	private UserDao userDao;

	@Transactional // (rollbackFor=RuntimeException.class)
	public void create(User user) {
		this.userDao.create(user);
	}

	public User readUserByLogin(String login) {
		User u = this.userDao.readByLogin(login);
		return u != null ? u : null;
	}

	public User readUserByUUID(String uuid) {
		User u = this.userDao.readByUUID(uuid);
		return u != null ? u : null;
	}
	@Transactional
	public void update(User u){
		this.userDao.update(u);
	}
}
