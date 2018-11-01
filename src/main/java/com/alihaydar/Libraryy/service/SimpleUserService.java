package com.alihaydar.Libraryy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alihaydar.Libraryy.email.NotificationService;
import com.alihaydar.Libraryy.model.User;
import com.alihaydar.Libraryy.repository.UserRepository;
import com.alihaydar.Libraryy.service.base.UserService;
import com.alihaydar.Libraryy.util.DateUtil;
import com.alihaydar.Libraryy.util.RandomUtil;

@Service
public class SimpleUserService implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User addUser(User user) {
		User userModel = new User();
		userModel.setCreatedDate(DateUtil.now());
		userModel.setLastModifiedDate(DateUtil.now());
		userModel.setUsername(user.getUsername());
		userModel.setEmail(user.getEmail());
		userModel.setActivationKey(RandomUtil.generateActivationKey());
		userModel.setActivate(false);
		userModel.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(userModel);

		try {
			NotificationService.sendEmail(userModel);
		} catch (Exception e) {
			
		}
		return userModel;
	}	
	
	@Override
	public User updateUser(User user, User existUsername) {
		existUsername.setLastModifiedDate(DateUtil.now());
		existUsername.setPassword(passwordEncoder.encode(user.getPassword()));
		existUsername.setEmail(user.getEmail());
		return userRepository.save(existUsername);
	}
	
	@Override
	public List<User> getAllUsers() {
		List<User> userList = userRepository.findAll();
		return userList;
	}

	@Override
	public void deleteUser(User existUser) {
		userRepository.delete(existUser);
	}

	@Override
	public User findUser(String username) {
		User existUsername = userRepository.findByUsername(username);
		return existUsername;
	}

	@Override
	public void activate(User user) {
		user.setActivate(true);
		user.setActivationKey(null);
		user.setLastModifiedDate(DateUtil.now());
		userRepository.save(user);
	}
}
