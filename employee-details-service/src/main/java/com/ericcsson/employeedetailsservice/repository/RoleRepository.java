package com.ericcsson.employeedetailsservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ericcsson.employeedetailsservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	public List<Role> findByUserUsername(String userName);
	
	@Query(value = "select u.roles from User u where u.username=:userName")
	public List<Role> findAllRolesByUser(@Param("userName") String username);
	
	@Transactional
	@Modifying
	@Query(value="update Role r set r.role=:role where r.user_id=:userId",nativeQuery = true)
	void updateRoleByUserName(@Param("role")String role, @Param("userId") Integer userId);
	
}
