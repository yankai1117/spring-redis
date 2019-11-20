package com.sync.entity;

/**
 * @ClassName User
 * @Description TODO
 * @Author yankai
 * @Date 2019/10/221:37
 * @Version 1.0.0
 */

public class User {
    private Long userId;
    private String name;
    private Integer age;
    private String sex;
    private String addr;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
