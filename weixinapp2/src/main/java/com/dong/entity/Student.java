package com.dong.entity;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable{
    private Integer id;
    private String name;
    private Integer age;
    private Integer schoolId;
    private List<Tag> tagList;

    private School school;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", schoolId=" + schoolId +
                '}';
    }
}
