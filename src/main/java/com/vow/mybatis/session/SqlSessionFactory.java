package com.vow.mybatis.session;

import com.vow.mybatis.session.SqlSession;

/**
 * @author: vow
 * @date: 2024/2/1 15:19
 * @description:
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
