package com.juger.my.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.juger.my.entity.User;

@Repository
@Qualifier("pers")
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public User readByLogin(String login) {
		List<User> results = entityManager.createNamedQuery("User.findByLogin").setParameter("login", login)
				.getResultList();
		return results.size() == 0 ? null : results.get(0);
	}

	@Override
	public User readByUUID(String uuid) {
		List<User> results = entityManager.createNamedQuery("User.findByUUID").setParameter("uuid", uuid)
				.getResultList();
		return results.size() == 0 ? null : results.get(0);
	}

}