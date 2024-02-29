package com.vow.mybatis.test.dao;


import com.vow.mybatis.test.po.Activity;

public interface IActivityDao {

    Activity queryActivityById(Long activityId);

}
