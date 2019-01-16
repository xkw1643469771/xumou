package com.xumou.ssh.config;

import com.xumou.ssh.properties.H2DatabaseProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 配置异步任务
 */
@Configuration
@EnableAsync
public class AsyncConfiguration {

    private Logger logger = LoggerFactory.getLogger(H2DatabaseProperties.class);

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("ssh-task-");//设置线程名称前缀
        taskExecutor.setCorePoolSize(10);//设置可以使用的线程数
        return taskExecutor;
    }

}