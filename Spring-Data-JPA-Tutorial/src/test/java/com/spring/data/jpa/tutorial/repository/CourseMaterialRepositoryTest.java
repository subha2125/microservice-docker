package com.spring.data.jpa.tutorial.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.data.jpa.tutorial.entity.Course;
import com.spring.data.jpa.tutorial.entity.CourseMaterial;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void SaveCourseMaterial() {
        Course course =
                Course.builder()
                .title(".net")
                .credit(6)
                .build();

        CourseMaterial courseMaterial =
                CourseMaterial.builder()
                .url("www.dailycodebuffer.com")
                .course(course)
                .build();

        repository.save(courseMaterial);
    }
    
    @Test
    public void printAllCourseMaterials() {
        List<CourseMaterial> courseMaterials = 
                repository.findAll();

        System.out.println("courseMaterials = " + courseMaterials);
    }
}