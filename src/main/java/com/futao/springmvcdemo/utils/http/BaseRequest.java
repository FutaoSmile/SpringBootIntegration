package com.futao.springmvcdemo.utils.http;

import com.alibaba.fastjson.JSON;
import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.futao.springmvcdemo.model.system.ErrorMessage.GEN_URI_FAIL;

/**
 * @author futao
 * Created on 2019-01-16.
 */
public class BaseRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseRequest.class);

    private int connectionRequestTimeout = 1000;
    private int connectTimeout = 1000;
    private int socketTimeout = 1000;


    /**
     * 请求头
     */
    private Collection<Header> headers = new ArrayList<>();
    /**
     * 请求参数
     */
    private List<NameValuePair> parameters = new ArrayList<>();
    private Object entity;


    /**
     * @param name  参数{@link org.apache.http.HttpHeaders}   k
     * @param value v
     */
    public void addHeader(String name, String value) {
        headers.add(new BasicHeader(name, value));
    }

    /**
     * 添加请求参数
     *
     * @param name  k
     * @param value v
     */
    public void addParameter(String name, String value) {
        parameters.add(new BasicNameValuePair(name, value));
    }


    /**
     * Note that the connection timeout will result in an org.apache.http.conn.ConnectTimeoutException being thrown,
     * while socket timeout will result in a java.net.SocketTimeoutException.
     *
     * @throws URISyntaxException
     */

    private CloseableHttpClient request() {
        //请求配置
        RequestConfig requestConfig = RequestConfig.custom()
                //时间如果设置为-1或者不设置(默认-1)则没有超时时间，详见默认配置
                //the Connection Manager Timeout (http.connection-manager.timeout) – the time to wait for a connection from the connection manager/pool
                //指从连接池获取到连接的超时时间，如果是非连接池的话，该参数暂时没有发现有什么用处
                .setConnectionRequestTimeout(this.connectionRequestTimeout)
                //the time to establish the connection with the remote host
                //与远程主机建立连接的超时时间
                .setConnectTimeout(this.connectTimeout)
                //the time waiting for data – after the connection was established; maximum time of inactivity between two data packets
                //等待数据的时间-在建立连接之后；两个数据包之间的最长非活动时间
                .setSocketTimeout(this.socketTimeout)
                .build();

        //请求头
        Collection<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));


        //构造一个httpclient设置并且设置请求配置
        return HttpClientBuilder.create()
                .setDefaultHeaders(headers)
                .setDefaultRequestConfig(requestConfig)
                .build();

    }

    void request_(RequestMethod method, String url) {

        //请求参数
        URIBuilder uriBuilder;
        try {
            uriBuilder = new URIBuilder(url);
        } catch (URISyntaxException e) {
            LOGGER.error(GEN_URI_FAIL + ":" + e.getMessage(), e);
            throw LogicException.le(GEN_URI_FAIL);
        }
        if (this.parameters.size() > 0) {
            uriBuilder.addParameters(this.parameters);
        }
        CloseableHttpResponse response;
        switch (method) {
            case GET:
                try {
                    response = request().execute(new HttpGet(uriBuilder.build()));
                } catch (IOException | URISyntaxException e) {
                    LOGGER.error("请求发生异常:" + e.getMessage(), e);
                    throw LogicException.le(ErrorMessage.REQUEST_FAIL, new String[]{e.getMessage()});
                }
                break;
            case PUT:
                try {
                    HttpPut httpPut = new HttpPut(uriBuilder.build());
                    if (this.entity != null) {
                        httpPut.setEntity(new ByteArrayEntity(JSON.toJSONBytes(this.entity)));
                    }
                    response = request().execute(httpPut);
                } catch (IOException | URISyntaxException e) {
                    LOGGER.error("请求发生异常:" + e.getMessage(), e);
                    throw LogicException.le(ErrorMessage.REQUEST_FAIL, new String[]{e.getMessage()});
                }
                break;
            case POST:
                try {
                    HttpPost httpPost = new HttpPost(uriBuilder.build());
                    if (this.entity != null) {
                        httpPost.setEntity(new ByteArrayEntity(JSON.toJSONBytes(this.entity)));
                    }
                    response = request().execute(httpPost);
                } catch (IOException | URISyntaxException e) {
                    LOGGER.error("请求发生异常:" + e.getMessage(), e);
                    throw LogicException.le(ErrorMessage.REQUEST_FAIL, new String[]{e.getMessage()});
                }
                break;
            case DELETE:
                try {
                    response = request().execute(new HttpDelete(uriBuilder.build()));
                } catch (IOException | URISyntaxException e) {
                    LOGGER.error("请求发生异常:" + e.getMessage(), e);
                    throw LogicException.le(ErrorMessage.REQUEST_FAIL, new String[]{e.getMessage()});
                }
                break;
            default:
                throw LogicException.le(ErrorMessage.REQUEST_METHOD_NOT_EXISTS);
        }

        System.out.println(response.getStatusLine().getStatusCode());
        //获取输出结果
        try {
            System.out.println(EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    enum RequestMethod {
        /**
         * get
         */
        GET,
        /**
         * put
         */
        PUT,
        /**
         * post
         */
        POST,
        /**
         * delete
         */
        DELETE
    }

    void addHttpEntity(Object o) {
        this.entity = o;
    }
}
