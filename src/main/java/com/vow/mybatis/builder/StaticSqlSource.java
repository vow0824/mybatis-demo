package com.vow.mybatis.builder;

import com.vow.mybatis.mapping.BoundSql;
import com.vow.mybatis.mapping.ParameterMapping;
import com.vow.mybatis.mapping.SqlSource;
import com.vow.mybatis.session.Configuration;

import java.util.List;

/**
 * @author 小傅哥，微信：fustack
 * @description 静态SQL源码
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class StaticSqlSource implements SqlSource {

    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Configuration configuration;

    public StaticSqlSource(Configuration configuration, String sql) {
        this(configuration, sql, null);
    }

    public StaticSqlSource(Configuration configuration, String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.configuration = configuration;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(configuration, sql, parameterMappings, parameterObject);
    }

}
