package com.vow.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: vow
 * @date: 2024/2/26 14:21
 * @description:
 */
public class LongTypeHandler extends BaseTypeHandler<Long> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter);
    }
}
