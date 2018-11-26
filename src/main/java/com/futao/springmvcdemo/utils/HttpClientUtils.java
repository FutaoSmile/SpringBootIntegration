package com.futao.springmvcdemo.utils;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * @author futao
 * Created on 2018/11/14.
 */
public class HttpClientUtils {

    private static String ip = "";
    private static int port = 0;
    private static int maxTotal = 10;
    private static int maxPerRoute = 10;

    static {
        //配置信息
        RequestConfig config = RequestConfig
                //TODO("这几个时间是啥意思啊")
                .custom()
                //连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setConnectTimeout(5000)
                //读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setSocketTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();

        HttpHost host = new HttpHost(ip, port);
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        //客户端总并行链接最大数
        connectionManager.setMaxTotal(maxTotal);
        //每个主机的最大并行链接数
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);
        connectionManager.setMaxPerRoute(new HttpRoute(host), 20);
        HttpClientBuilder clientBuilder = HttpClients.custom();
        clientBuilder.setConnectionManager(connectionManager);
    }

}
