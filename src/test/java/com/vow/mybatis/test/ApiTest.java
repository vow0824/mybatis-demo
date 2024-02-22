package com.vow.mybatis.test;

import com.alibaba.fastjson.JSON;
import com.vow.mybatis.binding.MapperRegistry;
import com.vow.mybatis.builder.xml.XMLConfigBuilder;
import com.vow.mybatis.datasource.pooled.PooledDataSource;
import com.vow.mybatis.io.Resources;
import com.vow.mybatis.session.Configuration;
import com.vow.mybatis.session.SqlSession;
import com.vow.mybatis.session.SqlSessionFactory;
import com.vow.mybatis.session.SqlSessionFactoryBuilder;
import com.vow.mybatis.session.defaults.DefaultSqlSession;
import com.vow.mybatis.session.defaults.DefaultSqlSessionFactory;
import com.vow.mybatis.test.dao.IUserDao;
import com.vow.mybatis.test.po.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: vow
 * @date: 2024/2/1 15:31
 * @description:
 */
public class ApiTest {

//    <property name="url" value="jdbc:mysql://127.0.0.1:3306/mybatis?useUnicode=true&amp;characterEncoding=utf8"/>
//    <property name="username" value="root"/>
//    <property name="password" value="123123"/>

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        User user = userDao.queryUserInfoById(1L);
        logger.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_pooled() throws SQLException, InterruptedException {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.cj.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/mybatis?useUnicode=true&characterEncoding=utf8");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("123123");
        // 持续获得链接
        while (true) {
            Connection connection = pooledDataSource.getConnection();
            System.out.println(connection);
            Thread.sleep(1000);
            // 注释掉/不注释掉测试
            connection.close();
        }
    }
}
