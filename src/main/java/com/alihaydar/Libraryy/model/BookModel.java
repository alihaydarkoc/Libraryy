package com.alihaydar.Libraryy.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.alihaydar.Libraryy.model.Base.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name = "BOOK")
public class BookModel extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Size(max = 50)
	private String bookName;

	@Size(max = 50)
	private String authorName;

	@Size(max = 50)
	private String authorSurname;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_type_model_id", nullable = false)
	private BookTypeModel bookTypeModel;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorSurname() {
		return authorSurname;
	}

	public void setAuthorSurname(String authorSurname) {
		this.authorSurname = authorSurname;
	}

	@JsonIgnore
	@JsonProperty
	public BookTypeModel getBookTypeModel() {
		return bookTypeModel;
	}

	public void setBookTypeModel(BookTypeModel bookTypeModel) {
		this.bookTypeModel = bookTypeModel;
	}
}
