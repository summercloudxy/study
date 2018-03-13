package com.xy.elasticsearch.controller;

import com.xy.elasticsearch.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class Controller {
    @Autowired
    IndexService indexService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public void create(@RequestParam String index, @RequestParam String type, @RequestParam String id, @RequestBody String context){
        indexService.create(index, type, id, context);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(@RequestParam String index, @RequestParam String type, @RequestParam String id, @RequestBody String context){
        indexService.update(index, type, id, context);
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String get(@RequestParam String index, @RequestParam String type, @RequestParam String id){
        return indexService.get(index, type, id);
    }


}
