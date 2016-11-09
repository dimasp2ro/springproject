package com.juger.my.controller.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.juger.my.controller.validation.form.RegistrationForm;

@Component
public class RegistrationValidation implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return RegistrationForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object o, Errors errors) {
		RegistrationForm registrationForm = (RegistrationForm) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "label.validate.emailEmpty", "Email is empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fio", "label.validate.fioIsEmpty", "NSM is empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "label.validate.passwordIsEmpty",
				"password is empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "label.validate.passwordConfirmIsEmpty",
				"password confirm is empty");
		if (!registrationForm.getPassword().equals(registrationForm.getPasswordConfirm())) {
			errors.rejectValue("password", "label.validate.passwordsAreNoEqual",
					"password is not equal to confrm password");
			errors.rejectValue("passwordConfirm", "label.validate.passwordConfirmsAreNoEqual",
					"password is not equal to confrm password");
		}
	}

	public boolean validate(String pattern, String field) {
		Pattern patt = Pattern.compile(pattern);
		Matcher m = patt.matcher(field);
		return m.matches();
	}

}
