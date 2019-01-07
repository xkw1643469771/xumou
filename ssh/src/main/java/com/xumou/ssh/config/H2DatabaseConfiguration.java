package com.xumou.ssh.config;

import com.xumou.ssh.properties.H2DatabaseProperties;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * 配置H2数据库
 */
@Configuration
public class H2DatabaseConfiguration {

    private Logger logger = LoggerFactory.getLogger(H2DatabaseProperties.class);

    @Autowired
    private H2DatabaseProperties h2DatabaseProperties;

    @Bean
    public Server h2DatabaseManager() {
        Server webServer = null;
        if(h2DatabaseProperties.isStartDatabaseManager()){
            try {
                webServer = Server.createWebServer();
                webServer.start();
                logger.info("H2 database manager started: http://localhost:8082");
            } catch (SQLException e) {
                logger.error("H2 database manager started: http://localhost:8082");
            }
        }
        return webServer;
    }

}