package com.dong.entity;

public class Student {
    private int id;
    private String name;
    private String tel;

    public Student(){

    }

    public Student(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }

    public Student(int id, String name, String tel) {

        this.id = id;
        this.name = name;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
