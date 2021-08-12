package com.spring.data.jpa.tutorial.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.data.jpa.tutorial.entity.Course;
import com.spring.data.jpa.tutorial.entity.Teacher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {

        Course courseDBA = Course.builder()
                .title("DBA")
                .credit(5)
                .build();

        Course courseJava = Course.builder()
                .title("Java")
                .credit(6)
                .build();
        
        List<Course> lCourse  = new ArrayList<>();
        lCourse.add(courseDBA);
        lCourse.add(courseJava);
        
        Teacher teacher =
                Teacher.builder()
                        .firstName("Subhadip")
                        .lastName("Gh")
                        .courses(lCourse)
                        .build();

        teacherRepository.save(teacher);
    }
}