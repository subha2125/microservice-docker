package com.ericcsson.employeeinfoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ericcsson.employeeinfoservice.model.EmployeeInfo;

public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Long>{

}
