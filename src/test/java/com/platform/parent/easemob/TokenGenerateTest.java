package com.platform.parent.easemob;

import com.platform.parent.easemob.api.impl.EasemobAuthToken;
import io.swagger.client.ApiException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tqyao.
 */
public class TokenGenerateTest {
    private EasemobAuthToken easemobAuthToken = new EasemobAuthToken();

    @Test
    public void testTokenGet() throws ApiException {
        System.out.println(easemobAuthToken.getAuthToken());
        Assert.assertNotNull(easemobAuthToken.getAuthToken());
    }
}
