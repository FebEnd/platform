package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.UserSettlement;
import com.platform.parent.response.financial.Expense;
import com.platform.parent.response.financial.Income;
import com.platform.parent.response.financial.Profit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dengb.
 */
@Mapper
@Component
public interface UserSettlementMapper {
    int add(UserSettlement userSettlement);

    int update(UserSettlement userSettlement);

    List<UserSettlement> findAllUserSettlements();

    UserSettlement findUserSettlementByCampUser(@Param("campId") long campId, @Param("userId") long userId);

    List<UserSettlement> findUserSettlementBetweenTime(@Param("start") Timestamp start, @Param("end") Timestamp end);

    List<Income> findIncomeBetweenTime(@Param("start") Timestamp start, @Param("end") Timestamp end);

    List<Expense> findExpenseBetweenTime(@Param("start") Timestamp start, @Param("end") Timestamp end);

    Profit findProfitBetweenTime(@Param("start") Timestamp start, @Param("end") Timestamp end);

    List<Long> findDividendUsersByCampId(@Param("campId") long campId);
}
