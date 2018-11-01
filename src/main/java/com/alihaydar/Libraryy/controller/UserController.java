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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alihaydar.Libraryy.model.User;
import com.alihaydar.Libraryy.repository.UserRepository;
import com.alihaydar.Libraryy.service.SimpleUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private SimpleUserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/all")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping(path = "/addUser", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		
		User existUsername = userRepository.findByUsername(user.getUsername());
		if (existUsername != null)
			return new ResponseEntity<>("Username already exists", textPlainHeaders, HttpStatus.BAD_REQUEST);
		User existEmail = userRepository.findByEmail(user.getEmail());
		if (existEmail != null)
			return new ResponseEntity<>("Email already exists", textPlainHeaders, HttpStatus.BAD_REQUEST);

		userService.addUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/updateUser", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		User existUsername = userRepository.findByUsername(user.getUsername());
		if (existUsername != null) {
			userService.updateUser(user, existUsername);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}else
			return new ResponseEntity<>("Username not found", textPlainHeaders, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(path = "delete/{username}")
	public ResponseEntity<?> deleteUser(@PathVariable String username) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		User existUsername = userRepository.findByUsername(username);
		if (existUsername != null) {
			userService.deleteUser(existUsername);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else
			return new ResponseEntity<>("USERNAME NOT FOUND", textPlainHeaders, HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/findUser/{username}")
	public User findUser(@PathVariable String username) {
		return userService.findUser(username);
	}
	
	@GetMapping(path = "/active/{activationKey}")
	public ResponseEntity<?> activate(@PathVariable String activationKey) {
		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
		User existUser = userRepository.findByActivationKey(activationKey);
		if (existUser != null) {
			userService.activate(existUser);
			return new ResponseEntity<>("CONGRATULATIONS! YOU CAN USE APP NOW", textPlainHeaders, HttpStatus.OK);
		}
		return new ResponseEntity<>("USER NOT FOUND!", textPlainHeaders, HttpStatus.BAD_REQUEST);
	}
}
