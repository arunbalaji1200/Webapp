package com.cts.gsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.gsd.model.RequestForm;
import com.cts.gsd.model.RequestForm.Status;

public interface RequestFormRepository extends JpaRepository<RequestForm, Integer> {

	@Modifying
	@Query(value = "update RequestForm r set r.status = :status where r.id= :id")
	void setUpdateStatus (@Param("id") int id,@Param("status") Status status); 
	
	@Modifying
	@Query(value = "update RequestForm r set r.comments = :comments where r.id= :id")
	void setComment (@Param("id") int id,@Param("comments") String comments); 
}
