package com.vow.mybatis.session;

import com.vow.mybatis.binding.MapperRegistry;
import com.vow.mybatis.datasource.druid.DruidDataSourceFactory;
import com.vow.mybatis.datasource.pooled.PooledDataSourceFactory;
import com.vow.mybatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.vow.mybatis.executor.Executor;
import com.vow.mybatis.executor.SimpleExecutor;
import com.vow.mybatis.executor.resultset.DefaultResultSetHandler;
import com.vow.mybatis.executor.resultset.ResultSetHandler;
import com.vow.mybatis.executor.statement.PreparedStatementHandler;
import com.vow.mybatis.executor.statement.StatementHandler;
import com.vow.mybatis.mapping.BoundSql;
import com.vow.mybatis.mapping.Environment;
import com.vow.mybatis.mapping.MappedStatement;
import com.vow.mybatis.transaction.Transaction;
import com.vow.mybatis.transaction.jdbc.JdbcTransactionFactory;
import com.vow.mybatis.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: vow
 * @date: 2024/2/19 11:04
 * @description:
 */
public class Configuration {

    protected Environment environment;

    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    public Configuration(){
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
    }

    public void addMappers(String packageName){
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type){
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type){
        return mapperRegistry.getMapper(type, null);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession){
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type){
        return mapperRegistry.hasMapper(type);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public void addMappedStatement(MappedStatement mappedStatement) {
        mappedStatements.put(mappedStatement.getId(), mappedStatement);
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 创建结果集处理器
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, boundSql);
    }

    /**
     * 生产执行器
     */
    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

    /**
     * 创建语句处理器
     */
    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, mappedStatement, parameter, resultHandler, boundSql);
    }
}
