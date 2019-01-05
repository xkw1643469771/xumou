package com.xumou.ssh.repository;

import com.xumou.ssh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 */
public interface UserRepository extends JpaRepository<User,Long>{

    // 使用纯正SQL查询
    @Query(value = "select * from user", nativeQuery = true)
    User selectUserBySQL();

    // 使用HQL
    @Query(value = "select u from User u")
    User selectUserByHQL();

}