package com.vow.mybatis;

/**
 * @author: vow
 * @date: 2023/8/30 16:15
 * @description:
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
