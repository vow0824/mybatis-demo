package com.vow.mybatis.builder;

import com.vow.mybatis.session.Configuration;

/**
 * @author: vow
 * @date: 2024/2/19 11:20
 * @description:
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
