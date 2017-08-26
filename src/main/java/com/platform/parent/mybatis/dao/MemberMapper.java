package com.platform.parent.mybatis.dao;

import com.platform.parent.mybatis.bean.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by tqyao.
 */
@Mapper
@Component
public interface MemberMapper {
    int add(Member member);
    int update(Member member);
    int deleteByIds(String[] ids);
    Member findMemberById(long id);

}
