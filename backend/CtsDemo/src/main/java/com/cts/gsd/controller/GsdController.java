package com.cts.gsd.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cts.gsd.exception.UserException;
import com.cts.gsd.exception.AddUserRoleException;
import com.cts.gsd.exception.RoleException;
import com.cts.gsd.model.FileDb;
import com.cts.gsd.model.NewLaptop;
import com.cts.gsd.model.RepairLaptop;
import com.cts.gsd.model.RequestForm;
import com.cts.gsd.model.RequestForm.Status;
import com.cts.gsd.model.Role;
import com.cts.gsd.model.User;
import com.cts.gsd.user.EmailService;
import com.cts.gsd.user.FileDbService;
import com.cts.gsd.user.FormService;
import com.cts.gsd.user.RoleService;
import com.cts.gsd.user.UserService;

import jakarta.mail.MessagingException;

// TODO: Auto-generated Javadoc
/**
 * The Class GsdController.
 */
@RestController()
@RequestMapping("/service/")
@CrossOrigin(origins="http://localhost:3000")
public class GsdController {
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The role service. */
	@Autowired
	private RoleService roleService;
	
	/** The form service. */
	@Autowired
	private FormService formService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private FileDbService filedbService;
	
	/**
	 * Adduser.
	 *
	 * @param id the id
	 * @param user the user
	 * @return the user
	 */
//	@PostMapping(path="adduser/{id}", consumes= {"application/json"})
//	public User adduser(@PathVariable("id") int id,@RequestBody User user) {
//		userService.addUser(id, user);	
//		return user;
//	}
	
	@PostMapping("adduser/{id}")
	public ResponseEntity<?> username(@PathVariable("id") int id,@RequestBody User user){
		try {
			userService.addUser(id, user);
			return ResponseEntity.ok(("User registered successfully."));
		}catch(DataIntegrityViolationException e) {
			throw new AddUserRoleException();
		}
	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	@GetMapping("getuser")
	public List<User> getuser() {
		return userService.getUser();
	}
	
	/**
	 * Gets the userid.
	 *
	 * @param id the id
	 * @return the userid
	 */
	@GetMapping("getiduser/{id}")
	public ResponseEntity<User> getuserid(@PathVariable int id) {
		User user=userService.getUserId(id);
		if(user==null)
			throw new UserException();
		return new ResponseEntity<User>(user, HttpStatus.OK); 
	}
	
	/**
	 * Updateuser.
	 *
	 * @param id the id
	 * @param user the user
	 * @return the user
	 */
	@PatchMapping("updateuser/{id}")
	public User updateuser(@PathVariable int id,@RequestBody User user) {
		return userService.updateUser(id, user);
	}
	
	/**
	 * Deleteuser.
	 *
	 * @param id the id
	 */
	@DeleteMapping("deleteuser/{id}")
	public void deleteuser(@PathVariable("id") int id){
		if(userService.getUserId(id)==null)	
			throw new UserException();
		userService.deleteUser(id);
	}
	
	/**
	 * Addrole.
	 *
	 * @param id the id
	 * @param role the role
	 * @return the role
	 */
	@PostMapping("addrole/{id}")
	public ResponseEntity<String> addrole(@PathVariable("id")int id,@RequestBody Role role){
		try {
			roleService.addRole(id,role);		
			return ResponseEntity.ok("Role created successfully!");
		}catch(Exception e) {
			throw new AddUserRoleException();
		}
	}
	
	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	@GetMapping("getrole")
	public List<Role> getrole() {
		return roleService.getRole();
	}
	
	/**
	 * Gets the roleid.
	 *
	 * @param id the id
	 * @return the roleid
	 */
	@GetMapping("getidrole/{id}")
	public ResponseEntity<Role> getroleid(@PathVariable int id) {
		Role role=roleService.getRoleId(id);
		if(role==null)
			throw new RoleException();
		return new ResponseEntity<Role>(role,HttpStatus.OK);
	}
	
	/**
	 * Updaterole.
	 *
	 * @param id the id
	 * @param role the role
	 * @return the role
	 */
	@PatchMapping("updaterole/{id}")
	public Role updaterole(@PathVariable("id")int id,@RequestBody Role role) {
		return roleService.updateRole(id, role);
	}
	
	/**
	 * Deleterole.
	 *
	 * @param id the id
	 */
	@DeleteMapping("deleterole/{id}")
	public void deleterole(@PathVariable("id") int id) {
		if(roleService.getRoleId(id)==null)
			throw new RoleException();
		roleService.deleteRole(id);
	}
	
/**
 * Find paginated.
 *
 * @param pageNo the page no
 * @param pageSize the page size
 * @return the map
 */
//	@CrossOrigin(origins="http://localhost:3000/viewform")
	@GetMapping("getform")
	public Map<String, Object> findPaginated(
			@RequestParam(value="pageNo",required = false) int pageNo,
			@RequestParam(value="pageSize",required = false) int pageSize) {
		return formService.findPaginated(pageNo, pageSize);
	}
	
	/**
	 * Addform.
	 *
	 * @param requestform the requestform
	 * @return the request form
	 */
	@PostMapping("saveform")
	public RequestForm addform(@RequestBody RequestForm requestform) {
		return formService.addform(requestform);
	}
	
	/**
	 * Shownewlap.
	 *
	 * @return the list
	 */
	@GetMapping("getnewlap")
	public List<NewLaptop> shownewlap() {
		return formService.shownewlap();
	}
	
	/**
	 * Shownewid.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@GetMapping("getnewid/{id}")
	public Optional<NewLaptop> shownewid(@PathVariable("id")int id){
		return formService.shownewid(id);
	}
	
	/**
	 * Showrepairid.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@GetMapping("getrepairid/{id}")
	public Optional<RepairLaptop> showrepairid(@PathVariable("id")int id) {
		return formService.showrepairid(id);
	}
	
/**
 * Updatestatus.
 *
 * @param id the id
 * @param status the status
 * @return the status
 */
//	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping(value="update/status/{id}")
	public Status updatestatus(@PathVariable("id") int id,@RequestBody Status status) {
		 return formService.updatestatus(id,status);
	}
	
	/**
	 * Updatecomment.
	 *
	 * @param id the id
	 * @param comments the comments
	 */
	@PostMapping("update/comment/{id}")
		public void updatecomment(@PathVariable("id")int id,@RequestBody String comments) {
			formService.updatecomment(id, comments);
		}
//	@GetMapping("sendmail")
//	public void sendMail() throws MessagingException {
//		emailService.sendMail();
//	}
	
	@GetMapping("sendmail")
	public void sendMail()throws UserException {
		emailService.sendMail();
	}
	
	@GetMapping("sendmailattachment/{id}")
		public ResponseEntity<String> sendmailWithAttachment(@RequestParam int id) throws MessagingException {
		try {
		emailService.sendMailWithAttachment(id);
		return ResponseEntity.ok("mail sent with attachment successfully");
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to send mail");
		}
	}
	
	@GetMapping("singlemailscheduler")
	public ResponseEntity<String> singlemailScheduler(){
		try {
			emailService.singleMailScheduler();
			return ResponseEntity.ok("mail sent successfully");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Send Mail");
		}
	}
	
	@GetMapping("multithread")
	public ResponseEntity<String> MultiThread(){
		try {
			emailService.multiThread();
			return ResponseEntity.ok("mail sent successfully");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Send Mail");
		}
	}
	
	@PostMapping("uploadfile")
	public ResponseEntity<String> uploadfile(@RequestParam("file") MultipartFile file) throws IOException {
		filedbService.uploadFile(file);
		return ResponseEntity.ok("File uploaded sucessfully");
	}
	
	@GetMapping("downloadfile/{id}")
	public ResponseEntity<ByteArrayResource> getfile(@PathVariable Long id){
		 FileDb filedb = filedbService.getFile(id);
		return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(filedb.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filedb.getName() + "\"")
                .body(new ByteArrayResource(filedb.getData()));	
	}
	@GetMapping("testexception")
		public ResponseEntity<Object> test(){
			try {
				throw new UserException();
			}catch(UserException ce) {
				throw ce;
			}
		}
	
}
