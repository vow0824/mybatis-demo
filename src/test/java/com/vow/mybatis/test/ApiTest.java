package com.vow.mybatis.test;

import com.alibaba.fastjson.JSON;
import com.vow.mybatis.io.Resources;
import com.vow.mybatis.session.SqlSession;
import com.vow.mybatis.session.SqlSessionFactory;
import com.vow.mybatis.session.SqlSessionFactoryBuilder;
import com.vow.mybatis.test.dao.IActivityDao;
import com.vow.mybatis.test.po.Activity;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_queryActivityById() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        // 2. 请求对象
        Activity req = new Activity();
        req.setActivityId(100001L);

        // 3. 第一组：SqlSession
        // 3.1 开启 Session
        SqlSession sqlSession01 = sqlSessionFactory.openSession();
        // 3.2 获取映射器对象
        IActivityDao dao01 = sqlSession01.getMapper(IActivityDao.class);
        logger.info("测试结果01：{}", JSON.toJSONString(dao01.queryActivityById(req)));
        sqlSession01.close();

        // 4. 第一组：SqlSession
        // 4.1 开启 Session
        SqlSession sqlSession02 = sqlSessionFactory.openSession();
        // 4.2 获取映射器对象
        IActivityDao dao02 = sqlSession02.getMapper(IActivityDao.class);
        logger.info("测试结果02：{}", JSON.toJSONString(dao02.queryActivityById(req)));
        sqlSession02.close();
    }

    @Test
    public void test_ClassPathXmlApplicationContext() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring-config.xml");
        IActivityDao dao = beanFactory.getBean("IActivityDao", IActivityDao.class);
        Activity res = dao.queryActivityById(new Activity(100001L));
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }
}
