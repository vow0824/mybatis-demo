package com.vow.mybatis.test;

import com.vow.mybatis.binding.MapperRegistry;
import com.vow.mybatis.io.Resources;
import com.vow.mybatis.session.SqlSession;
import com.vow.mybatis.session.SqlSessionFactory;
import com.vow.mybatis.session.SqlSessionFactoryBuilder;
import com.vow.mybatis.session.defaults.DefaultSqlSessionFactory;
import com.vow.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * @author: vow
 * @date: 2024/2/1 15:31
 * @description:
 */
public class ApiTest {

//    <property name="url" value="jdbc:mysql://127.0.0.1:3306/mybatis?useUnicode=true&amp;characterEncoding=utf8"/>
//    <property name="username" value="root"/>
//    <property name="password" value="123123"/>

//    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        String res = userDao.queryUserInfoById("10001");
//        logger.info("测试结果：{}", res);
        System.out.println(res);
    }
}
