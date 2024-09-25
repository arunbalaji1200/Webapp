package com.cts.gsd.user;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cts.gsd.model.FileDb;
import com.cts.gsd.repository.FileDbRepository;

import jakarta.mail.Quota.Resource;

@Service
public class FileDbService {

	@Autowired
	private FileDbRepository fileDbRepository;
	
	public FileDb uploadFile(MultipartFile file) throws IOException {
		try {
//		String file=StringUtils.cleanPath(file.getOriginalFilename());
		FileDb filedb=new FileDb();
		filedb.setName(file.getOriginalFilename());
		filedb.setData(file.getBytes());
		filedb.setType(file.getContentType());
		return fileDbRepository.save(filedb);
		}catch (IOException e){
			throw new RuntimeException("Failed to upload file",e);
		}
	}
	
	public FileDb getFile(Long fileid) {
		
		return fileDbRepository.findById(fileid).orElseThrow();
//			Optional<FileDb> filedb=fileDbRepository.findById(fileid);
//			if(filedb.isPresent()) {
//				ByteArrayResource res=new ByteArrayResource(filedb.get().getData());
//				HttpHeaders headers=new HttpHeaders();
//				headers.add("Content-Diposition","attachment; filename="+filedb.get().getName());
//				return ResponseEntity.ok().headers(headers)
//		                .body(res);
//			}else {
//			return ResponseEntity.notFound().build();
//			}
	}
}
