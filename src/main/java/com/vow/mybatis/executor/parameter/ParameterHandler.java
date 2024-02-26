package com.vow.mybatis.executor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: vow
 * @date: 2024/2/26 14:08
 * @description:
 */
public interface ParameterHandler {

    /**
     * 获取参数
     */
    Object getParameterObject();

    /**
     * 设置参数
     */
    void setParameters(PreparedStatement ps) throws SQLException;
}
