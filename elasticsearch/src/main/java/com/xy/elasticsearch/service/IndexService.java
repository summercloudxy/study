package com.xy.elasticsearch.service;

import org.springframework.stereotype.Service;

@Service
public interface IndexService {
    String get(String index,String type,String id);
    void update(String index,String type,String id,String context);
    void del(String index,String type,String id);
    void create(String index,String type,String id,String context);
}
