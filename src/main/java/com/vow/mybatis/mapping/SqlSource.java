package com.vow.mybatis.mapping;

/**
 * @author: vow
 * @date: 2024/2/26 8:48
 * @description:
 */
public interface SqlSource {

    BoundSql getBoundSql(Object parameterObject);
}
