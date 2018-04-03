package com.dong.dao;

import com.dong.BaseTestCase;
import com.dong.entity.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class StudentDaoTest extends BaseTestCase {
    @Autowired
    private StudentDao studentDao;
    @Test
    public void save(){
        Student student = new Student("rose","125");
        studentDao.save(student);
    }
    @Test
    public  void findById(){
        Student student = studentDao.findById(1);
        System.out.println(student);
    }
    @Test
    public void findAll(){
        List<Student> studentList = studentDao.findAll();
        for (Student student : studentList){
            System.out.println(student);
        }
    }
    @Test
    public void update(){
        Student student = studentDao.findById(2);
        student.setTel("000");
        studentDao.update(student);
    }
    @Test
    public  void batchSave(){
        List<Student> studentList = Arrays.asList(
                new Student("jetty","167"),
                new Student("tom","199")
        );
        studentDao.batchSave(studentList);
    }
    @Test
    public void countAll(){
        Long count = studentDao.countAll();
        System.out.println("count"+count);
    }
}
