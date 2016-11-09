package com.juger.my.controller.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.juger.my.controller.validation.form.LoginForm;

@Component
public class LoginValidation implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return LoginForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "label.validate.emailEmpty", "Email is empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "label.validate.passwordIsEmpty", "password is empty");
	}

	public boolean validate(String pattern, String field) {
		Pattern patt = Pattern.compile(pattern);
		Matcher m = patt.matcher(field);
		return m.matches();
	}

}
