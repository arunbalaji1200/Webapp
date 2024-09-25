package com.cts.gsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.gsd.model.EmailScheduler;

public interface EmailSchedulerRepository extends JpaRepository<EmailScheduler, String>{

}
