package com.juger.my.dao;

import com.juger.my.entity.User;

public interface UserDao {
	
	void create(User user);
	User readByLogin(String id);
	User readByUUID(String uuid);
	void update(User u);

}
