package com.cts.gsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.gsd.model.FileDb;

@Repository
public interface FileDbRepository extends JpaRepository<FileDb, Long>{

	FileDb findByName(String name);
	

}
