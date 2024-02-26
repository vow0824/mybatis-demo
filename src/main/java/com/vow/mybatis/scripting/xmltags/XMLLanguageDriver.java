package com.vow.mybatis.scripting.xmltags;

import com.vow.mybatis.mapping.SqlSource;
import com.vow.mybatis.scripting.LanguageDriver;
import com.vow.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * @author 小傅哥，微信：fustack
 * @description XML语言驱动器
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

}