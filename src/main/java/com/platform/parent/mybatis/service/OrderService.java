package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.Order;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tqyao.
 */
public interface OrderService {
    int add(Order order);
    int update(Order order);
    int deleteByIds(String[] ids);
    Order findOrderById(long id);
    int cancelOrder(Order order);
    List<Order> findCompleteOrderForCamp();

    /**
     * 查找从start 到end之间的order记录
     * @param start
     * @param end
     * @return
     */
    List<Order> findOrderByTime(Timestamp start, Timestamp end);

    /**
     * 查找type的order记录，即某个训练营的相关balance记录
     * @param type
     * @return
     */
    List<Order> findOrderByType(long type);

}
