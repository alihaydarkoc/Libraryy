package com.alihaydar.Libraryy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.alihaydar.Libraryy.model.Base.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USER")
public class User extends BaseModel{
	private static final long serialVersionUID=1L;
	@NotNull
	@Size(min = 1, max = 50)
	@Column(length = 50, unique = true, nullable = false)
	private String username;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(length = 50, unique = true, nullable = false)
	private String email;
	
	@NotNull
	@Column(nullable = false)
	private boolean activate;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActivate() {
		return activate;
	}

	public void setActivate(boolean activate) {
		this.activate = activate;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Size(max = 20)
	@JsonIgnore
	private String activationKey;
	
	@Size(min = 1, max = 60)
	private String password;
	
	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", activate=" + activate + ", activationKey="
				+ activationKey + ", password=" + password + "]";
	}
	
	
}
