package com.vow.mybatis;

import java.sql.Connection;
import java.util.Map;

/**
 * @author: vow
 * @date: 2023/8/30 16:16
 * @description:
 */
public class Configuration {

    protected Connection connection;

    protected Map<String, String> datasource;

    protected Map<String, XNode> mapperElement;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setDatasource(Map<String, String> datasource) {
        this.datasource = datasource;
    }

    public void setMapperElement(Map<String, XNode> mapperElement) {
        this.mapperElement = mapperElement;
    }
}
