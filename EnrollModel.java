package com.klu.sdp3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="enrollmodel")
public class EnrollModel 
{
	@Id
	private String coursetitle;
	@Column
	private String useremail;
	public String getCoursetitle() {
		return coursetitle;
	}
	public void setCoursetitle(String coursetitle) {
		this.coursetitle = coursetitle;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

}
