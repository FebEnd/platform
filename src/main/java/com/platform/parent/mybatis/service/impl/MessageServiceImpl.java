package com.platform.parent.mybatis.service.impl;

import com.platform.parent.mybatis.bean.Message;
import com.platform.parent.mybatis.bean.msgpayload.Body;
import com.platform.parent.mybatis.dao.BodyMapper;
import com.platform.parent.mybatis.dao.ExtMapper;
import com.platform.parent.mybatis.dao.MessageMapper;
import com.platform.parent.mybatis.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tqyao.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    BodyMapper bodyMapper;
    @Autowired
    ExtMapper extMapper;

    @Override
    @Transactional
    public int add(Message message) {
        int i = this.messageMapper.add(message);
        int j =0;
        for (Body body : message.getPayload().getBodies()) {
            j += this.bodyMapper.add(body);
        }
        int k = this.extMapper.add(message.getPayload().getExt());
        return (i>0&&j>0&&k>0)? 1:0;
    }
}
