package com.example.dao;

/**
 * @ClassName RoleDao
 * @Description TODO
 * @Author yankai
 * @Date 2019/9/2916:08
 * @Version 1.0.0
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Role;
@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

}