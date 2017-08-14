package com.platform.parent.easemob.api.impl;

import com.platform.parent.easemob.api.AuthTokenAPI;
import com.platform.parent.easemob.comm.TokenUtil;

/**
 * Created by tqyao.
 */
public class EasemobAuthToken implements AuthTokenAPI {
    @Override
    public Object getAuthToken() {
        return TokenUtil.getAccessToken();
    }
}
