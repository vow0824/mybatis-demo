package com.vow.mybatis.scripting;

import com.vow.mybatis.mapping.SqlSource;
import com.vow.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * @author: vow
 * @date: 2024/2/26 8:48
 * @description:
 */
public interface LanguageDriver {

    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);
}
