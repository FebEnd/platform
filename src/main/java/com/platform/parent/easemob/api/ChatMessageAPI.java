package com.platform.parent.easemob.api;

/**
 * Created by tqyao.
 */
public interface ChatMessageAPI {
    /**
     * 获取聊天记录下载链接
     * GET
     * @param timeStr 目标聊天记录时间
     * @return
     */
    Object exportChatMessages(String timeStr);
}
