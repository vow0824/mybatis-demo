package com.vow.mybatis.test;

import com.alibaba.fastjson.JSON;
import com.vow.mybatis.Resources;
import com.vow.mybatis.SqlSession;
import com.vow.mybatis.SqlSessionFactory;
import com.vow.mybatis.SqlSessionFactoryBuilder;
import com.vow.mybatis.test.po.User;
import org.junit.Test;

import java.io.Reader;
import java.util.List;

/**
 * @author: vow
 * @date: 2023/8/30 14:26
 * @description:
 */
public class ApiTest {

    @Test
    public void test_queryUserInfoById() {
        String resource = "mybatis-config-datasource.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

            SqlSession sqlSession = sqlMapper.openSession();
            try {
                User user = sqlSession.selectOne("com.vow.mybatis.test.dao.IUserDao.queryUserInfoById", 1L);
                System.out.println(JSON.toJSONString(user));
            } finally {
                sqlSession.close();
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_queryUserList() {
        String resource = "mybatis-config-datasource.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

            SqlSession session = sqlMapper.openSession();
            try {
                User req = new User();
                req.setUserId("10001");
                List<User> userList = session.selectList("com.vow.mybatis.test.dao.IUserDao.queryUserList", req);
                System.out.println(JSON.toJSONString(userList));
            } finally {
                session.close();
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
