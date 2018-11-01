package com.alihaydar.Libraryy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.alihaydar.Libraryy.model.Base.BaseModel;
@Entity
@Table(name = "BOOK_TYPE")
public class BookTypeModel extends BaseModel{
	private static final long serialVersionUID = 1L;

	@Size(max = 40)
	private String typeName;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "bookTypeModel")
	List<BookModel> books = new ArrayList<BookModel>();

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<BookModel> getBooks() {
		return books;
	}

	public void setBooks(List<BookModel> books) {
		this.books = books;
	}
}
