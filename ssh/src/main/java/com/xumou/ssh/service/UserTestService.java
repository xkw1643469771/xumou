package com.xumou.ssh.service;

import com.xumou.ssh.annotation.MethodInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xumou.ssh.annotation.MethodTimer;
import com.xumou.ssh.entity.User;
import com.xumou.ssh.repository.UserRepository;

import java.util.Random;

/**
 *
 */
@Transactional(rollbackFor = Throwable.class)
@Service
public class UserTestService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTestService self;

    @MethodTimer
    @MethodInfo(time = true, param = true)
    public Object selectUserByParam(User user) {
        return userRepository.selectUserByParam(user.getId());
    }

    @MethodTimer
    public void updateUserTestTrans() throws Exception{
        long id = 11901670;
        for (int i = 0; i < 4; i++) {
            // updateUserOne 有单独的事务, 即便是这里报异常也不会被影响
            self.updateUserOne(id ++);
            if(i == 3)
                throw new Exception();
        }
        throw new Exception();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateUserOne(long id){
        User one = userRepository.getOne(id);
        Random r = new Random();
        one.setName(String.valueOf(r.nextInt(1000)));
        userRepository.flush();
    }
}