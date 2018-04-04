package com.dong.service;

import com.dong.dao.StudentDao;
import com.dong.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;
    @Transactional(rollbackFor = Exception.class,isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRED)
    public void saveStudent(List<Student> studentList){
        for (Student student : studentList){
            studentDao.save(student);
        }
    }
}
