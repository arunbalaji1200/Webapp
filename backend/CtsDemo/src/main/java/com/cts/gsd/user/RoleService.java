package com.cts.gsd.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.gsd.exception.RoleException;
import com.cts.gsd.model.Role;
import com.cts.gsd.model.User;
import com.cts.gsd.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role addRole(@PathVariable("id")int id, @RequestBody Role roles) {
		roleRepository.findById(id);
		roleRepository.save(roles);	
		return roles;
	}
	
	public List<Role> getRole() {
		return roleRepository.findAll();
	}
	
	public Role getRoleId(@PathVariable int id) {
		return roleRepository.findById(id).orElse(null);
	}
	
	public Role updateRole(@PathVariable("id")int id,@RequestBody Role role) {
		roleRepository.findById(id);
		return roleRepository.saveAndFlush(role);
	}
	
	public void deleteRole(@PathVariable("id") int id){
		roleRepository.deleteById(id);
	}
}
