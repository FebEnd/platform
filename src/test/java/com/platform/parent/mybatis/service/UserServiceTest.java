package com.platform.parent.mybatis.service;

import com.platform.parent.mybatis.bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * Created by tqyao.
*/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService service;

    @Test
    public void add() {
        User user = new User().phone("testphone3").password("testpassword").nickname("nthuser");
//        User user = new User("testphone2","thirduser","testpassword");
        long i = service.add(user);
        System.out.println(i);
        System.out.println(user.getId());
        Assert.assertNotEquals(0, i);
    }

    @Test
    public void update() {
        User user = new User().phone("testphone3").password("testpassword").nickname("updatenickname").id(3);
//        User user = new User(1,"updatephone", "updatenickname","updatepassword");
        int i = service.update(user);
        Assert.assertNotEquals(0,i);
    }

    @Test
    public void delete() {
        String[] ids = {"1", "2"};
        int i = service.deleteByIds(ids);
        Assert.assertNotEquals(0, i);
    }

    @Test
    public void queryUserById() {
        long id = 3l;
        User user = service.queryUserById(id);
        Assert.assertEquals("thirduser", user.getNickname());
    }

    @Test
    public void findUserByPhone() {
        String phone = "testphone";
        User user = service.findUserByPhone(phone);
        Assert.assertEquals(phone, user.getPhone());
    }

    @Test
    public void findUserByPhoneWithRole() {
        String phone = "testphone";
        User user = service.findUserByPhoneWithRole(phone);
        Assert.assertEquals("ROLE_USER", user.getRoles().get(0).getName());
    }

}
