package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.UserSettlement;
import com.platform.parent.response.financial.Expense;
import com.platform.parent.response.financial.Income;
import com.platform.parent.response.financial.Profit;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dengb.
 */
public interface UserSettlementService {
    int add(UserSettlement userSettlement);

    int update(UserSettlement userSettlement);

    List<UserSettlement> findAllUserSettlements();

    UserSettlement findUserSettlementByCampUser(long campId, long userId);

    List<UserSettlement> findUserSettlementBetweenTime(Timestamp start, Timestamp end);

    List<Income> findIncomeBetweenTime(Timestamp start, Timestamp end);

    List<Expense> findExpenseBetweenTime(Timestamp start, Timestamp end);

    Profit findProfitBetweenTime(Timestamp start, Timestamp end);

    List<Long> findDividendUsersByCampId(long campId);
}
