package com.xy.springbootdemo.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 *  STOMP监听类
 * 用于session注册 以及key值获取
 */
@Component
public class STOMPConnectEventListener implements ApplicationListener<SessionConnectEvent> {


    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        //login get from browser
        //前端放到connect时的header里
        String userId = sha.getNativeHeader("userId").get(0);
        String sessionId = sha.getSessionId();
        webAgentSessionRegistry.registerSessionId(userId,sessionId);
    }
}
