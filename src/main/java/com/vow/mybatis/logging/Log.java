package com.vow.mybatis.logging;

/**
 * @author: vow
 * @date: 2024/3/1 15:20
 * @description:
 */
public interface Log {

    boolean isDebugEnabled();

    boolean isTraceEnabled();

    void error(String s, Throwable e);

    void error(String s);

    void debug(String s);

    void trace(String s);

    void warn(String s);
}
