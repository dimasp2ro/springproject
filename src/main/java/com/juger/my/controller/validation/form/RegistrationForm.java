package com.juger.my.controller.validation.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationForm {

	@NotNull
	@Size(min = 3)
	private String login;
	@NotNull
	@Size(min = 3, max = 5)
	private String fio;
	@NotNull
	@Size(min = 5)
	private String password;

	@NotNull
	@Size(min = 5)
	private String passwordConfirm;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder("{\"login\":\"");
		out.append(login).append("\",\"fio\":\"").append(fio).append("\",\"password\":\"").append(password)
				.append("\",\"passwordConfirm\":\"").append(passwordConfirm).append("\"}");
		return out.toString();
	}

}
