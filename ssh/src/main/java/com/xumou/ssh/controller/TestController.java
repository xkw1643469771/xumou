package com.xumou.ssh.controller;

import com.xumou.ssh.entity.User;
import com.xumou.ssh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("insertUserByJpa")
    public void insertUserByJpa(){
        User user = new User();
        user.setName("Tom");
        userRepository.saveAndFlush(user);
    }


}