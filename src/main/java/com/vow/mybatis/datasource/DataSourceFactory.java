package com.vow.mybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author: vow
 * @date: 2024/2/20 13:45
 * @description:
 */
public interface DataSourceFactory {

    void setProperties(Properties properties);

    DataSource getDataSource();
}
