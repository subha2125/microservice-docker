package com.ericcsson.employeedetailsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ericcsson.employeedetailsservice.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
	
	@Query(value = "select user_id from Role r where r.role=:role", nativeQuery = true)
	public List<Integer> findAllUserByRole(@Param("role") String role);

}
