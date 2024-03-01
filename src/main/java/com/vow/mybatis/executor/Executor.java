package com.vow.mybatis.executor;

import com.vow.mybatis.mapping.BoundSql;
import com.vow.mybatis.mapping.MappedStatement;
import com.vow.mybatis.session.ResultHandler;
import com.vow.mybatis.session.RowBounds;
import com.vow.mybatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: vow
 * @date: 2024/2/22 10:47
 * @description:
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    int update(MappedStatement ms, Object parameter) throws SQLException;

    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException;

    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException;

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);
}
