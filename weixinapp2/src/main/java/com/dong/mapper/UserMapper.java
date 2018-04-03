package com.dong.mapper;

import com.dong.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;



public interface UserMapper {

     /**
      * 保存user对象
      * @date 2018-3-22
      * @param user 对象
      * @return void
      */int save(User user);
    User findById(int id);
    List<User> findAll();
    List<User> page(Map<String, Integer> map);
    List<User> page2(int start, int size);
    List<User> page3(@Param("start") int start, @Param("size") int size);
    int update(User user);
    int delById(int id);


        }