package com.xy.springbootdemo.websocket.service;

import com.xy.springbootdemo.websocket.config.SocketSessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SendService {

    @Autowired
    private SocketSessionRegistry socketSessionRegistry;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private String sendToUserUri = "/test";


    /**
     * 发送给指定用户
     * 这里发送的uri为 "/test" ，前端注册时需要带发送给用户的前缀，即 "/user/test"   这个前缀是在WebSocketConfiguration#configureMessageBroker中定义的
     *
     * @param userId
     */
    public void sendToUser(String userId, String message) {

        Set<String> sessionIds = socketSessionRegistry.getSessionIds(userId);
        for (String sessionId : sessionIds) {
            messagingTemplate.convertAndSendToUser(sessionId, sendToUserUri, message, createHeaders(sessionId));
        }
    }

    /**
     * sendToUser时必须带这个参数，否则发送不成功
     *
     * @param sessionId
     * @return
     */
    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
