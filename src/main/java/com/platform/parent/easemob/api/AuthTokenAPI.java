package com.platform.parent.easemob.api;

import io.swagger.client.ApiException;

/**
 * Created by tqyao.
 */
public interface AuthTokenAPI {
    /**
     * Request an Authentication Token
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response Body
     */
    Object getAuthToken();
}
