package com.futao.springbootdemo.http.client;

import com.alibaba.fastjson.JSON;
import com.futao.springbootdemo.model.entity.User;
import com.futao.springbootdemo.model.enums.UserRoleEnum;
import com.futao.springbootdemo.model.enums.UserStatusEnum;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author futao
 * Created on 2019-01-16.
 */
public class HttpClientTest {

    private static final String REQUEST_URL = "http://localhost:8888/test/postObject?lang=en_US";


    /**
     * 简单请求，并获取响应状态码
     *
     * @throws IOException
     */
    private static void getResponseCode() throws IOException {
        //构造一个httpclient
        HttpClient httpClient = HttpClientBuilder.create().build();
        //执行请求，指定请求方式并且返回响应
        HttpResponse response = httpClient.execute(new HttpGet(REQUEST_URL));
        //获取响应状态码
        int code = response.getStatusLine().getStatusCode();
        System.out.println(code == HttpStatus.SC_OK);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        simpleConfigRequest();

    }

    /**
     * Note that the connection timeout will result in an org.apache.http.conn.ConnectTimeoutException being thrown,
     * while socket timeout will result in a java.net.SocketTimeoutException.
     */
    private static void simpleConfigRequest() throws IOException, URISyntaxException {
        //请求配置
        RequestConfig requestConfig = RequestConfig.custom()
                //时间如果设置为-1或者不设置(默认-1)则没有超时时间，详见默认配置
                //the Connection Manager Timeout (http.connection-manager.timeout) – the TIME to wait for a connection from the connection manager/pool
                //指从连接池获取到连接的超时时间，如果是非连接池的话，该参数暂时没有发现有什么用处
                .setConnectionRequestTimeout(1000)
                //the TIME to establish the connection with the remote host
                //与远程主机建立连接的超时时间
                .setConnectTimeout(1000)
                //the TIME waiting for data – after the connection was established; maximum TIME of inactivity between two data packets
                //等待数据的时间-在建立连接之后；两个数据包之间的最长非活动时间
                .setSocketTimeout(1000)
                .build();

        //请求头
        Collection<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));

        //请求参数
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("p1", "111"));
        parameters.add(new BasicNameValuePair("p2", "111"));
        URIBuilder uriBuilder = new URIBuilder(REQUEST_URL);
//        uriBuilder.addParameters(parameters);

        //构造一个httpclient设置并且设置请求配置
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultHeaders(headers)
                .setDefaultRequestConfig(requestConfig)
                .build();
        //构造http请求
        //HttpGet httpGet = new HttpGet(new URI("http", "localhost:8080", "statistic/apiList", ""));

        HttpPost request = new HttpPost(uriBuilder.build());
        CloseableHttpResponse response = httpClient.execute(request);
        System.out.println(response.getStatusLine().getStatusCode());
        //获取输出结果
        System.out.println(EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8")));
    }


}
