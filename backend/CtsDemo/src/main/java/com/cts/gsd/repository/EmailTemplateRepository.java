package com.cts.gsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.gsd.model.EmailTemplate;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Integer>{
	
//	@Query(value = "select u.emailid from user u where u.id=6")
//	String getEmailid();
	
	@Query(value = "select e.mailContent from EmailTemplate e where e.id= 1")
	String getContent ();
	
	@Query(value = "select e.mailSubject from EmailTemplate e where e.id= 1")
	String getSubject ();
	
	@Query(value = "select e.templateName from EmailTemplate e where e.id= 1")
	String getType ();
}
