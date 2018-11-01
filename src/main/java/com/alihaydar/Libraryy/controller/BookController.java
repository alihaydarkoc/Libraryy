package com.alihaydar.Libraryy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alihaydar.Libraryy.model.BookModel;
import com.alihaydar.Libraryy.repository.BookRepository;
import com.alihaydar.Libraryy.util.DateUtil;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/all")
	public List<BookModel> getBookList() {
		List<BookModel> bookList = bookRepository.findAll();
		return bookList;
	}

	@GetMapping("/{bookName}")
	private BookModel getBookListByBookName(@PathVariable String bookName) {
		BookModel book = bookRepository.findByBookName(bookName);
		return book;
	}

	@PostMapping(path = "/addBook", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> addBook(@Valid @RequestBody BookModel book) {
		
		 HttpHeaders textPlainHeaders = new HttpHeaders();
	     textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
	        
		BookModel existsBook = bookRepository.findByBookName(book.getBookName());
		if (existsBook != null) {
			return new ResponseEntity<>("Book name already exists", textPlainHeaders, HttpStatus.BAD_REQUEST);
		} else {
			BookModel bookModel = new BookModel();
			bookModel.setCreatedDate(DateUtil.now());
			bookModel.setLastModifiedDate(DateUtil.now());
			bookModel.setBookName(book.getBookName());
			bookModel.setAuthorName(book.getAuthorName());
			bookModel.setAuthorSurname(book.getAuthorSurname());
			bookModel.setBookTypeModel(book.getBookTypeModel());
			bookRepository.save(bookModel);
		}
			
		return new ResponseEntity<>(HttpStatus.CREATED); 
	}

	@DeleteMapping(path = "/delete/{id}")
	public BookModel deleteBook(@PathVariable Long id) {
		BookModel model = bookRepository.findOne(id);
		bookRepository.delete(model);
		return model;
	}

	@PostMapping(path = "/updateBook", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> updateBook(@Valid @RequestBody BookModel book) {
		
		HttpHeaders textPlainHeaders = new HttpHeaders();
	    textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
	     
		BookModel existsBook = bookRepository.findByBookName(book.getBookName());
		if (existsBook != null) {
			existsBook.setLastModifiedDate(DateUtil.now());
			existsBook.setBookName(book.getBookName());
			existsBook.setAuthorName(book.getAuthorName());
			existsBook.setAuthorSurname(book.getAuthorSurname());
			existsBook.setBookTypeModel(book.getBookTypeModel());
			bookRepository.save(existsBook);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}else
			return new ResponseEntity<>("Book not found", textPlainHeaders, HttpStatus.BAD_REQUEST);
	}
}
