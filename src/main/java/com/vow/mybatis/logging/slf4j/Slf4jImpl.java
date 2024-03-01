package com.vow.mybatis.logging.slf4j;

import com.vow.mybatis.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: vow
 * @date: 2024/3/1 15:21
 * @description:
 */
public class Slf4jImpl implements Log {

    public Slf4jImpl(String clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public void error(String s, Throwable e) {

    }

    @Override
    public void error(String s) {

    }

    @Override
    public void debug(String s) {

    }

    @Override
    public void trace(String s) {

    }

    @Override
    public void warn(String s) {

    }
}
