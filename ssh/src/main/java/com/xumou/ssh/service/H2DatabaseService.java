package com.xumou.ssh.service;

import com.xumou.ssh.entity.User;
import com.xumou.ssh.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

@Transactional
@Service
public class H2DatabaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private UserRepository userRepository;
    @Autowired
	private H2DatabaseAsyncService h2DatabaseAsyncService;
    
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

	/** 使用save方法异步插入1千万条数据 */
	public void batchInsertAsync() {
		Random r = new Random();
		// 信号器, 用于控制线程数
		Semaphore semaphore = new Semaphore(10);
		for (int i = 0; i < 10000*1000; i++) {
			User user = new User();
			user.setName(String.valueOf(r.nextInt()));
			user.setIdCard(String.valueOf(r.nextLong()));
			user.setAge((short)r.nextInt());
			// 获取一个信号
			semaphore.acquireUninterruptibly();
			h2DatabaseAsyncService.saveUser(user, semaphore);
			if(i % 1000 == 0){
				logger.info("已插入: " + i);
			}
		}
		logger.info("数据全部插入");
	}

	/** 使用saveAll方法异步插入1千万条数据 */
	public void batchInsertAsyncByList() {
		Random r = new Random();
		// 信号器, 用于控制线程数
		Semaphore semaphore = new Semaphore(10);
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 10000*1000; i++) {
			User user = new User();
			user.setName(String.valueOf(r.nextInt()));
			user.setIdCard(String.valueOf(r.nextLong()));
			user.setAge((short)r.nextInt());
			users.add(user);
			if(i % 1000 == 0){
				// 获取一个信号
				semaphore.acquireUninterruptibly();
				h2DatabaseAsyncService.saveUserList(users, semaphore);
				users = new ArrayList<>();
				logger.info("已插入: " + i);
			}
		}
		logger.info("数据全部插入");
	}
}