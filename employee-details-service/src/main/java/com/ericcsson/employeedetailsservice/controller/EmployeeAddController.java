package com.ericcsson.employeedetailsservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ericcsson.employeedetailsservice.model.EmployeeDetails;
import com.ericcsson.employeedetailsservice.model.Role;
import com.ericcsson.employeedetailsservice.model.User;
import com.ericcsson.employeedetailsservice.repository.RoleRepository;
import com.ericcsson.employeedetailsservice.repository.UserRepository;
import com.hazelcast.core.HazelcastInstance;


@RestController
@RequestMapping("/empdetails")
public class EmployeeAddController {
	
	Logger logger = LoggerFactory.getLogger(EmployeeAddController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private HazelcastInstance hazelInst;
	
	@Autowired
	private Map<Integer, EmployeeDetails> empDetailsMap;

	@CrossOrigin("*")
	@PostMapping("/saveUser")
	public @ResponseBody User saveUser(@RequestBody User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	@CrossOrigin("*")
	@PostMapping("/saveRole")
	public @ResponseBody Role saveRole(@RequestBody Role role) {
		return roleRepository.save(role);
	}
	
	@CrossOrigin("*")
	@GetMapping("/getAllUser")
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	@CrossOrigin("*")
	@GetMapping("/getAllSortedUser/{soretdParam}")
	public List<User> getAllSortedUser(@PathVariable String soretdParam) {
		return userRepository.findAll(Sort.by(soretdParam));
	}
	
	@SuppressWarnings("unchecked")
	@CrossOrigin("*")
	@GetMapping("/getAllUserPages/{pageNumber}/{noOfElementPerPages}/{soretdParam}")
	public Page<User> getAllUserPages(@PathVariable int pageNumber,@PathVariable int noOfElementPerPages,
			@PathVariable String soretdParam) {
		return userRepository.findAll(PageRequest.of(pageNumber, noOfElementPerPages,Sort.by(soretdParam)));
	}
	
	@CrossOrigin("*")
	@GetMapping("/getUserByName/{username}")
	public User getUserByName(@PathVariable String username) {
		return userRepository.findByUsername(username);
	}
	
	@CrossOrigin("*")
	@GetMapping("/getAllRoleByUser/{username}")
	public List<Role> getAllRoleByUser(@PathVariable String username) {
		List<Role> roles = new ArrayList<>();
		roleRepository.findByUserUsername(username).forEach(roles::add);
		return roles;
	}
	
	@CrossOrigin("*")
	@GetMapping("/findAllRolesByUser/{username}")
	public List<Role> findAllRolesByUser(@PathVariable String username) {
		List<Role> roles = new ArrayList<>();
		roleRepository.findAllRolesByUser(username).forEach(roles::add);
		return roles;
	}
	
	@CrossOrigin("*")
	@GetMapping("/findAllUserByRole/{role}")
	public List<Integer> findAllUserByRole(@PathVariable String role) {
		List<Integer> user = new ArrayList<>();
		userRepository.findAllUserByRole(role).forEach(user::add);
		return user;
	}
	
	@CrossOrigin("*")
	@GetMapping("/updateRoleByUserName/{role}/{userName}")
	public HttpStatus updateRoleByUserName(@PathVariable String role,@PathVariable String userName) {
		Integer userId = userRepository.findByUsername(userName).getUser_id();
		roleRepository.updateRoleByUserName(role,userId);
		return HttpStatus.OK;
	}
	
	@GetMapping("/hello")
	public String helloUser() {
		return "Hello User";
	}
	
	@RequestMapping("/getEmpByHazelCast/{id}")
	public EmployeeDetails employeeDetails(@PathVariable int id) {
		logger.info("hazelInst :{}",hazelInst);
		logger.info("EmployeeAddController Empdetails Imap..{}",empDetailsMap);
		
		if(Objects.nonNull(hazelInst) && Objects.nonNull(hazelInst.getMap("empDetailsMap"))) {
			logger.info("hazelInst Emp Id :{}",hazelInst.getMap("empDetailsMap").get(id));
		}
		if(Objects.nonNull(empDetailsMap)) {
			logger.info("hazelInst Emp Id :{} And Details:{]", id,empDetailsMap.get(id));
		}
		return Objects.nonNull(empDetailsMap) ? empDetailsMap.get(id) : new EmployeeDetails();
	}
}
