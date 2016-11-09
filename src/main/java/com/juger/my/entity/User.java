package com.juger.my.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "users")
@NamedQueries({ 
	@NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
	@NamedQuery(name = "User.findByUUID", query = "SELECT u FROM User u WHERE u.uuid = :uuid")
})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String login;

	@NotNull
	private String fio;

	@NotNull
	private String password;

	@NotNull
	private String uuid;
	
	@OneToMany(mappedBy="user")
	private List<Note> notes;
	
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> norwes) {
		this.notes = norwes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public void addNote(Note note) {
        this.notes.add(note);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fio == null) ? 0 : fio.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (fio == null) {
			if (other.fio != null)
				return false;
		} else if (!fio.equals(other.fio))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	


}