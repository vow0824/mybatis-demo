package com.vow.mybatis.test.dao;

import com.vow.mybatis.test.po.User;

import java.util.List;

public interface IUserDao {

    User queryUserInfoById(Long id);
}
