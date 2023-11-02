package com.vow.mybatis;

import java.util.List;

/**
 * @author: vow
 * @date: 2023/8/30 16:13
 * @description:
 */
public interface SqlSession {

    <T> T selectOne(String statement);

    <T> T selectOne(String statement, Object parameter);

    <T> List<T> selectList(String statement);

    <T> List<T> selectList(String statement, Object parameter);

    void close();
}
