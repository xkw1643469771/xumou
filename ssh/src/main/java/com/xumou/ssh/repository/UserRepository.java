package com.xumou.ssh.repository;

import com.xumou.ssh.entity.User;
import com.xumou.ssh.repository.custom.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * JpaRepository 是基本增删改查
 * UserDao 是扩展用法, 随自己怎么用
 */
public interface UserRepository extends JpaRepository<User,Long>, UserDao{

    // 使用纯正SQL查询
    @Query(value = "select * from user limit 1,1", nativeQuery = true)
    User selectUserBySQL();

    // 使用HQL
    @Query(value = "select u from User u where u.id = 1")
    User selectUserByHQL();

}