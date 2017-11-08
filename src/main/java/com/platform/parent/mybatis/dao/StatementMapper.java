package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Statement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dengb.
 */
@Mapper
@Component
public interface StatementMapper {
    List<Statement> findStatements();
}
