package com.cts.gsd.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.gsd.model.NewLaptop;
import com.cts.gsd.model.RepairLaptop;
import com.cts.gsd.model.RequestForm;
import com.cts.gsd.model.RequestForm.Status;
import com.cts.gsd.repository.NewLaptopRepository;
import com.cts.gsd.repository.RepairLaptopRepository;
import com.cts.gsd.repository.RequestFormRepository;

@Service
public class FormService {
	
	@Autowired
	private RequestFormRepository requestFormRepository;
	
	@Autowired
	private NewLaptopRepository newLaptopRepository;
	
	@Autowired
	private RepairLaptopRepository repairLaptopRepository;
	
	@Transactional
	public Status updatestatus(@PathVariable int id,@RequestBody Status status){	
		requestFormRepository.setUpdateStatus(id, status);
		return status;
	}
	
	@Transactional
	public void updatecomment(int id,String comments){
		requestFormRepository.setComment(id,comments);
	}
	
	
//	public Pagenation showform(int pageNo,int pageSize,String sortField, String sortDirection) {
//		Pageable pageable=PageRequest.of(pageNo, pageSize,(Sort.Direction.ASC.name()) != null?Sort.by(sortField).ascending() :
//			Sort.by(sortField).descending());
//		Page<RequestForm> page=requestFormRepository.findAll(pageable);
//		Pagenation pagenation=Pagenation.createPagenation(page);
//		return pagenation;
//		}
	
	public Map<String,Object> findPaginated(int pageNo, int pageSize
//			, String sortField, String sortDirection
			) {
		
//		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//			Sort.by(sortField).descending();
		List<RequestForm> forms = new ArrayList<RequestForm>();
		Pageable pageable = PageRequest.of(pageNo , pageSize);
		 Page<RequestForm> formPage;
		formPage=requestFormRepository.findAll(pageable);
		forms=formPage.getContent();
		
		Map<String, Object> response = 
                new HashMap<String, Object>();
		response.put("forms", forms);
		 response.put("currentPage", formPage.getNumber());
	      response.put("totalItems", formPage.getTotalElements());
	      response.put("totalPages", formPage.getTotalPages());
	      response.put("pagesize", formPage.getNumberOfElements());
	    return response;
			
	}
	
	public RequestForm addform(@RequestBody RequestForm requestform) {
		return requestFormRepository.save(requestform);
	}
	
	public List<NewLaptop> shownewlap() {
		return newLaptopRepository.findAll();
	}
	
	public Optional<NewLaptop> shownewid(@PathVariable("id")int id) {
		return newLaptopRepository.findById(id);
	}
	
	public Optional<RepairLaptop> showrepairid(@PathVariable("id")int id) {
		return repairLaptopRepository.findById(id);
	}
	
}
