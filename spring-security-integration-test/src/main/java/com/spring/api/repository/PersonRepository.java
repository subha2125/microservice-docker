package com.spring.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.api.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

}
