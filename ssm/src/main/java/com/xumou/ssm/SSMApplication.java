package com.xumou.ssm;

import org.h2.tools.Server;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

/**
 *
 */
@SpringBootApplication
@MapperScan("com.xumou.ssm.mapper")
public class SSMApplication {

    private static Logger logger = LoggerFactory.getLogger(SSMApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SSMApplication.class);
        startH2DatabaseManager();
    }

    private static void startH2DatabaseManager() {
        try {
            Server.createWebServer().start();
            logger.info("http://localhost:8082");
        } catch (SQLException e) {}
    }

}