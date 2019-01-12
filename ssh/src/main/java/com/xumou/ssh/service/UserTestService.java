package com.xumou.ssh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xumou.ssh.annotation.MethodTimer;
import com.xumou.ssh.entity.User;
import com.xumou.ssh.repository.UserRepository;

/**
 *
 */
@Transactional
@Service
public class UserTestService {

    @Autowired
    private UserRepository userRepository;

    @MethodTimer
    public Object selectUserByParam(User user) {
        return userRepository.selectUserByParam(user.getId());
    }
}