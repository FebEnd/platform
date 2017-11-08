package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.UserSettlement;
import com.platform.parent.mybatis.dao.UserSettlementMapper;
import com.platform.parent.mybatis.service.UserSettlementService;
import com.platform.parent.response.financial.Expense;
import com.platform.parent.response.financial.Income;
import com.platform.parent.response.financial.Profit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dengb.
 */
@Service
public class UserSettlementServiceImpl implements UserSettlementService {
    @Autowired
    UserSettlementMapper userSettlementMapper;

    @Override
    public int add(UserSettlement userSettlement) {
        return this.userSettlementMapper.add(userSettlement);
    }

    @Override
    public int update(UserSettlement userSettlement) {
        return this.userSettlementMapper.update(userSettlement);
    }

    @Override
    public List<UserSettlement> findAllUserSettlements() {
        return this.userSettlementMapper.findAllUserSettlements();
    }

    @Override
    public UserSettlement findUserSettlementByCampUser(long campId, long userId)
    {
        return this.userSettlementMapper.findUserSettlementByCampUser(campId, userId);
    }

    @Override
    public List<UserSettlement> findUserSettlementBetweenTime(Timestamp start, Timestamp end)
    {
        return this.userSettlementMapper.findUserSettlementBetweenTime(start, end);
    }

    @Override
    public List<Income> findIncomeBetweenTime(Timestamp start, Timestamp end)
    {
        return this.userSettlementMapper.findIncomeBetweenTime(start, end);
    }

    @Override
    public List<Expense> findExpenseBetweenTime(Timestamp start, Timestamp end)
    {
        return this.userSettlementMapper.findExpenseBetweenTime(start, end);
    }

    @Override
    public Profit findProfitBetweenTime(Timestamp start, Timestamp end)
    {
        return this.userSettlementMapper.findProfitBetweenTime(start, end);
    }

    @Override
    public List<Long> findDividendUsersByCampId(long campId)
    {
        return this.userSettlementMapper.findDividendUsersByCampId(campId);
    }
}
