package com.ericcsson.employeedetailsservice;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ericcsson.employeedetailsservice.controller.EmployeeAddController;
import com.ericcsson.employeedetailsservice.model.User;
import com.ericcsson.employeedetailsservice.repository.RoleRepository;
import com.ericcsson.employeedetailsservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
/*@SpringBootTest*/
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestConfig.class,EmployeeAddController.class})
@TestPropertySource(locations="classpath:test.properties")
public class EmployeeDetailsServiceApplicationTests {
	
	@Autowired
	private EmployeeAddController controller;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private RoleRepository roleRepository;

	@Test
	public void passWordEmcoder() {
		String password = "admin";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println("Password Encoded..."+ hashedPassword);
	}
	
	@Test
	public void testExecuteMockController() throws Exception {
		User user = new User();
		List<User> userList = new ArrayList<>();
		when(userRepository.findAll()).thenReturn(userList);
		/*MvcResult result = mvc.perform(MockMvcRequestBuilders
				.post("/user/getAllUser")
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.with(user("springBoot").password("springBoot")))
				.andReturn();
		result = mvc.perform(MockMvcRequestBuilders.asyncDispatch(result)).andReturn();*/
		
		System.out.println(controller.getAllUser());
	}

	private  String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
