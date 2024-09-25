package com.cts.gsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.gsd.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
