package com.vow.mybatis.plugin;

/**
 * @author: vow
 * @date: 2024/3/1 10:44
 * @description:
 */
public @interface Signature {

    /**
     * 被拦截类
     */
    Class<?> type();

    /**
     * 被拦截类的方法
     */
    String method();

    /**
     * 被拦截类的方法的参数
     */
    Class<?>[] args();
}
