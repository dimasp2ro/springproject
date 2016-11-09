package com.juger.my.controller.validation.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NoteForm {

	private int id;
	@NotNull
	@Size(min = 4)
	private String name;
	@NotNull
	@Size(min = 4)
	private String surname;
	@NotNull
	@Size(min = 4)
	private String lastname;
	@NotNull
	@Pattern(regexp = "[+]{1}[3]{1}[8]{1}[(]{0,1}[0-9]{3}[)]{0,1}[0-9]{7}")
	private String phone;

	@Pattern(regexp = "([+]{1}[3]{1}[8]{1}[(]{0,1}[0-9]{3,4}[)]{0,1}[0-9]{7}){0,1}")
	private String homephone;
	@Size(max = 100)
	private String address;

	@Pattern(regexp = "(^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$){0,1}")
	private String email;
	@NotNull
	private String uuid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
