package com.xy.elasticsearch.service;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class ElasticsearchManagement {
    private static TransportClient client = null;
    private void newClient() {
        try {
            Settings settings = Settings.builder()
                    //让客户端去嗅探整个集群的状态，把集群中其他机器的IP地址加到客户端中
                    //好处是不用手动设置集群里所有机器的IP到连接客户端，会自动添加，并且自动发现新加入集群的机器
                    .put("client.transport.sniff", true)
                    //如果集群名称不是“Elasticsearch”，必须设置集群名称
                    .put("cluster.name", "secilog")
                    .build();
            client = new PreBuiltTransportClient(settings).addTransportAddress(
                    //可以连接一个集群中的多个节点
                    new TransportAddress(InetAddress.getByName("192.168.9.181"), 9200));
        }catch (Exception e){
            if (client != null){
                client.close();
            }
        }
    }

    public TransportClient getClient(){
        if (client == null) {
            synchronized (this) {
                if (client == null){
                    newClient();
                }
            }
        }
        return client;
    }
}
