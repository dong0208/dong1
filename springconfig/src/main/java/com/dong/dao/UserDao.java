package com.dong.dao;

import org.springframework.stereotype.Repository;

import javax.inject.Named;

@Repository
public class UserDao {
    public void save(){
        System.out.println("save....");
    }
}
