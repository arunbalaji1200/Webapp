package com.cts.gsd.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.gsd.model.User;
import com.cts.gsd.repository.RoleRepository;
import com.cts.gsd.repository.UserRepository;

@Service
public class UserService {

//	public void setUserRepository(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//
//	public void setRoleRepository(RoleRepository roleRepository) {
//		this.roleRepository = roleRepository;
//	}

	public void setLog(Logger log) {
		this.log = log;
	}

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	 Logger log=LoggerFactory.getLogger(UserService.class);
	
	 @Cacheable("cacheuserid")
	public User addUser(@PathVariable("id") int id,@RequestBody User user) {
		roleRepository.findById(id);
		return userRepository.save(user);		
	}
	
	public List<User> getUser() {
		log.info("getUsers");
		return userRepository.findAll();	
	}
	
	public User getUserId(@PathVariable int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User updateUser(@PathVariable int id,@RequestBody User user) {
		userRepository.findById(id);
		return userRepository.saveAndFlush(user);
	}
	
	public void deleteUser(@PathVariable("id") int id) {
		userRepository.deleteById(id);
	}
}
