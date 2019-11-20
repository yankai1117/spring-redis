package com.sync.service.Impl;

import com.sync.dao.IUserDao;
import com.sync.entity.User;
import com.sync.service.SendService;
import com.sync.service.UserService;
import com.sync.util.RedisCacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author yankai
 * @Date 2019/10/221:44
 * @Version 1.0.0
 */
@Service(value = "userSerivceImpl")
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    IUserDao userMapper;
    @Autowired
    RedisCacheUtil redisCacheUtil;
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    SendService sendService;
    /**
     * @Author yankai
     * @Description //TODO 
     * @Date 10:02 2019/10/3
     * value 缓存的名称，在 spring 配置文件中定义，必须指定至少一个例如: @Cacheable(value={”cache1”,”cache2”}
     * key 缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合
     * condition 缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存
     **/
    @Cacheable(key = "caches[0].name + T(String).valueOf(#userId)", unless = "#result eq null")
    public User getUser(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }



    @Cacheable(key = "caches[0].name + #name")
    public String getIdByName(String name) {
        Long userId = userMapper.getIdByName(name);
        return String.valueOf(userId);
    }
    /**
     * @Cacheable 可以标记在一个方法上，也可以标记在一个类上。当标记在一个方法上时表示该方法是支持缓存的，
     * 当标记在一个类上时则表示该类所有的方法都是支持缓存的。对于一个支持缓存的方法，
     * Spring会在其被调用后将其返回值缓存起来，以保证下次利用同样的参数来执行该方法时可以直接从缓存中获取结果，
     * 而不需要再次执行该方法。Spring在缓存方法的返回值时是以键值对进行缓存的，值就是方法的返回结果，
     * 至于键的话，Spring又支持两种策略，默认策略和自定义策略
     *
     * 当一个支持缓存的方法在对象内部被调用时是不会触发缓存功能的。
     **/
    //使用getUserByName方式调用getIdByName 和getUser方法来实现查询，但是如果用此方式在controller中直接调用
    //getUserByName方法，缓存效果是不起作用的，必须是直接调用getIdByName和getUser方法才能起作用
    public User getUserByName(String name) {
        //通过name 查询到主键  再由主键查询实体
        return getUser(Long.valueOf(getIdByName(name)));
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public User delete(Long userId) {
        return null;
    }
    @Override
    public User updateUser(User user) {
        System.out.println("   impl2   active   ");
        String key = "user"+ user.getUserId();
        System.out.println("key:"+key);
        //是否存在key
        if(!redisCacheUtil.exists(key)){
            return userMapper.updateByPrimaryKeySelective(user) == 1 ? user : null;
        }
        /*  更新key对应的value
            更新队列
         */
        User user1 = (User)redisCacheUtil.getValueOfObject(key);
//        try {
//            redisCacheUtil.set(key,user);
//            TransMsg<User> msg = new TransMsg<User>(key,user,this.getClass().getName(),"updateUser");
//            sendService.sendMessage(msg);
//
//        }catch (Exception e){
//            redisCacheUtil.set(key,user1);
//        }
        return user;
    }
}