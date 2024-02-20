package com.vow.mybatis.session;

import java.util.List;

/**
 * @author: vow
 * @date: 2024/2/1 14:44
 * @description:
 */
public interface SqlSession {

    <T> T selectOne(String statement);

    <T> T selectOne(String statement, Object parameter);

    <T> T getMapper(Class<T> type);
}
