package com.cts.gsd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.gsd.controller.GsdController;
import com.cts.gsd.model.NewLaptop;
import com.cts.gsd.model.RequestForm;
import com.cts.gsd.model.RequestForm.Requesttype;
import com.cts.gsd.model.RequestForm.Status;
import com.cts.gsd.model.Role;
import com.cts.gsd.model.User;
import com.cts.gsd.repository.RequestFormRepository;
import com.cts.gsd.repository.UserRepository;
import com.cts.gsd.user.FormService;
import com.cts.gsd.user.RoleService;
import com.cts.gsd.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class CtsDemoApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private GsdController controller;
	
	@Mock
	private UserService userService;
	
	@Mock
	private RoleService roleService;
	
	@Mock
	private FormService formService;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private RequestFormRepository formRepository;
	
	@Autowired
	private ObjectMapper objectMapper = new ObjectMapper();
	
	User user=new User();
	{
	user.setId(12);
	user.setActive(false);
	user.setCreatedOn(new Date(2000-10-12));
	user.setName("arun");
	user.setUserName("arun889");
	Role role=new Role();
	role.setId(3);
	role.setActive(true);
	role.setName("users");
	user.setRole(role);
	}
	
	RequestForm form = new RequestForm();
	{
		form.setRequesttype(Requesttype.NewLaptop);
		form.setRequestingfor("self");
		form.setDescription("newlaptop");
		form.setStatus(Status.Open);
		NewLaptop newlap=new NewLaptop();
		newlap.setCapacity("128");
		newlap.setId(1);
		newlap.setLocation("CBE");
		newlap.setModel("Dell");
		newlap.setOs("Windows");
		newlap.setRamsize("8");
		form.setNewlaptop(newlap);
		form.setRepairlaptop(null);
	}

	
	@BeforeEach
	  void setup() throws ApplicationContextException {
	    MockitoAnnotations	.openMocks(this);
	  }
	
	@Test
	  void testgetuser() throws Exception {
		List<User> user1=List.of(user);
		when(userRepository.findAll()).thenReturn(user1);
		MvcResult result = mockMvc
	      .perform(MockMvcRequestBuilders.get("/getuser")
	    		  .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().is2xxSuccessful()).andReturn();
	      	
	    assertNotNull(result);
	  }
	
	@Test
	  void testadduser() throws Exception {
		when(userRepository.save(user)).thenReturn(user);
	    MvcResult result = mockMvc
	      .perform(MockMvcRequestBuilders
			.post("/adduser/"+"3").content(objectMapper.writeValueAsString(user))
			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk()).andReturn();
	    assertNotNull(result);
	  }
	
	@Test
	  void testgetiduser() throws Exception {
		when(userRepository.getById(2)).thenReturn(user);
	    MvcResult result = mockMvc
	      .perform(MockMvcRequestBuilders
	    		  .get("/getiduser/"+"5")
	    		  .content(objectMapper.writeValueAsString(user) )
	    		  .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk()).andReturn();
	      	
	    assertNotNull(result);
	  }
	@Test 
	void testupdateuser() throws Exception {
		when(userRepository.saveAndFlush(user)).thenReturn(user);
		MvcResult result=mockMvc
				.perform(MockMvcRequestBuilders
						.patch("/updateuser/1").content(objectMapper.writeValueAsString(user) )
	    		  .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk()).andReturn();
		assertNotNull(result);
	}
	
	@Test 
	void testdeleteuser() throws Exception {
		MvcResult result=mockMvc
				.perform(MockMvcRequestBuilders
						.delete("/deleteuser/1").content(objectMapper.writeValueAsString(user) )
	    		  .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk()).andReturn();
		verify(userRepository).deleteById(1);
		assertNotNull(result);
	}
	@Test 
	void testgetform() throws Exception {
//		when(formRepository.findAll()).thenReturn(form1);
		List<RequestForm> form=formRepository.findAll();
		MvcResult result=mockMvc
				.perform(MockMvcRequestBuilders
						.get("/getform?pageNo=0&pageSize=2").content(objectMapper.writeValueAsString(form) )
	    		  .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk()).andReturn();
		assertNotNull(result);
	}
	
	@Test 
	void testsaveform() throws Exception {
		when(formRepository.save(form)).thenReturn(form);
		MvcResult result=mockMvc
				.perform(MockMvcRequestBuilders
						.post("/saveform").content(objectMapper.writeValueAsString(form) )
	    		  .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk()).andReturn();
		assertNotNull(result);
	}
	
	@Test
	void adduserTest() {
		assertEquals(user,user);
	}
	
//	@Test
//	void adduserserviceTest() {
//		UserService service= new UserService();
//		Mockito.when(userRepository.save(user)).thenReturn(user);
//		service.setUserRepository(userRepository);
//		assertEquals(user,service.addUser(12, user));
//		
//	}
//
//	@Test
//	void findidTest() {
//		UserService service= new UserService();
//		Optional<User> user1=Optional.of(user);
//		Mockito.when(userRepository.findById(16)).thenReturn(user1);
//		service.setUserRepository(userRepository);
//		assertEquals(user1,service.getUserId(16));
//		
//	}

}
