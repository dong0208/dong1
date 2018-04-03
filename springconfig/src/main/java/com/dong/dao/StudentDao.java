package com.dong.dao;

import com.dong.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public void save(Student student){
        String sql = "insert into student(name,tel) value(?,?)";
        jdbcTemplate.update(sql,student.getName(),student.getTel());
    }
    public Student findById(int id){
        String sql = "select * from student where id = ? ";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class),id);
    }
    public List<Student> findAll(){
        String sql = "select * from student";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Student>(Student.class));
    }
    public void update(Student student){
        String sql = "update student set name = :name,tel = :tel where id = :id";
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("name",student.getName());
        params.put("tel",student.getTel());
        params.put("id",student.getId());
        namedParameterJdbcTemplate.update(sql,params);
    }
    public void batchSave(List<Student> students){
        String sql ="insert into student(name,tel) values(?,?)";
        List<Object[]> params = new ArrayList<Object[]>();
        for(Student student : students){
            Object[] objects = new Object[2];
            objects[0] = student.getName();
            objects[1] = student.getTel();
            params.add(objects);
        }
        jdbcTemplate.batchUpdate(sql,params);
    }
    public Long countAll(){
        String sql = "select count(*)from student";
        return jdbcTemplate.queryForObject(sql,new SingleColumnRowMapper<Long>(Long.class));
    }
}
