package com.xumou.ssh.service;

import com.xumou.ssh.entity.User;
import com.xumou.ssh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional
@Service
public class UserTestService {

    @Autowired
    private UserRepository userRepository;

    public Object selectUserByParam(User user) {
        return userRepository.selectUserByParam(user.getId());
    }
}