package com.spring.data.jpa.tutorial.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.spring.data.jpa.tutorial.entity.Teacher;
import com.spring.data.jpa.tutorial.repository.TeacherRepository;

public class Teachercache {

	@Autowired
    TeacherRepository teacherRepository;

    public Teacher getTeacher(String firtsName) {
        System.out.println("Retrieving Teacher from Database for Firstname: " + firtsName);
        return teacherRepository.findByFirstName(firtsName);
    }
    
    @Cacheable(value = "teachersCache", key = "#name")
    public List<Teacher> getAllTeacher() {
        System.out.println("Retrieving All Teacher from Database");
        return teacherRepository.findAll();
    }
}
