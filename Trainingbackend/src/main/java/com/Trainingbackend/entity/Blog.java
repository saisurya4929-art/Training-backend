package com.Trainingbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="blog")
public class Blog {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	  private String title;
	  private String category;
	  @Column(columnDefinition = "TEXT")
	  private String image;
	  @Column(columnDefinition = "TEXT")
	  private String description;
	  
	  public Blog() {}
	  
	  public Blog(String title,String category,String image,String description ) {
		  this.title=title;
		  this.category=category;
		  this.image=image;
		  this.description=description;
		  
  }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
