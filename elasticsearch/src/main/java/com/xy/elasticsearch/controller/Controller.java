package com.xy.elasticsearch.controller;

import com.xy.elasticsearch.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    IndexService indexService;


    public void create(String id){
        indexService.create(id);
    }
}
