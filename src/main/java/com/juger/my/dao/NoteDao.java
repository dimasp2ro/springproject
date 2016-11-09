package com.juger.my.dao;

import java.util.List;

import com.juger.my.entity.Note;
import com.juger.my.entity.User;

public interface NoteDao {
	
	void create(Note n);
	
	Note read(Object id);

	void deleteByPhone(String string);

	void update(Note note);

	List<Note> filterByPhone(String phone, User u);

	List<Note> filterByName(String string, User u);

	List<Note> filterBySurname(String string, User u);
	
}
