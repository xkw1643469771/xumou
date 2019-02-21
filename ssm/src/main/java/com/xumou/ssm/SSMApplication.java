package com.xumou.ssm;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.h2.tools.Server;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

/**
 *
 */
// activit中引用了security, 所以需要排除这个, 不然没法启动
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.xumou.ssm.mapper")
public class SSMApplication {

    private static Logger logger = LoggerFactory.getLogger(SSMApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SSMApplication.class);
        startH2DatabaseManager(context);
    }

    private static void startH2DatabaseManager(ConfigurableApplicationContext context) {
        try {
            int port = 10000 + Integer.valueOf(context.getEnvironment().getProperty("server.port"));
            Server.createWebServer("-webPort", String.valueOf(port)).start();
            logger.info("http://localhost:" + port);
        } catch (SQLException e) {}
    }

}