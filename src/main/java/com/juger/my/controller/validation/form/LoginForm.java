package com.juger.my.controller.validation.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {

	@NotNull
	@Size(min = 3)
	private String login;
	@NotNull
	@Size(min = 5)
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder("{");
		out.append(login).append("\",\"password\":\"").append(password).append("\"}");
		return out.toString();
	}

}
