package com.xumou.ssh.repository;

import com.xumou.ssh.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JpaRepository 是基本增删改查
 * JpaSpecificationExecutor 时JPA提供的查询
 */
public interface RoleRepository extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role>{

}