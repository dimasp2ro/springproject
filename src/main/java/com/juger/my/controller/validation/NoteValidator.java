package com.juger.my.controller.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.juger.my.controller.validation.form.NoteForm;

@Component
public class NoteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NoteForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

	}

}
