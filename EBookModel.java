package com.klu.sdp3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ebookmodel")
public class EBookModel {
	// title, author, cost, language, filesize
	@Id
	private String title;
	@Column
	private String author;
	@Column
	private String language;
	@Column
	private int cost;
	@Column 
	private int filesize;
	@Column(nullable = true)
    private String pdf;
	public String getTitle() {
		return title;
	}
	public String getPdf() {
		return pdf;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public EBookModel(String title, String author, String language, int cost, int filesize) {
		super();
		this.title = title;
		this.author = author;
		this.language = language;
		this.cost = cost;
		this.filesize = filesize;
	}
	public EBookModel() {
	}
}
