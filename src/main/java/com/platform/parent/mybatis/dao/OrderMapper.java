package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface OrderMapper {
    int add(Order order);
    int update(Order order);
    int deleteByIds(String[] ids);
    Order findOrderById(long id);
    List<Order> findCompleteOrderForCamp();

    /**
     * 查找从start 到end之间的balance记录
     * @param start
     * @param end
     * @return
     */
    List<Order> findOrderByTime(@Param("start") Timestamp start, @Param("end") Timestamp end);

    /**
     * 查找type的balance记录，即某个训练营的相关balance记录
     * @param type
     * @return
     */
    List<Order> findOrderByType(long type);

}
