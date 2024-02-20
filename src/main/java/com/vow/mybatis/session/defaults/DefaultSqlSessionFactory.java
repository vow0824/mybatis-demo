package com.vow.mybatis.session.defaults;

import com.vow.mybatis.binding.MapperRegistry;
import com.vow.mybatis.session.Configuration;
import com.vow.mybatis.session.SqlSession;
import com.vow.mybatis.session.SqlSessionFactory;

/**
 * @author: vow
 * @date: 2024/2/1 15:20
 * @description:
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
