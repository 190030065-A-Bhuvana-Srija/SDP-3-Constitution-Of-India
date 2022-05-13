package com.klu.sdp3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mymodel")
public class MyModel {
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Id
	private String email;
	@Column
	private String gender;
	@Column
	private String password;
	@Column
	private String confirmpassword;
	@Override
	public String toString() {
		return "Model [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password="
				+ password + ", confirmpassword=" + confirmpassword + "]";
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
}
