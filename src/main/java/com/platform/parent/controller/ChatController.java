package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.easemob.api.ChatGroupAPI;
import com.platform.parent.easemob.api.impl.EasemobChatGroup;
import com.platform.parent.mybatis.bean.CampAttend;
import com.platform.parent.mybatis.bean.ChatGroup;
import com.platform.parent.mybatis.bean.Topic;
import com.platform.parent.mybatis.bean.User;
import com.platform.parent.mybatis.service.CampAttendService;
import com.platform.parent.mybatis.service.ChatGroupService;
import com.platform.parent.mybatis.service.TopicService;
import com.platform.parent.mybatis.service.UserService;
import com.platform.parent.util.EnumUtil;
import com.platform.parent.util.ErrorCode;
import com.platform.parent.util.StringUtil;
import io.swagger.client.model.Group;
import io.swagger.client.model.UserName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tqyao.
 */
@RestController
@RequestMapping(value = "/chat")
public class ChatController {
    static final ChatGroupAPI easemobChatGroup = new EasemobChatGroup();
    static final String NAME = "1v1私聊", DESC = "1v1私聊";
    static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    @Autowired
    UserService userService;
    @Autowired
    CampAttendService campAttendService;
    @Autowired
    ChatGroupService chatGroupService;
    @Autowired
    TopicService topicService;

    @RequestMapping(value = "/createPrivate", method = RequestMethod.POST)
    @ResponseBody
    public Object createPrivate(@RequestParam(required = false, value = "member") String member,
                                @RequestParam(required = true, value = "userId") String _userId,
                                @RequestParam("campId") String _campId) {
        if (!StringUtil.isNumber(_userId) || !StringUtil.isNumber(_campId)) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(_userId);
        User owner = this.userService.queryUserById(userId);
        if (owner == null) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        long campId = Long.valueOf(_campId);
        CampAttend campAttend = this.campAttendService.findCampAttendByUserIdAndCampId(owner.getId(),campId);
        if (campAttend == null) {
            return EnumUtil.errorToJson(ErrorCode.USER_NOT_ATTEND_CAMP);
        }
        String members = "";
        UserName userList = new UserName();
        members = getMembers(member, campId, userList);
        Group group = new Group().groupname(NAME).desc(DESC)._public(false).approval(true).owner(owner.getPhone()).members(userList);
        String createResult = (String)easemobChatGroup.createChatGroup(group);
        logger.info("create chat group result:{}", createResult);
        if (createResult == null) return EnumUtil.errorToJson(ErrorCode.CREATE_CHAT_GROUP_FAILED);
        JSONObject object = JSONObject.parseObject(createResult);
        JSONObject data = (JSONObject) object.get("data");
        String groupId = data.getString("groupid");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Topic topic = new Topic().campId(campId).created(now).essence(false).groupId(groupId)
                .name(NAME).ownerId(userId).pri(true).top(false).updated(now).temp(true);
        ChatGroup chatGroup = new ChatGroup().id(groupId).name(NAME).description(DESC).owner(owner.getPhone()).member(members);
        //todo insert into database
        int i = this.chatGroupService.add(chatGroup);
        int j = this.topicService.add(topic);
        JSONObject result = new JSONObject();
        data = new JSONObject();
        result.put("status",200);
        result.put("message","成功");
        data.put("groupId", groupId);
        result.put("data",data);
        return result;
        /*if (StringUtil.isNull(member)) {
            Group group = new Group().groupname(NAME).desc(DESC)._public(false).approval(false).owner(owner.getPhone());
            String createResult = (String)easemobChatGroup.createChatGroup(group);
            logger.info("create chat group result:{}", createResult);
            if (createResult == null) return EnumUtil.errorToJson(ErrorCode.CREATE_CHAT_GROUP_FAILED);
            JSONObject object = JSONObject.parseObject(createResult);
            JSONObject data = (JSONObject) object.get("data");
            String groupId = data.getString("groupid");
            ChatGroup chatGroup = new ChatGroup().id(groupId).name(NAME).description(DESC).owner(owner.getPhone());
            String members = "";
            List<User> teachers = this.userService.findUserByCampId(campId);
            UserNames userNames = new UserNames();
            UserName userList = new UserName();
            int count = 0;
            for (User teacher : teachers) {
                if (count==0) {
                    members += teacher.getPhone();
                    userList.add(teacher.getPhone());
                    count++;
                } else {
                    members += ","+teacher.getPhone();
                    userList.add(teacher.getPhone());
                    count++;
                }
            }
            chatGroup.member(members);
            userNames.usernames(userList);
            String memberResult = (String)easemobChatGroup.addBatchUsersToChatGroup(groupId, userNames);
            if (memberResult == null) return EnumUtil.errorToJson(ErrorCode.ATTEND_CAMP_FAILED);
            //todo insert into database
            JSONObject result = new JSONObject();
            data = new JSONObject();
            result.put("status",200);
            result.put("message","成功");
            data.put("groupId", groupId);
            result.put("data",data);
            return result;
        } else {
            String[] phones = member.trim().split(",");
            Group group = new Group().groupname(NAME).desc(DESC)._public(false).approval(false).owner(owner.getPhone());
            String createResult = (String)easemobChatGroup.createChatGroup(group);
            logger.info("create chat group result:{}", createResult);
            if (createResult == null) return EnumUtil.errorToJson(ErrorCode.CREATE_CHAT_GROUP_FAILED);
            JSONObject object = JSONObject.parseObject(createResult);
            JSONObject data = (JSONObject) object.get("data");
            String groupId = data.getString("groupid");
            ChatGroup chatGroup = new ChatGroup().id(groupId).name(NAME).description(DESC).owner(owner.getPhone());
            String members = "";
            UserNames userNames = new UserNames();
            UserName userList = new UserName();
            for (int i = 0; i < phones.length; i++) {
                if (i==0) {
                    members += phones[i];
                    userList.add(phones[i]);
                } else {
                    members += "," + phones[i];
                    userList.add(phones[i]);
                }
            }
            userNames.usernames(userList);
            String memberResult = (String)easemobChatGroup.addBatchUsersToChatGroup(groupId, userNames);
            if (memberResult == null) return EnumUtil.errorToJson(ErrorCode.ATTEND_CAMP_FAILED);
            //todo insert into database
            JSONObject result = new JSONObject();
            data = new JSONObject();
            result.put("status",200);
            result.put("message","成功");
            data.put("groupId", groupId);
            result.put("data",data);
        }*/
    }

    @RequestMapping(value = "/createTopic", method = RequestMethod.POST)
    @ResponseBody
    public Object createTopic(@RequestParam("title") String title, @RequestParam("userId") String _userId, @RequestParam("campId") String _campId) {
        //todo 根据传入参数创建topic存入数据库，并创建相应name=话题：｛title｝的群聊，并自动将导师和观察员加入，设置加入条件为无，return groupId
        if (!StringUtil.isNumber(_userId) || !StringUtil.isNumber(_campId)) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(_userId);
        User owner = this.userService.queryUserById(userId);
        if (owner == null) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        long campId = Long.valueOf(_campId);
        CampAttend campAttend = this.campAttendService.findCampAttendByUserIdAndCampId(owner.getId(),campId);
        if (campAttend == null) {
            return EnumUtil.errorToJson(ErrorCode.USER_NOT_ATTEND_CAMP);
        }
        List<User> users = this.campAttendService.findTeachersAndObserverByCampId(campId);
        UserName userList = new UserName();
        for (User user : users) {
            userList.add(user.getPhone());
        }
        Group group = new Group().groupname("话题："+ title)._public(true).approval(false).owner(owner.getPhone()).members(userList);
        String createResult = (String)easemobChatGroup.createChatGroup(group);
        logger.info("create chat group result:{}", createResult);
        if (createResult == null) return EnumUtil.errorToJson(ErrorCode.CREATE_CHAT_GROUP_FAILED);
        JSONObject object = JSONObject.parseObject(createResult);
        JSONObject data = (JSONObject) object.get("data");
        String groupId = data.getString("groupid");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Topic topic = new Topic().campId(campId).created(now).essence(false).groupId(groupId)
                .name("话题："+ title).ownerId(userId).pri(false).top(false).updated(now).temp(false);
        ChatGroup chatGroup = new ChatGroup().id(groupId).name(NAME).description(DESC).owner(owner.getPhone()).member("");
        //todo insert into database
        int i = this.chatGroupService.add(chatGroup);
        List<User> teachers = this.userService.findUserByCampId(campId);
        int j = this.topicService.add(topic, teachers);
        JSONObject result = new JSONObject();
        data = new JSONObject();
        result.put("status",200);
        result.put("message","成功");
        data.put("groupId", groupId);
        result.put("data",data);
        return result;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Object modify(@RequestParam("groupId") String groupId,
                         @RequestParam("campId") String _campId, @RequestParam("userId") String _userId,
                         @RequestParam(required = false, value = "pri") boolean pri,
                         @RequestParam(required = false, value = "essence") boolean essence,
                         @RequestParam(required = false, value = "top") boolean top) {
        //todo 根据pri， essence， top的值调用相应的方法
        if (!(StringUtil.isNumber(groupId)&&StringUtil.isNumber(_campId)&&StringUtil.isNumber(_userId))) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long campId = Long.valueOf(_campId);
        long userId = Long.valueOf(_userId);
        User user = this.userService.queryUserById(userId);
        if (user == null) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        CampAttend campAttend = this.campAttendService.findCampAttendByUserIdAndCampId(userId, campId);
        if (campAttend == null) {
            return EnumUtil.errorToJson(ErrorCode.USER_NOT_ATTEND_CAMP);
        }
        Topic topic = this.topicService.findTopicByGroupId(groupId);
        ChatGroup chatGroup = this.chatGroupService.findChatGroupById(groupId);
        if (topic == null) {
            return EnumUtil.errorToJson(ErrorCode.TOPIC_NOT_EXIST);
        }
        if (pri) {
            if (!(user.getPhone().equals(chatGroup.getOwner()) || campAttend.getRole() == 2)) {
                return EnumUtil.errorToJson(ErrorCode.NO_AUTH_ERROR);
            }
            topic.pri(true).temp(false);
            topic.updated(new Timestamp(System.currentTimeMillis()));
            int i = this.topicService.update(topic);
            if (i <= 0) {
                return EnumUtil.errorToJson(ErrorCode.UPDATE_FAILED);
            }
        } else if (essence) {
            if (!(campAttend.getRole() == 1|| campAttend.getRole() == 2 || campAttend.getRole() == 3)) {
                return EnumUtil.errorToJson(ErrorCode.NO_AUTH_ERROR);
            }
            topic.essence(true);
            topic.updated(new Timestamp(System.currentTimeMillis()));
            int i = this.topicService.update(topic);
            if (i <= 0) {
                return EnumUtil.errorToJson(ErrorCode.UPDATE_FAILED);
            }
        } else if (top) {
            if (!(campAttend.getRole() == 1|| campAttend.getRole() == 2 || campAttend.getRole() == 3)) {
                return EnumUtil.errorToJson(ErrorCode.NO_AUTH_ERROR);
            }
            topic.top(true);
            topic.updated(new Timestamp(System.currentTimeMillis()));
            int i = this.topicService.update(topic);
            if (i <= 0) {
                return EnumUtil.errorToJson(ErrorCode.UPDATE_FAILED);
            }
        } else {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("status", 200);
        result.put("message", "成功");
        data.put("groupId", topic.getGroupId());
        result.put("data", data);
        return result;
    }

    private String getMembers(String member, long campId, UserName userList) {
        if (StringUtil.isNull(member)) {
            String members = "";
            List<User> teachers = this.userService.findUserByCampId(campId);
            int count = 0;
            for (User teacher : teachers) {
                if (count==0) {
                    members += teacher.getPhone();
                    userList.add(teacher.getPhone());
                    count++;
                } else {
                    members += ","+teacher.getPhone();
                    userList.add(teacher.getPhone());
                    count++;
                }
            }
            return members;
        } else {
            String members = "";
            String[] phones = member.trim().split(",");
            for (int i = 0; i < phones.length; i++) {
                if (i==0) {
                    members += phones[i];
                    userList.add(phones[i]);
                } else {
                    members += "," + phones[i];
                    userList.add(phones[i]);
                }
            }
            return members;
        }
    }
}
