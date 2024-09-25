package com.cts.gsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.gsd.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(value = "select u.emailid from user u where u.id=:id",nativeQuery = true)
	String getEmailid(int id);
	
	@Query(value="select um.emailid from user u join manager m on u.id =m.userid join user um on um.id=m.managerid where u.id=:id"
			,nativeQuery = true)
	String managerEmailid(int id);
}
