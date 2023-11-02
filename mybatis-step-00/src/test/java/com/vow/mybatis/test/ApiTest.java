package com.vow.mybatis.test;

import com.alibaba.fastjson.JSON;
import com.vow.mybatis.test.dao.IActivityDao;
import com.vow.mybatis.test.dao.IUserDao;
import com.vow.mybatis.test.po.Activity;
import com.vow.mybatis.test.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author: vow
 * @date: 2023/8/30 14:26
 * @description:
 */
public class ApiTest {

    private final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1、从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        // 2、请求对象
        Activity req = new Activity();
        req.setActivityId(100001L);

        // 3、第一组：SqlSession
        // 3.1 开启Session
        SqlSession sqlSession01 = sqlSessionFactory.openSession();
        // 3.2 获取映射器对象
        IActivityDao dao01 = sqlSession01.getMapper(IActivityDao.class);
        logger.info("测试结果01：{}", JSON.toJSONString(dao01.queryActivityById(req)));
        sqlSession01.close();

        // 4、第二组SqlSession
        // 3.1 开启Session
        SqlSession sqlSession02 = sqlSessionFactory.openSession();
        // 3.2 获取映射器对象
        IActivityDao dao02 = sqlSession02.getMapper(IActivityDao.class);
        logger.info("测试结果02：{}", JSON.toJSONString(dao02.queryActivityById(req)));
        sqlSession02.close();
    }


    @Test
    public void test_SqlSessionFactory_Annotation() throws IOException {
        // 1、从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource-annotation.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> users = userDao.queryUserInfoList();
        logger.info("查询结果:{}", JSON.toJSONString(users));

        sqlSession.close();
    }
}
