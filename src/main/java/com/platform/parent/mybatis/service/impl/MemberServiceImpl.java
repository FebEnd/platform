package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.Member;
import com.platform.parent.mybatis.dao.MemberMapper;
import com.platform.parent.mybatis.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by tqyao.
 */
@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    MemberMapper memberMapper;

    @Override
    public int add(Member member) {
        return this.memberMapper.add(member);
    }

    @Override
    public int renewVip(long memberId, int day) {
        Member member = this.memberMapper.findMemberById(memberId);
        if (member == null) {
            return 0;
        }
        Timestamp vip = member.getVip();
        long now = System.currentTimeMillis();
        long expiration = 0;
        if (vip.getTime() < now) {
            //已过期
            expiration = now + 3600*1000*24*day;//vip过期时间为当前时间加day天
        } else {
            //未过期
            expiration = vip.getTime() + 3600*1000*24*day;
        }
        member.vip(new Timestamp(expiration));
        return this.memberMapper.update(member);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.memberMapper.deleteByIds(ids);
    }

    @Override
    public Member findMemberById(long id) {
        return this.memberMapper.findMemberById(id);
    }
}
