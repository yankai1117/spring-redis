package com.spring.sourcecode;
import com.google.gson.Gson;
import com.sync.entity.User;
import org.springframework.beans.factory.xml.XmlBeanFactory;

import	java.util.HashSet;
import	java.util.Set;

/**
 * @ClassName TestForDefinitionSet
 * @Description TODO
 * @Author yankai
 * @Date 2019/10/59:46
 * @Version 1.0.0
 */
public class TestForDefinitionSet {
    public static void main(String [] args) throws ClassNotFoundException {

        User user = new User();
        user.setUserId(20115007L);
        user.setName("yankai");
        user.setAddr("济南");
        user.setSex("男");
        user.setAge(18);
        XmlBeanFactory bean;
        TestForDefinitTransMsg<User> msg = new TestForDefinitTransMsg<>("sdg",user,"com.sync.entity.User","updateSet");
//        Set result = msg.getObj();
//        Class oo = getclass("java.util.Set");
//        System.out.println("Class = "+oo);
        Gson gson = new Gson();
        String classJson = gson.toJson(user);
        System.out.println("classJson = "+classJson);
        gson.fromJson(classJson,Class.forName("java.util.Set"));
        if("com.sync.entity.User" == msg.getTypeName() || "com.sync.entity.User".equals(msg.getTypeName())){

        }
    }
    //以String类型的className实例化类
    public static Class getclass(String className)//className是类名
    {
        Class obj = null;
        try {

            obj = Class.forName(className);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return obj;
        }


    }
}
