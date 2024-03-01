package com.vow.mybatis.test;

import com.alibaba.fastjson.JSON;
import com.vow.mybatis.builder.xml.XMLConfigBuilder;
import com.vow.mybatis.executor.Executor;
import com.vow.mybatis.io.Resources;
import com.vow.mybatis.mapping.Environment;
import com.vow.mybatis.session.*;
import com.vow.mybatis.session.defaults.DefaultSqlSession;
import com.vow.mybatis.test.dao.IActivityDao;
import com.vow.mybatis.test.dao.IUserDao;
import com.vow.mybatis.test.po.Activity;
import com.vow.mybatis.test.po.User;
import com.vow.mybatis.transaction.Transaction;
import com.vow.mybatis.transaction.TransactionFactory;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

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

    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test_insertUserInfo() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证
        User user = new User();
        user.setUserId("10002");
        user.setUserName("小白");
        user.setUserHead("1_05");
        userDao.insertUserInfo(user);
        logger.info("测试结果：{}", "Insert OK");

        // 3. 提交事务
        sqlSession.commit();
    }

    @Test
    public void test_deleteUserInfoByUserId() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证
        int count = userDao.deleteUserInfoByUserId("10002");
        logger.info("测试结果：{}", count == 1);

        // 3. 提交事务
        sqlSession.commit();
    }


    @Test
    public void test_updateUserInfo() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证
        int count = userDao.updateUserInfo(new User(1L, "10001", "vow"));
        logger.info("测试结果：{}", count);

        // 3. 提交事务
        sqlSession.commit();
    }

    @Test
    public void test_queryUserInfoById() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证：基本参数
        User user = userDao.queryUserInfoById(1L);
        logger.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_queryUserInfo() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证：对象参数
        User user = userDao.queryUserInfo(new User(1L, "10001"));
        logger.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_queryUserInfoList() {
        // 1. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 2. 测试验证：对象参数
        List<User> users = userDao.queryUserInfoList();
        logger.info("测试结果：{}", JSON.toJSONString(users));
    }

    @Test
    public void test_queryActivityById() {
        // 1. 获取映射器对象
        IActivityDao dao = sqlSession.getMapper(IActivityDao.class);
        // 2. 测试验证
        Activity res = dao.queryActivityById(100001L);
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }

    @Test
    public void test_insert() {
        // 1. 获取映射器对象
        IActivityDao dao = sqlSession.getMapper(IActivityDao.class);

        Activity activity = new Activity();
        activity.setActivityId(10005L);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("测试数据插入");
        activity.setCreator("xiaofuge");

        // 2. 测试验证
        Integer res = dao.insert(activity);
        sqlSession.commit();

        logger.info("测试结果：count：{} idx：{}", res, JSON.toJSONString(activity.getId()));
    }

    @Test
    public void test_insert_select() throws IOException {
        // 解析 XML
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        Configuration configuration = xmlConfigBuilder.parse();

        // 获取 DefaultSqlSession
        final Environment environment = configuration.getEnvironment();
        TransactionFactory transactionFactory = environment.getTransactionFactory();
        Transaction tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);

        // 创建执行器
        final Executor executor = configuration.newExecutor(tx);
        SqlSession sqlSession = new DefaultSqlSession(configuration, executor);

        // 执行查询：默认是一个集合参数
        Activity activity = new Activity();
        activity.setActivityId(10008L);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("测试数据插入");
        activity.setCreator("vow");
        int res = sqlSession.insert("com.vow.mybatis.test.dao.IActivityDao.insert", activity);

        Object obj = sqlSession.selectOne("com.vow.mybatis.test.dao.IActivityDao.insert!selectKey");
        logger.info("测试结果：count：{} idx：{}", res, JSON.toJSONString(obj));

        sqlSession.commit();
    }
}
