package com.enrtities;

import java.util.*;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="NoteTaker")
public class Note {
	
	@Id
	private int id;
	private String title;
	
	@Column(length = 2000)
	private String content;
	private Date addeDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getAddeDate() {
		return addeDate;
	}
	public void setAddeDate(Date addeDate) {
		this.addeDate = addeDate;
	}
	public Note( String title, String content, Date addeDate) {
		super();
		this.id = new Random().nextInt(100000);
		this.title = title;
		this.content = content;
		this.addeDate = addeDate;
	}
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", content=" + content + ", addeDate=" + addeDate + "]";
	}
	
	
	
	

}
