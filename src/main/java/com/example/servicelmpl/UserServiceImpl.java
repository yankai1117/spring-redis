package com.example.servicelmpl;

import com.example.dao.UserDao;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServicelmpl
 * @Description TODO
 * @Author yankai
 * @Date 2019/9/2914:46
 * @Version 1.0.0
 */

import com.example.entity.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User readByLoginName(String name) {
        return userDao.readByLoginName(name);
    }

}