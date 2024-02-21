package com.vow.mybatis.datasource.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.vow.mybatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author: vow
 * @date: 2024/2/20 13:47
 * @description:
 */
public class DruidDataSourceFactory implements DataSourceFactory {

    private Properties props;

    @Override
    public void setProperties(Properties props) {
        this.props = props;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(props.getProperty("driver"));
        druidDataSource.setUrl(props.getProperty("url"));
        druidDataSource.setUsername(props.getProperty("username"));
        druidDataSource.setPassword(props.getProperty("password"));
        return druidDataSource;
    }
}
