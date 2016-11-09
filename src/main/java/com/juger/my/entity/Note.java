package com.juger.my.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "notes")
@NamedQueries({ 
	@NamedQuery(name = "Note.deleteByPhone", query = "DELETE FROM Note n WHERE n.phone = :phone"),
	@NamedQuery(name = "Note.filterByPhone", query = "SELECT n FROM Note n WHERE n.user = :user AND n.phone LIKE :phone"),
	@NamedQuery(name = "Note.filterByPhone", query = "SELECT n FROM Note n WHERE n.user = :user AND n.name LIKE :name"),
	@NamedQuery(name = "Note.filterByPhone", query = "SELECT n FROM Note n WHERE n.user = :user AND n.surname LIKE :surname")
})
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private long userid;
	@NotNull
	private String name;
	@NotNull
	private String surname;
	@NotNull
	private String lastname;
	@NotNull
	private String phone;

	private String homephone;

	private String address;

	private String email;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid", insertable = false, updatable = false)
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((homephone == null) ? 0 : homephone.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + (int) (userid ^ (userid >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (homephone == null) {
			if (other.homephone != null)
				return false;
		} else if (!homephone.equals(other.homephone))
			return false;
		if (id != other.id)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (userid != other.userid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder("{\"userid\":\"");
		out.append(userid).append("\",\"name\":\"").append(name).append("\",\"surname\":\"").append(surname)
				.append("\",\"lastname\":\"").append(lastname).append("\",\"phone\":\"").append(phone)
				.append("\",\"homephone\":\"").append(homephone == null ? "" : homephone).append("\",\"address\":\"")
				.append(address == null ? "" : address).append("\",\"email\":\"").append(email == null ? "" : email)
				.append("\",\"id\":\"").append(id).append("\"}");
		return out.toString();
	}

}
