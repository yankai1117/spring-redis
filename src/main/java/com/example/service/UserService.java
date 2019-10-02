package com.example.service;

import com.example.entity.User;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author yankai
 * @Date 2019/9/2914:29
 * @Version 1.0.0
 */
@Service
public interface UserService{
    User readByLoginName(String name);
}
//实现类<br>//假设有需求是由name查询user的，一般我们是先由name->id,再由id->user，这样会减少redis缓存的冗余信息