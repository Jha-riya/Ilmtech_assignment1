package com.example.Tutorials.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;  

@Entity
@Table(name = "tutorial")

public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name= "title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name= "published")
	private boolean published;
	
	public Tutorial() {
		
	}

	public Tutorial(String title, String description, boolean published) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.description = description;
		this.published = published;
	}
	public long getId() {
		return id;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub
		this.title = title;
		
	}

	public void setDescription(String description) {
		// TODO Auto-generated method stub
		this.description = description;
		
	}

	public boolean ispublished() {
		// TODO Auto-generated method stub
		return published;
	}

	public void setpublished(boolean ispublished) {
		// TODO Auto-generated method stub
		this.published = published;
		
	}
	@Override
	public String toString() {
		return "Demo [id=" + id + ",title= " +title+",desc=" +description+",published="+published+"]";
	}

}
