package com.platform.parent.easemob.api.impl;

import com.platform.parent.easemob.api.SendMessageAPI;
import com.platform.parent.easemob.comm.EasemobAPI;
import com.platform.parent.easemob.comm.OrgInfo;
import com.platform.parent.easemob.comm.ResponseHandler;
import com.platform.parent.easemob.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.MessagesApi;
import io.swagger.client.model.Msg;

/**
 * Created by tqyao.
 */
public class EasemobSendMessage implements SendMessageAPI {
    private ResponseHandler responseHandler = new ResponseHandler();
    private MessagesApi api = new MessagesApi();
    @Override
    public Object sendMessage(final Object payload) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameMessagesPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME, TokenUtil.getAccessToken(), (Msg) payload);
            }
        });
    }
}
