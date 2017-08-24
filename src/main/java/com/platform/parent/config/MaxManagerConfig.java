package com.platform.parent.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by tqyao.
 * load config file for manager limit
 */
@Component
@ConfigurationProperties( prefix = "manager.max")
@PropertySource("classpath:config/teacher.properties")
public class MaxManagerConfig {
    private int one, two, three, four, five;

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public int getThree() {
        return three;
    }

    public void setThree(int three) {
        this.three = three;
    }

    public int getFour() {
        return four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public int getFive() {
        return five;
    }

    public void setFive(int five) {
        this.five = five;
    }

    public int getMaxManager(int star) {
        switch (star) {
            case 1:
            default:
                return getOne();
            case 2:
                return getTwo();
            case 3:
                return getThree();
            case 4:
                return getFour();
            case 5:
                return getFive();
        }
    }
}
