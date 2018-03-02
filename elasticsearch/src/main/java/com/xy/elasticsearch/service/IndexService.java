package com.xy.elasticsearch.service;

public interface IndexService {
    void get(String id);
    void update();
    void del(String id);
    void create(String id);
}
