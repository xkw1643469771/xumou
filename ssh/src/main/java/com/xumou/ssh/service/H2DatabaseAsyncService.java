package com.xumou.ssh.service;

import com.xumou.ssh.entity.User;
import com.xumou.ssh.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Semaphore;

@Transactional
@Service
public class H2DatabaseAsyncService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private UserRepository userRepository;

    @Async
	public void saveUser(User user, Semaphore semaphore) {
		try{
			userRepository.save(user);
		}finally {
			// 释放信号
			semaphore.release();
		}
	}

	@Async
	public void saveUserList(List<User> users, Semaphore semaphore) {
		try{
			userRepository.saveAll(users);
		}finally {
			// 释放信号
			semaphore.release();
		}
	}
}