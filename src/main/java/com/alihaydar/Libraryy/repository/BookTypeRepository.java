package com.alihaydar.Libraryy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alihaydar.Libraryy.model.BookTypeModel;
@Repository
public interface BookTypeRepository extends JpaRepository<BookTypeModel,Long> {
	public List<BookTypeModel> findByTypeName(String typeName);
}
