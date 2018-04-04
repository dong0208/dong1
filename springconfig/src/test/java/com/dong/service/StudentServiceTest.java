package com.dong.service;

import com.dong.BaseTestCase;
import com.dong.entity.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class StudentServiceTest extends BaseTestCase{
    @Autowired
    private StudentService studentService;
    @Test
    public void save(){
        List<Student> studentList = Arrays.asList(
                new Student("小名","189"),
                new Student("刘丽","157")
        );
        studentService.saveStudent(studentList);
    }

}
