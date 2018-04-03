package com.dong.dao;

import com.dong.BaseTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class DaoTest extends BaseTestCase{

    @Autowired
    private UserDao userDao;
    @Test
    public void save(){
        userDao.save();
    }
}
