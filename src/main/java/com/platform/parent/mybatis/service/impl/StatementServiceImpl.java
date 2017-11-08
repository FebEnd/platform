package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.Statement;
import com.platform.parent.mybatis.dao.StatementMapper;
import com.platform.parent.mybatis.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dengb.
 */
@Service
public class StatementServiceImpl implements StatementService {
    @Autowired
    StatementMapper statementMapper;

    @Override
    public List<Statement> findStatements() {
        return this.statementMapper.findStatements();
    }
}
