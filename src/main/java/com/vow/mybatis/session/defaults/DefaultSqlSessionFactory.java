package com.vow.mybatis.session.defaults;

import com.vow.mybatis.binding.MapperRegistry;
import com.vow.mybatis.executor.Executor;
import com.vow.mybatis.mapping.Environment;
import com.vow.mybatis.session.Configuration;
import com.vow.mybatis.session.SqlSession;
import com.vow.mybatis.session.SqlSessionFactory;
import com.vow.mybatis.session.TransactionIsolationLevel;
import com.vow.mybatis.transaction.Transaction;
import com.vow.mybatis.transaction.TransactionFactory;

import java.sql.SQLException;

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
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);
            // 创建执行器
            Executor executor = configuration.newExecutor(tx);
            // 创建DefaultSqlSession
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }
    }
}
