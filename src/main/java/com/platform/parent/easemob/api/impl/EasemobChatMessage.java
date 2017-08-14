package com.platform.parent.easemob.api.impl;

import com.platform.parent.easemob.api.ChatMessageAPI;
import com.platform.parent.easemob.comm.EasemobAPI;
import com.platform.parent.easemob.comm.OrgInfo;
import com.platform.parent.easemob.comm.ResponseHandler;
import com.platform.parent.easemob.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.ChatHistoryApi;

/**
 * Created by tqyao.
 */
public class EasemobChatMessage implements ChatMessageAPI {
    private ResponseHandler responseHandler = new ResponseHandler();
    private ChatHistoryApi api = new ChatHistoryApi();
    @Override
    public Object exportChatMessages(final String timeStr) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatmessagesTimeGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME, TokenUtil.getAccessToken(),timeStr);
            }
        });
    }
}
