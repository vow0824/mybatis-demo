package com.vow.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: vow
 * @date: 2024/2/20 13:53
 * @description:
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;
}
