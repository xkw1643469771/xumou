package com.xumou.ssh.repository.custom;

import java.util.List;
import java.util.Map;

/**
 * UserRepository的扩展
 */
public interface UserDao {

    List<Map<String, Object>> selectUser();

}