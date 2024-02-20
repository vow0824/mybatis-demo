package com.vow.mybatis.session.defaults;

import com.vow.mybatis.binding.MapperRegistry;
import com.vow.mybatis.session.Configuration;
import com.vow.mybatis.session.SqlSession;

/**
 * @author: vow
 * @date: 2024/2/1 14:45
 * @description:
 */
public class DefaultSqlSession implements SqlSession {


    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你的操作被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你的操作被代理了！" + "方法：" + statement + " 入参：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
