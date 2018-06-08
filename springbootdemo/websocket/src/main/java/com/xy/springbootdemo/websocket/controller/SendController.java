package com.xy.springbootdemo.websocket.controller;

import com.xy.springbootdemo.websocket.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {
    @Autowired
    private SendService sendService;

    @GetMapping("send")
    public void sendToUser(String userId,String message){
        sendService.sendToUser(userId,message);
    }
}
