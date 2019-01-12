package com.xumou.ssh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xumou.ssh.entity.User;
import com.xumou.ssh.repository.UserRepository;

@Transactional
@Service
public class H2DatabaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private UserRepository userRepository;
    
    /** 测试批量插入save */
    public void batchInsert(){
    	Random r = new Random();
    	for (int i = 0; i < 10000*1000; i++) {
			User user = new User();
			user.setName(String.valueOf(r.nextInt()));
			user.setIdCard(String.valueOf(r.nextLong()));
			user.setAge((short)r.nextInt());
			userRepository.save(user);
			if(i % 1000 == 0){
				userRepository.flush();
				logger.info("已插入: " + i);
			}
		}
    	userRepository.flush();
    	logger.info("数据全部插入");
    }

	public void batchInsertByJdbcTemplate() {
		Random r = new Random();
    	for (int i = 0; i < 10000*1000; i++) {
			User user = new User();
			user.setName(String.valueOf(r.nextInt()));
			user.setIdCard(String.valueOf(r.nextLong()));
			user.setAge((short)r.nextInt());
			userRepository.insertUser(user);
			if(i % 1000 == 0){
				logger.info("已插入: " + i);
			}
		}
    	logger.info("数据全部插入: " + (10000*1000));
	}
	
	/** 测试批量插入通过List */
    public void batchInsertByList(){
    	Random r = new Random();
    	List<User> list = new ArrayList<>(1024);
    	for (int i = 0; i < 10000*1000; i++) {
			User user = new User();
			user.setName(String.valueOf(r.nextInt()));
			user.setIdCard(String.valueOf(r.nextLong()));
			user.setAge((short)r.nextInt());
			list.add(user);
			if(i % 1000 == 0){
				userRepository.saveAll(list);
				list.clear();
				userRepository.flush();
				logger.info("已插入: " + i);
			}
		}
    	userRepository.saveAll(list);
    	userRepository.flush();
    	logger.info("数据全部插入");
    }

}