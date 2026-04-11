package com.Trainingbackend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Gallery")
public class Gallery {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
     private long id;
     
     private String name;
     private String category;
     private String imagename;
     private String imageurl;
      
     
     public Gallery() {}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getImagename() {
		return imagename;
	}


	public void setImagename(String imagename) {
		this.imagename = imagename;
	}


	public String getImageurl() {
		return imageurl;
	}


	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
       

}
