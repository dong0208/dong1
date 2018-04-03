package com.dong.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class SqlSessionFactoryUtil {

    private  static SqlSessionFactory sqlSessionFactory;

    static {
        try{
            //操作数据库
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            //创建sqlSessionFactory
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getSqlSessionFactory(){

        return sqlSessionFactory;
    }
    public static SqlSession getSqlSession(boolean autoCommit){
        return sqlSessionFactory.openSession(autoCommit);
    }

}
