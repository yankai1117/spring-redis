package com.example.servicelmpl;

import java.util.Date;
import java.util.List;

import com.example.controller.UserController;
import com.example.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.dao.DepartmentDao;
import com.example.dao.RoleDao;
import com.example.dao.UserDao;
import com.example.entity.Department;
import com.example.entity.Role;
import com.example.entity.User;
/**
 * @ClassName DataServiceImpl
 * @Description TODO
 * @Author yankai
 * @Date 2019/9/2916:13
 * @Version 1.0.0
 */
@Service
public class DataServiceImpl implements DataService {
    private static final Logger logger = LoggerFactory
            .getLogger(DataServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private DepartmentDao departmentDao;

    public void initData(){
        userDao.deleteAll();
        departmentDao.deleteAll();
        roleDao.deleteAll();

        Department department = new Department();
        department.setName("财务部");
        department.setCreatedate(new Date());

        departmentDao.save(department);
        logger.info("save department");
        Assert.notNull(department.getId(),"部门ID不能为空！");

        Role role = new Role();
        role.setLoginName("管理员");
        role.setCreatedate(new Date());

        roleDao.save(role);
        logger.info("save role");
        Assert.notNull(role.getId(),"角色ID不能为空");

        User user = new User();
        user.setName("管理员");
        user.setLoginName("admin");
        user.setDepartment(department);
        List<Role> roleList = roleDao.findAll();
        Assert.notNull(roleList,"角色列表不能为空！");
        user.setRoleList(roleList);
        user.setPassword("admin");

        userDao.save(user);
        logger.info("save user");
        Assert.notNull(user.getId(),"用户ID不能为空！");
    }
}