package com.juger.my.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juger.my.controller.validation.form.NoteForm;
import com.juger.my.dao.NoteDao;
import com.juger.my.entity.Note;
import com.juger.my.entity.User;

@Service
public class NoteServices {
	
	@Autowired
	@Qualifier("pers")
	private NoteDao noteDao;

	@Transactional // (rollbackFor=RuntimeException.class)
	public void create(Note note) {
		this.noteDao.create(note);
	}
	
	public Note read(Long id){
		return noteDao.read(id);
	}
	@Transactional
	public void deleteByPhone(String string) {
		this.noteDao.deleteByPhone(string);
	}
	@Transactional
	public void update(Note note) {
		this.noteDao.update(note);
	}

	public List<Note> filterByPhone(String phone, User u) {
		
		return this.noteDao.filterByPhone(new StringBuilder(phone).append("%").toString(), u);
		
	}

	public List<Note> filterByName(String name, User u) {
		return this.noteDao.filterByName(new StringBuilder(name).append("%").toString(), u);
	}

	public List<Note> filterBySurname(String surname, User u) {
		return this.noteDao.filterBySurname(new StringBuilder(surname).append("%").toString(), u);
		
	}
	
}
