package com.platform.parent.component;

import com.platform.parent.easemob.api.impl.EasemobChatGroup;
import com.platform.parent.mybatis.bean.Topic;
import com.platform.parent.mybatis.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tqyao.
 */
@Component
public class ClearTopicTask {
    static final Logger logger = LoggerFactory.getLogger(ClearTopicTask.class);
    static EasemobChatGroup chatGroup = new EasemobChatGroup();
    @Autowired
    TopicService topicService;

    @Scheduled(fixedRate = 24*3600*1000)
    public void clearTopicTask() {
        List<Topic> topics = this.topicService.findTopicsOverSevenDays();
        logger.info("Start clear topics created over 7 days");
        for (Topic topic : topics) {
            chatGroup.deleteChatGroup(topic.getGroupId());
        }
        this.topicService.clearTopicsOverSevenDays();
        logger.info("Finish clear topics created over 7 days");
    }
}
