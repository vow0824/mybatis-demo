package com.vow.mybatis.session;

/**
 * @author: vow
 * @date: 2024/2/26 15:11
 * @description:
 */
public interface ResultContext {

    /**
     * 获取结果
     */
    Object getResultObject();

    /**
     * 获取记录数
     */
    int getResultCount();
}
