package com.platform.parent.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by tqyao.
 * load config file for manager share
 */
@Component
@ConfigurationProperties( prefix = "manager")
@PropertySource("classpath:config/teacher.properties")
public class ManagerShareConfig {
    private int share;

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }
}
