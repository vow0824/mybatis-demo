package com.vow.mybatis.binding;

import com.vow.mybatis.mapping.MappedStatement;
import com.vow.mybatis.mapping.SqlCommandType;
import com.vow.mybatis.session.Configuration;

import java.lang.reflect.Method;

/**
 * @author: vow
 * @date: 2024/2/19 14:14
 * @description:
 */
public class MapperMethod {

    private SqlCommand command;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration configuration) {
        this.command = new SqlCommand(configuration, mapperInterface, method);
    }

    public static class SqlCommand {

        private final String name;

        private final SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappedStatement ms = configuration.getMappedStatement(statementName);
            name = ms.getId();
            type = ms.getSqlCommandType();
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }
    }
}
