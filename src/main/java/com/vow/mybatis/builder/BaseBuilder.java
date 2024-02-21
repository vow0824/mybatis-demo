package com.vow.mybatis.builder;

import com.vow.mybatis.session.Configuration;
import com.vow.mybatis.type.TypeAliasRegistry;

/**
 * @author: vow
 * @date: 2024/2/19 11:20
 * @description:
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    protected final TypeAliasRegistry typeAliasRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
