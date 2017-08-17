package com.platform.parent.mybatis.bean;

import com.platform.parent.mybatis.bean.msgbody.Body;
import com.platform.parent.mybatis.bean.msgbody.TextBody;
import org.junit.Test;

/**
 * Created by tqyao.
 */
public class MsgBodyTest {

    @Test
    public void testBody() {
        Body body = new TextBody().msg("test");
        body.setType("txt");
        System.out.println(body);
    }
}
