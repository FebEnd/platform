package com.platform.parent.easemob;

import com.platform.parent.easemob.api.impl.EasemobIMUser;
import io.swagger.client.model.NewPassword;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created by tqyao.
 */
public class UserTest {
    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);
    private EasemobIMUser easemobIMUsers = new EasemobIMUser();

    @Test
    public void createUser() {
        RegisterUsers users = new RegisterUsers();
        User user = new User().username("aaaa123456" + new Random().nextInt(500)).password("123456");
        User user1 = new User().username("aaa123456" + new Random().nextInt(500)).password("123456");
        users.add(user);
        users.add(user1);
        Object result = easemobIMUsers.createNewIMUserSingle(users);
        logger.info(result.toString());
        Assert.assertNotNull(result);
    }

    @Test
    public void getUserByName() {
        String userName = "15001877056";
        Object result = easemobIMUsers.getIMUserByUserName(userName);
        logger.info(result.toString());
    }

    @Test
    public void gerUsers() {
        Object result = easemobIMUsers.getIMUsersBatch(5L, null);
        logger.info(result.toString());
    }

    @Test
    public void changePassword() {
        String userName = "15001877056";
        NewPassword psd = new NewPassword().newpassword("123");
        Object result = easemobIMUsers.modifyIMUserPasswordWithAdminToken(userName, psd);
        logger.info(result.toString());
    }

    @Test
    public void getFriend() {
        String userName = "15001877056";
        Object result = easemobIMUsers.getFriends(userName);
        logger.info(result.toString());
    }
}
