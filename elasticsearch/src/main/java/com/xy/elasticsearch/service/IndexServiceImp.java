package com.xy.elasticsearch.service;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IndexServiceImp implements IndexService {
    @Autowired
    private ElasticsearchManagement elasticsearchManagement;

    @Override
    public void get(String id) {
        TransportClient client = elasticsearchManagement.getClient();

    }

    @Override
    public void update() {

    }

    @Override
    public void del(String id) {

    }

    @Override
    public void create(String id) {
        try {
            TransportClient client = elasticsearchManagement.getClient();
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("name", "jim" + id);
            jsonMap.put("age", 20 + id);
            jsonMap.put("date", new Date());
            jsonMap.put("message", "测试" + id);
            jsonMap.put("tel", "1234567");
            //IndexResponse indexResponse = client.getConnection().prepareIndex("twitter", "tweet").setSource(JSONObject.toJSON(jsonMap), XContentType.JSON).get();
            IndexResponse indexResponse = client.prepareIndex("xiaot", "test", id).setSource(jsonMap).get();
            // Index name
            String _index = indexResponse.getIndex();
            // Type name
            String _type = indexResponse.getType();
            // Document ID (generated or not)
            String _id = indexResponse.getId();
            // Version (if it's the first time you index this document, you will get: 1)
            long _version = indexResponse.getVersion();
            // status has stored current instance statement.
            RestStatus status = indexResponse.status();
            System.out.println(_index + "_" + _type + "_" + _id + "_" + _version + "_" + status);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
