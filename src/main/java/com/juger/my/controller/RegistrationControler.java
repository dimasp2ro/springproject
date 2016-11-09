package com.juger.my.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juger.my.controller.utilize.ErrorToJSON;
import com.juger.my.controller.validation.RegistrationValidation;
import com.juger.my.controller.validation.form.RegistrationForm;
import com.juger.my.entity.User;
import com.juger.my.services.UserServices;

@RestController
@RequestMapping("/registr")
public class RegistrationControler {

	@Autowired
	private RegistrationValidation validation;

	@Autowired
	private UserServices userServices;;

	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String checkUser(@Valid @RequestBody RegistrationForm registration, BindingResult result) {
		validation.validate(registration, result);
		if (result.hasErrors()) {
			return ErrorToJSON.getError(result).toString();
		} else {
			User u = toDB(registration);
			StringBuilder sb = new StringBuilder("{\"success\":\"");
			sb.append(u.getUuid()).append("\"}");
			return sb.toString();
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String checkLogin(@RequestBody String registration) {
		if (!validation.validate("[\\p{Graph}]{5,}", registration)) {
			return "{\"login\":\"is invalid value\"}";
		} else {
			return "success";
		}
	}

	@RequestMapping(value = "/fio", method = RequestMethod.POST)
	public String checkFio(@RequestBody String registration) {
		if (!validation.validate("[\\p{Graph}]{3,5}", registration)) {
			return "{\"fio\":\"is invalid value\"}";
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

	@RequestMapping(value = "/passwordConfirm", method = RequestMethod.POST)
	public String checkPasswordConfirm(@RequestBody String registration) {
		if (!validation.validate("[\\p{Graph}]{5,}", registration)) {
			return "{\"passwordConfirm\":\"is invalid value\"}";
		} else {
			return "success";
		}
	}

	private User toDB(RegistrationForm registration) {
		User u = new User();
		u.setLogin(registration.getLogin());
		u.setPassword(registration.getPassword());
		u.setFio(registration.getFio());
		u.setUuid(UUID.randomUUID().toString());
		userServices.create(u);
		return u;
	}

}
