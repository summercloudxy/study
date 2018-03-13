package com.xy.elasticsearch.service;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class IndexServiceImp implements IndexService {
    @Autowired
    private ElasticsearchManagement elasticsearchManagement;

    @Override
    public String get(String index, String type, String id) {
        TransportClient client = elasticsearchManagement.getClient();
        GetResponse getResponse = client.prepareGet(index, type, id).get();
        return getResponse.getSourceAsString();
    }


    @Override
    public void del(String index, String type, String id) {
        TransportClient client = elasticsearchManagement.getClient();
        DeleteResponse deleteResponse = client.prepareDelete(index, type, id).get();
        RestStatus status = deleteResponse.status();

    }

    /**
     * 新建一个文档，如果该文档存在，则进行更新
     *
     * @param index
     * @param type
     * @param id
     * @param context
     */
    @Override
    public void create(String index, String type, String id, String context) {
        try {
            TransportClient client = elasticsearchManagement.getClient();
//            Map<String, Object> jsonMap = new HashMap<String, Object>();
//            jsonMap.put("name", "jim" + id);
//            jsonMap.put("age", 20 + id);
//            jsonMap.put("date", new Date());
//            jsonMap.put("message", "测试" + id);
//            jsonMap.put("tel", "1234567");
            //可以直接传一个Json字符串
            IndexResponse indexResponse = client.prepareIndex(index, type, id).setSource(context, XContentType.JSON).get();
            //也可以传一个map，key为field
//            IndexResponse indexResponse = client.prepareIndex("xiaot", "test", id).setSource(jsonMap).get();
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
            System.out.println("index:" + _index + ",type:" + _type + ",id:" + _id + ",version:" + _version + ",status:" + status);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void update(String index, String type, String id, String context) {
        try {
            TransportClient client = elasticsearchManagement.getClient();
            //第一种：只更新，如果文档不存在会抛异常
//            UpdateRequest updateRequest = new UpdateRequest();
//            updateRequest.index(index).type(type).id(id).doc(context,XContentType.JSON);

            //第二种：如果不存在新增，存在更新
            IndexRequest indexRequest = new IndexRequest();
            indexRequest.index(index).type(type).id(id).source(context, XContentType.JSON);
            UpdateRequest updateRequest = new UpdateRequest(index, type, id).doc(context, XContentType.JSON).upsert(indexRequest);

            UpdateResponse updateResponse = client.update(updateRequest).get();
            String _index = updateResponse.getIndex();
            String _type = updateResponse.getType();
            String _id = updateResponse.getId();
            long _version = updateResponse.getVersion();
            RestStatus status = updateResponse.status();
            System.out.println("index:" + _index + ",type:" + _type + ",id:" + _id + ",version:" + _version + ",status:" + status);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
