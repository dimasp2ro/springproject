package com.juger.my.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.sym.Name;
import com.juger.my.controller.utilize.ErrorToJSON;
import com.juger.my.controller.validation.NoteValidator;
import com.juger.my.controller.validation.form.NoteForm;
import com.juger.my.entity.Note;
import com.juger.my.entity.User;
import com.juger.my.services.NoteServices;
import com.juger.my.services.UserServices;

@RestController
@RequestMapping("/notes")
public class NoteController {
	
	
	@Autowired
	private NoteValidator validation;
	
	@Autowired
	private UserServices userServices;
	@Autowired
	private NoteServices noteServices;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addNote(@Valid @RequestBody NoteForm note, BindingResult result) {	
		if (result.hasErrors()) {
			validation.validate(note, result);
			return ErrorToJSON.getError(result).toString();
		} else {
			return toDB(note).toString();
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String checkLogin(@RequestBody String phone) {
		deleteFromeDb(phone);
		return "success";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateLogin(@Valid @RequestBody NoteForm note, BindingResult result) {
		if (result.hasErrors()) {
			validation.validate(note, result);
			return ErrorToJSON.getError(result).toString();
		} else {
			editNoteDb(note);
			return "success";
		}
		
	}

	private void editNoteDb(NoteForm note) {
		Note n = noteServices.read(new Long(note.getId()));
		n.setName(note.getName());
		n.setSurname(note.getSurname());
		n.setLastname(note.getName());
		n.setPhone(note.getPhone());
		n.setHomephone(note.getHomephone());
		n.setAddress(note.getAddress());
		n.setEmail(note.getEmail());
		noteServices.update(n);
		
	}

	private void deleteFromeDb(String string) {
		noteServices.deleteByPhone(string);
	}

	private Note toDB(NoteForm note) {
		Note n = new Note();
		User u = userServices.readUserByUUID(note.getUuid());
		n.setUser(u);
		n.setUserid(u.getId());
		n.setEmail(note.getEmail());
		n.setAddress(note.getAddress());
		n.setHomephone(note.getHomephone());
		n.setPhone(note.getPhone());
		n.setLastname(note.getLastname());
		n.setName(note.getName());
		n.setSurname(note.getSurname());
		u.addNote(n);
		noteServices.create(n);
		return n;
	}
	
	
}
