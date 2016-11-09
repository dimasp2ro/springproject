package com.juger.my.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.juger.my.entity.Note;
import com.juger.my.entity.User;

@Repository
@Qualifier("pers")
public class NoteDaoImpl extends AbstractDao<Note> implements NoteDao {
	@PersistenceContext
	private EntityManager entityManager;

	public NoteDaoImpl() {
		super(Note.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public void deleteByPhone(String string) {
		entityManager.createNamedQuery("Note.deleteByPhone").setParameter("phone", string).executeUpdate();		
	}

	@Override
	public List<Note> filterByPhone(String phone, User u) {
		return entityManager.createNamedQuery("Note.filterByPhone").setParameter("user", u).setParameter("phone", phone).getResultList();
	}

	@Override
	public List<Note> filterByName(String string, User u) {
		return entityManager.createNamedQuery("Note.filterByName").setParameter("user", u).setParameter("name", string).getResultList();
	}
	
	@Override
	public List<Note> filterBySurname(String string, User u) {
		return entityManager.createNamedQuery("Note.filterBySurname").setParameter("user", u).setParameter("surname", string).getResultList();
	}

	
}
