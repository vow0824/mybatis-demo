package com.vow.mybatis.transaction.jdbc;

import com.vow.mybatis.session.TransactionIsolationLevel;
import com.vow.mybatis.transaction.Transaction;
import com.vow.mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author: vow
 * @date: 2024/2/20 14:05
 * @description:
 */
public class JdbcTransactionFactory implements TransactionFactory {
    @Override
    public Transaction newTransaction(Connection connection) {
        return new JdbcTransaction(connection);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
