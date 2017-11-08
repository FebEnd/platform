package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.Statement;

import java.util.List;

/**
 * Created by dengb.
 */
public interface StatementService {
    List<Statement> findStatements();
}
