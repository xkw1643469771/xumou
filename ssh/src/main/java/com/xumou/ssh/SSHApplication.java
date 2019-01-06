package com.xumou.ssh;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.SQLException;

/**
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class SSHApplication {

    private static Logger logger = LoggerFactory.getLogger(SSHApplication.class);

    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(SSHApplication.class, args);
        startH2DatabaseManager();
        logger.info("SSHApplication start");
    }

    /** 启动数据库管理器 */
    private static void startH2DatabaseManager() {
        try {
            Server.createWebServer().start();
            logger.info("H2 database manager started: http://localhost:8082");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

}