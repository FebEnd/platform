package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.Message;
import org.springframework.stereotype.Service;

/**
 * Created by tqyao.
 */
@Service
public interface MessageService {
    int add(Message message);
}
