package com.sync.service;

import com.sync.entity.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author yankai
 * @Date 2019/10/221:42
 * @Version 1.0.0
 */
public interface UserService {
    User getUser(Long userId);
    User updateUser(User user);
    User getUserByName(String name);
    int insertUser(User user);
    User delete (Long userId);
}//假设有需求是由name查询user的，一般我们是先由name->id,再由id->user，这样会减少redis缓存的冗余信息