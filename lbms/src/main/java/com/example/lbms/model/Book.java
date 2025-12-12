package com.example.lbms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Title is required")
	private String title;

	@NotBlank(message = "Author is required")
	private String author;

	@Min(value = 2, message = "Pages should be atleast 2")
	private int pages;

	@NotNull(message = "Avaiable copies are required")
	private Integer avaialableCopies;

	public Book() {
	}

	public Book(Integer id, @NotBlank(message = "Title is required") String title,
			@NotBlank(message = "Author is required") String author,
			@Min(value = 2, message = "Pages should be atleast 2") int pages,
			@NotNull(message = "Avaiable copies are required") Integer avaialableCopies) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.avaialableCopies = avaialableCopies;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
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

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Integer getAvaialableCopies() {
		return avaialableCopies;
	}

	public void setAvaialableCopies(Integer avaialableCopies) {
		this.avaialableCopies = avaialableCopies;
	}

}
