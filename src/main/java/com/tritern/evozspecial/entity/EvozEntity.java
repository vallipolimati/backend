package com.tritern.evozspecial.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "evoztable")
public class EvozEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", length = 55, nullable = false, unique = false)
	private String name;

	@Column(name = "emailid")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "username", length = 55, nullable = false, unique = true)
	private String username;

	@Column(name = "saltpassword")
	private String salt;

	public EvozEntity() {

	}

	public EvozEntity(Long id, String name, String email, String password, String username, String salt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.username = username;
		this.salt = salt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "EvozEntity [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", username="
				+ username + ", salt=" + salt + "]";
	}

}
