package com.vow.mybatis.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: vow
 * @date: 2024/2/26 14:22
 * @description:
 */
public class StringTypeHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter);
    }
}
