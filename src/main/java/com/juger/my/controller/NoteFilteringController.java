package com.juger.my.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juger.my.controller.validation.form.NoteForm;
import com.juger.my.entity.Note;
import com.juger.my.entity.User;
import com.juger.my.services.NoteServices;
import com.juger.my.services.UserServices;

@RestController
@RequestMapping("/notesfiltring")
public class NoteFilteringController {
	
	@Autowired
	private UserServices userServices;
	@Autowired
	private NoteServices noteServices;
	
	@RequestMapping(value = "/filterbyphone", method = RequestMethod.POST)
	public String filyerByPhone(@RequestBody NoteForm note) {
		return listToString(filterByPhone(note));
	}
	@RequestMapping(value = "/filterbyname", method = RequestMethod.POST)
	public String filyerByName(@RequestBody NoteForm note) {
		return listToString(filterByPhone(note));
	}
	@RequestMapping(value = "/filterbysurname", method = RequestMethod.POST)
	public String filyerBySurname(@RequestBody NoteForm note) {
		return listToString(filterByPhone(note));
	}

	private List<Note> filterByPhone(NoteForm note) {
		User u = userServices.readUserByUUID(note.getUuid());
		return noteServices.filterByPhone(note.getPhone(), u);
		
	}
	private List<Note> filterByName(NoteForm note) {
		User u = userServices.readUserByUUID(note.getUuid());
		return noteServices.filterByName(note.getName(), u);
		
	}
	private List<Note> filterBySurname(NoteForm note) {
		User u = userServices.readUserByUUID(note.getUuid());
		return noteServices.filterBySurname(note.getSurname(), u);
		
	}
	
	private String listToString(List<Note> list){
		if(list.size()==0)
			return "{\"success\":true, \"notes\" : []}";
		StringBuilder sb = new StringBuilder("{\"success\": true, \"notes\" : [");
		for (Note note : list) {
			sb.append(note.toString()).append(",");
		}
		sb.deleteCharAt(sb.length()-1).append("]}");
		return sb.toString();
	}
	
	
}
