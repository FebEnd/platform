package com.platform.parent.easemob;

import com.platform.parent.easemob.api.impl.EasemobChatGroup;
import io.swagger.client.model.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tqyao.
 */
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
public class ChatGroupTest {
    private EasemobChatGroup easemobChatGroup = new EasemobChatGroup();
    private static final Logger logger = LoggerFactory.getLogger(ChatGroupTest.class);

    @Test
    public void getChatGroups() {
        Long limit = 5L;
        String cursor = "";
        Object result = easemobChatGroup.getChatGroups(limit, cursor);
        logger.info(result.toString());
    }

    @Test
    public void getGroupsInfo() {
        String[] grousIds = new String[2];
        grousIds[0] = "24530476138550";
        grousIds[1] = "24530500255806";
        Object result = easemobChatGroup.getChatGroupDetails(grousIds);
        logger.info(result.toString());
    }

    @Test
    public void createGroup() {
        Group group = new Group();
        group.desc("org camp")._public(true).maxusers(50).approval(false).owner("13127502773");
        Object result = easemobChatGroup.createChatGroup(group);
        System.out.println(result);
        logger.info(result.toString());
    }

    @Test
    public void changeGroupInfo() {
        ModifyGroup group = new ModifyGroup();
        String groupId = "24530500255806";
        group.description("change group info").groupname("changed group").maxusers(300);
        Object result = easemobChatGroup.modifyChatGroup(groupId, group);
        logger.info(result.toString());
    }

    @Test
    public void addUsers() {
        String groupId = "24530500255806";
        UserNames userNames = new UserNames();
        UserName userList = new UserName();
        userList.add("aaaa12345652");
        userList.add("aaaa12345668");
        userNames.usernames(userList);
        Object result = easemobChatGroup.addBatchUsersToChatGroup(groupId, userNames);
        logger.info(result.toString());
    }

    @Test
    public void removeUsersFromGroup() {
        String groupId = "24530500255806";
        String[] userIds = new String[2];
        userIds[0] = "aaaa12345652";
        userIds[1] = "aaaa12345668";
        Object result = easemobChatGroup.removeBatchUsersFromChatGroup(groupId, userIds);
        logger.info(result.toString());
    }

    @Test
    public void getUsersFromGroup() {
        String groupId = "24530500255806";
        Object result = easemobChatGroup.getChatGroupUsers(groupId);
        logger.info(result.toString());
    }

    @Test
    public void transferGroupOwner() {
        String groupId = "24530500255806";
        NewOwner newOwner = new NewOwner();
        newOwner.newowner("aaa12345673");
        Object result = easemobChatGroup.transferChatGroupOwner(groupId, newOwner);
        logger.info(result.toString());
    }

    @Test
    public void addBlockUsers() {
        String groupId = "24530500255806";
        UserNames userNames = new UserNames();
        UserName userList = new UserName();
        userList.add("aaaa12345652");
        userList.add("aaaa12345668");
        userNames.usernames(userList);
        Object result = easemobChatGroup.addBatchBlockUsersToChatGroup(groupId, userNames);
        logger.info(result.toString());
    }

    @Test
    public void removeBlockUser() {
        String groupId = "24530500255806";
        String[] userIds = new String[2];
        userIds[0] = "aaaa12345652";
        userIds[1] = "aaaa12345668";
        Object result = easemobChatGroup.removeBatchBlockUsersFromChatGroup(groupId, userIds);
        logger.info(result.toString());
    }
}
