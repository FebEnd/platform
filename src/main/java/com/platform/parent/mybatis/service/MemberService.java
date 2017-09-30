package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.Member;

/**
 * Created by tqyao.
 */
public interface MemberService {
    int add(Member member);
    int renewVip(long memberId, int day);
    int deleteByIds(String[] ids);
    Member findMemberById(long id);
}
