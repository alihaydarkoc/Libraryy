package com.alihaydar.Libraryy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alihaydar.Libraryy.model.BookTypeModel;
import com.alihaydar.Libraryy.repository.BookTypeRepository;
import com.alihaydar.Libraryy.util.DateUtil;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/bookType")
public class BookTypeController {
	
	@Autowired
	private BookTypeRepository bookTypeRepository;
	
	@PostMapping(path = "/addBookType", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public BookTypeModel addBookType(@Valid @RequestBody BookTypeModel bookType) {

		BookTypeModel bookTypeModel = new BookTypeModel();
		bookTypeModel.setCreatedDate(DateUtil.now());
		bookTypeModel.setLastModifiedDate(DateUtil.now());
		bookTypeModel.setTypeName(bookType.getTypeName());
		bookTypeRepository.save(bookTypeModel);
		return bookTypeModel;
	}
	
	@GetMapping(path = "/all")
	public List<BookTypeModel> getBookList(){
		List<BookTypeModel> bookTypeModelList = bookTypeRepository.findAll();
		return bookTypeModelList;
	}
	
	@GetMapping(path = "/{type}")
	public List<BookTypeModel> getBookListByType(@PathVariable String type){
		List<BookTypeModel> bookTypeListByType = bookTypeRepository.findByTypeName(type);
		return bookTypeListByType;
	}
}
