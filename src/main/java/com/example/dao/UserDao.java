package com.example.dao;

import com.example.entity.Role;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author yankai
 * @Date 2019/9/2914:49
 * @Version 1.0.0
 */
@Repository
public interface UserDao  extends JpaRepository<Role, Long> {
//    User findByLoginNameLike(String name);

    User readByLoginName(String loginName);
//    User readByLoginName(Role name);
//    List<User> getByCreatedateLessThan(Date star);
//    List<User> deleteAll();
      User save(User user);
}
