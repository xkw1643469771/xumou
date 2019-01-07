package com.xumou.ssh.repository;

import com.xumou.ssh.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository 是基本增删改查
 * UserDao 是扩展用法, 随自己怎么用
 */
public interface RoleRepository extends JpaRepository<Role,Long>{

}