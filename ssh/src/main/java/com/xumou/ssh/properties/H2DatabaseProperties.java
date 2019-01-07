package com.xumou.ssh.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * H2数据库配置属性
 */
@Component
@ConfigurationProperties("ssh.h2")
public class H2DatabaseProperties {

    private boolean startDatabaseManager;

    public H2DatabaseProperties(){
        System.out.println();
    }

    public boolean isStartDatabaseManager() {
        return startDatabaseManager;
    }

    public void setStartDatabaseManager(boolean startDatabaseManager) {
        this.startDatabaseManager = startDatabaseManager;
    }
}