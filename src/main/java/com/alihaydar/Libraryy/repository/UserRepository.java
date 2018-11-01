package com.alihaydar.Libraryy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alihaydar.Libraryy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	User findByActivationKey(String activationKey);
	
}
