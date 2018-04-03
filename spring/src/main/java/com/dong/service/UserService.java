package com.dong.service;

import com.dong.dao.UserDao;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public void save(){
        userDao.save();
    }
}
