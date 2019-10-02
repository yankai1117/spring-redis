package com.example.controller;

import com.example.entity.User;
import com.example.service.DataService;
import com.example.service.UserRedisService;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author yankai
 * @Description //TODO
 * @Date 16:25 2019/9/29
 * @Param 
 * @return 
 **/
@RestController
//@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);
    @Resource
    private UserService userService;
    @Resource
    private DataService dataService;
    @Resource
    private UserRedisService userRedisService;

    @RequestMapping("/test")
    public  String test(String name){
        logger.info("接收到请求-------------------->>name=" + name );
        System.out.println("接收到请求-------------------->>name=" + name);
        return "返回响应内容" + name;
    }

    @RequestMapping("/initRedisData")
    public String initRedisData(){
        logger.info("请求过来了===================");
        userRedisService.redisInitData();
        return "success";
    }

    @RequestMapping("/demo/{name}")
    @ResponseBody
    public String demoShowName(@PathVariable String name){
        logger.debug("访问getUserByName,Name={}",name);
        return "name is " + name;
    }
    /**
     * @Title: UserController
     * @Description: 数据初始化
     * @author mengfanzhu
     * @throws
     */
    @RequestMapping("/initdata")
    @ResponseBody
    public String initData(){
        dataService.initData();
        return "success";
    }

    /**
     * @Title: UserController
     * @Description: 由loginName获取user
     * @param loginName
     * @author mengfanzhu
     * @throws
     */
    @RequestMapping("/getUserByLoginName/{loginName}")
    @ResponseBody
    public Map<String,Object> getUserByName(@PathVariable String loginName){
        Map<String,Object> result = new HashMap<String, Object>();
        User user = userService.readByLoginName(loginName);
        Assert.notNull(user);
        result.put("name", user.getName());
        result.put("loginName", user.getLoginName());
        result.put("departmentName",user.getDepartment().getName());
        result.put("roleName", user.getRoleList().get(0).getLoginName());
        return result;
    }

    @RequestMapping("/getUserRedisByLoginName/{loginName}")
    @ResponseBody
    public Map<String,Object> getUserRedisByLoginName(@PathVariable String loginName){
        Map<String,Object> result = new HashMap<String, Object>();
        User user = userRedisService.getUserRedis(loginName);
        Assert.notNull(user);
        result.put("name", user.getName());
        result.put("loginName", user.getLoginName());
        result.put("departmentName",user.getDepartment().getName());
        result.put("roleName", user.getRoleList().get(0).getLoginName());
        return result;
    }
}