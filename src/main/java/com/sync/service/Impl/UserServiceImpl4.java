package com.sync.service.Impl;

/**
 * @ClassName UserServiceImpl4
 * @Description TODO
 * @Author yankai
 * @Date 2019/10/310:37
 * @Version 1.0.0
 */

import com.sync.dao.IUserDao;
import com.sync.entity.User;
import com.sync.service.UserService;
import com.sync.util.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Executors;

/**
 * Created by yexin on 2017/9/8.
 *
 * 在Impl基础上+ 防止缓存雪崩和缓存穿透功能
 */
@Service(value = "userServiceImpl4")
public class UserServiceImpl4 implements UserService {

    @Autowired
    IUserDao userMapper;

    @Autowired
    RedisCacheUtil redisCacheUtil;

    @Value("${timeOut}")
    private long timeOut;

    @Override
    public User getUser(Long userId) {

        String key = "user" + userId;
        User user = (User) redisCacheUtil.getValueOfObject(key);
        String keySign = key + "_sign";
        String valueSign = redisCacheUtil.getValue(keySign);
        if(user == null){//防止第一次查询时返回时空结果
            //防止缓存穿透
            if(redisCacheUtil.exists(key)){
                return  null;
            }
            user = userMapper.selectByPrimaryKey(userId);

            redisCacheUtil.set(key,user);
            redisCacheUtil.set(keySign,"1",timeOut *(new Random().nextInt(10) + 1));
//            redisCacheUtil.set(keySign,"1",0L);  //过期时间不能设置为0，必须比0大的数
            return user;
        }

        if(valueSign != null){
            return user;
        }else {
            //设置标记的实效时间
            Long tt = timeOut * (new Random().nextInt(10) + 1);
            System.out.println("tt:"+tt);
            redisCacheUtil.set(keySign,"1",tt);
            //异步处理缓存更新  应对与高并发的情况，会产生脏读的情况
            Executors.newCachedThreadPool().execute(new Runnable(){
                public void run() { //
                    System.out.println("-----执行异步操作-----");
                    User user1 = userMapper.selectByPrimaryKey(userId);
                    redisCacheUtil.set(key,user1);
                }
            });

//            new Thread(){
//                public void run() { //应对与高并发的情况，会产生脏读的情况
//                    System.out.println("-----执行异步操作-----");
//                    User user1 = userMapper.selectByPrimaryKey(userId);
//                    redisCacheUtil.set(key,user1);
//                }
//            }.start();
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public User delete(Long userId) {
        return null;
    }
}