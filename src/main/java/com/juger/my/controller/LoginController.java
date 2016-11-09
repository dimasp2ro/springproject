package com.juger.my.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juger.my.controller.utilize.ErrorToJSON;
import com.juger.my.controller.validation.LoginValidation;
import com.juger.my.controller.validation.form.LoginForm;
import com.juger.my.entity.User;
import com.juger.my.services.UserServices;

@RestController
@RequestMapping("/logining")
public class LoginController {

	@Autowired
	private LoginValidation validation;

	@Autowired
	private UserServices userServices;

	
	@RequestMapping(value = "/logining", method = RequestMethod.POST)
	public String checkUser(@Valid @RequestBody LoginForm login, BindingResult result) {
		if (result.hasErrors()) {
			validation.validate(login, result);
			return ErrorToJSON.getError(result).toString();
		} else {
			return isSuccess(login);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String checkLogin(@RequestBody String login) {
		if (!validation.validate("[\\p{Graph}]{5,}", login)) {
			return "{\"login\":\"is invalid value\"}";
		} else {
			return "success";
		}
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public String checkPassword(@RequestBody String registration) {
		if (!validation.validate("[\\p{Graph}]{5,}", registration)) {
			return "{\"password\":\"is invalid value\"}";
		} else {
			return "success";
		}
	}

	@RequestMapping(value = "/getget", method = RequestMethod.GET)
	public String checkLogin() {
		return "success";
	}

	private User toDB(LoginForm login) {
		return userServices.readUserByLogin(login.getLogin());
	}

	private String isSuccess(LoginForm login) {
		User u = toDB(login);
		if (u == null)
			return "{\"login\":\"is incorrect user name\"}";
		else if (!u.getPassword().equals(login.getPassword()))
			return "{\"password\":\"is incorrect password\"}";
		else {
			StringBuilder sb = new StringBuilder("{\"success\":\"");
			sb.append(u.getUuid()).append("\"}");
			return sb.toString();
		}
	}

}
