package com.juger.my.controller.utilize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErrorToJSON {
	
	public static JSONObject getError(BindingResult bindingResult) {
		return new JSONObject(getMapFromErrors(bindingResult.getFieldErrors()));
	}

	private static Map<String, String> getMapFromErrors(List<FieldError> list) {
		Map<String, String> map = new HashMap<String, String>();
		for (FieldError fieldError : list) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return map;
	}
}
