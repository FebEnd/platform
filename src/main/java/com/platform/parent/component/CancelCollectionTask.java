package com.platform.parent.component;

import com.platform.parent.mybatis.bean.TopicCollection;
import com.platform.parent.mybatis.service.TopicCollectionService;
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
public class CancelCollectionTask {
    static final Logger logger = LoggerFactory.getLogger(CancelCollectionTask.class);
    @Autowired
    TopicCollectionService topicCollectionService;

    @Scheduled(fixedRate = 24*3600*1000)
    public void cancelCollectionTask(){
        List<TopicCollection> collections = this.topicCollectionService.findTopicCollectionByLine();
        String[] ids = new String[collections.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = collections.get(i).getId()+"";
        }
        logger.info("Cancel collection of topic over 7 days");
        this.topicCollectionService.deleteByIds(ids);
    }
}
