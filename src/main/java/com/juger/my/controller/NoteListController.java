package com.juger.my.controller;

import java.util.List;

import javax.swing.JScrollBar;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juger.my.entity.Note;
import com.juger.my.entity.User;
import com.juger.my.services.UserServices;

@RestController
@RequestMapping("/notelist")
public class NoteListController {
	
	@Autowired
	private UserServices userServices;
	
	
	@RequestMapping(value = "/getbyname", method = RequestMethod.POST)
	public String checkLogin(@RequestBody String uuid) throws JSONException {
		User u = userServices.readUserByUUID(uuid);
		return listToString(u.getNotes());
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
