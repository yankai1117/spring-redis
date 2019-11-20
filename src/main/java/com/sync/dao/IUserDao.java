package com.sync.dao;

import com.sync.entity.User;

/**
 * @ClassName IUserDao
 * @Description 系统基本接口
 * @Author yankai
 * @Date 2019/10/39:14
 * @Version 1.0.0
 */
public interface IUserDao {

    User selectByPrimaryKey(Long userId);

    Long getIdByName(String name);

    int updateByPrimaryKeySelective(User user);
}
