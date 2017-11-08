package com.platform.parent.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by tqyao.
 */
@Component
@ConfigurationProperties( prefix = "price")
@PropertySource("classpath:config/price.properties")
public class PriceConfig {
    private Map<String, String> sprint, ordinary, dividend;

    public Map<String, String> getSprint() {
        return sprint;
    }

    public void setSprint(Map<String, String> sprint) {
        this.sprint = sprint;
    }

    public Map<String, String> getOrdinary() {
        return ordinary;
    }

    public void setOrdinary(Map<String, String> ordinary) {
        this.ordinary = ordinary;
    }

    public  Map<String, String> getDividend()
    {
        return dividend;
    }

    public void setDividend(Map<String, String> dividend)
    {
        this.dividend = dividend;
    }
}
