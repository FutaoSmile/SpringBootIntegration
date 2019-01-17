package com.futao.springmvcdemo.utils.http;

import com.alibaba.fastjson.JSON;
import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.model.system.Constant;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import org.apache.http.*;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.futao.springmvcdemo.model.system.ErrorMessage.GEN_URI_FAIL;

/**
 * 请求基类
 * <p>
 * 基于教程-https://www.baeldung.com/httpclient-guide
 *
 * @author futao
 * Created on 2019-01-16.
 */
public abstract class AbstractBaseRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseRequest.class);

    /**
     * 请求头
     */
    private ArrayList<Header> headers = new ArrayList<>();

    /**
     * 请求参数
     */
    private List<NameValuePair> parameters = new ArrayList<>();

    /**
     * httpEntity
     */
    private Object entity;

    /**
     * 用户票据
     */
    private UsernamePasswordCredentials credentials;

    /**
     * cookies
     */
    private BasicCookieStore basicCookieStore = new BasicCookieStore();


    /**
     * @param name  参数{@link org.apache.http.HttpHeaders}   k
     * @param value v
     */
    public void addHeader(String name, String value) {
        this.headers.add(new BasicHeader(name, value));
    }

    /**
     * 添加权限信息
     *
     * @param userName 用户名
     * @param password 密码
     */
    public void addCredentials(String userName, String password) {
        this.credentials = new UsernamePasswordCredentials(userName, password);
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

    private String result;


    /**
     * 请求配置
     * Note that the connection timeout will result in an org.apache.http.conn.ConnectTimeoutException being thrown,
     * while socket timeout will result in a java.net.SocketTimeoutException.
     */
    private CloseableHttpClient setConfig() {
        //请求配置
        RequestConfig requestConfig = RequestConfig.custom()
                //时间如果设置为-1或者不设置(默认-1)则没有超时时间，详见默认配置
                //the Connection Manager Timeout (http.connection-manager.timeout) – the TIME to wait for a connection from the connection manager/pool
                //指从连接池获取到连接的超时时间，如果是非连接池的话，该参数暂时没有发现有什么用处
                .setConnectionRequestTimeout(Constant.RequestConst.CONNECTION_REQUEST_TIMEOUT)
                //the TIME to establish the connection with the remote host
                //与远程主机建立连接的超时时间
                .setConnectTimeout(Constant.RequestConst.CONNECT_TIMEOUT)
                //the TIME waiting for data – after the connection was established; maximum TIME of inactivity between two data packets
                //等待数据的时间-在建立连接之后；两个数据包之间的最长非活动时间
                .setSocketTimeout(Constant.RequestConst.SOCKET_TIMEOUT)
                .build();

        //请求头
        Collection<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE));


        //连接池
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(5);
        connectionManager.setDefaultMaxPerRoute(4);

        //keepAlive策略-管理死链接
        ConnectionKeepAliveStrategy keepAliveStrategy = (response, context) -> {
            HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && "timeout".equalsIgnoreCase(param)) {
                    return Long.parseLong(value) * 1000;
                }
            }
            return 5 * 1000;
        };

        //构造一个httpclient设置并且设置请求配置
        return HttpClientBuilder.create()
                //TODO("无效呢")
                .setDefaultHeaders(this.headers)
                .setDefaultCookieStore(this.basicCookieStore)
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(keepAliveStrategy)
                .build();
    }

    /**
     * 发起请求
     *
     * @param method 请求参数
     * @param url    请求地址
     * @return 响应
     */
    String sendRequest(RequestMethod method, String url) {

        //请求地址
        URIBuilder uriBuilder;
        try {
            uriBuilder = new URIBuilder(url);
        } catch (URISyntaxException e) {
            LOGGER.error(GEN_URI_FAIL + ":" + e.getMessage(), e);
            throw LogicException.le(GEN_URI_FAIL);
        }
        //请求参数
        if (this.parameters.size() > 0) {
            uriBuilder.addParameters(this.parameters);
        }
        //响应
        CloseableHttpResponse response = null;
        //请求
        HttpRequestBase request = null;

        switch (method) {
            //GET请求
            case GET:
                try {
                    request = new HttpGet(uriBuilder.build());
                    addHeader(request);
                    response = setConfig().execute(request);
                } catch (IOException | URISyntaxException e) {
                    LOGGER.error("请求发生异常:" + e.getMessage(), e);
                    throw LogicException.le(ErrorMessage.REQUEST_FAIL, new String[]{e.getMessage()});
                } finally {
                    requestLog(request, response);
                }
                break;
            //PUT请求
            case PUT:
                try {
                    request = new HttpPut(uriBuilder.build());
                    addHeader(request);
                    if (this.entity != null) {
                        ((HttpPut) request).setEntity(new ByteArrayEntity(JSON.toJSONBytes(this.entity)));
                    }
                    response = setConfig().execute(request);
                } catch (IOException | URISyntaxException e) {
                    LOGGER.error("请求发生异常:" + e.getMessage(), e);
                    throw LogicException.le(ErrorMessage.REQUEST_FAIL, new String[]{e.getMessage()});
                } finally {
                    requestLog(request, response);
                }
                break;
            //POST请求
            case POST:
                try {
                    request = new HttpPost(uriBuilder.build());
                    addHeader(request);
                    if (this.entity != null) {
                        ((HttpPost) request).setEntity(new ByteArrayEntity(JSON.toJSONBytes(this.entity)));
                    }
                    response = setConfig().execute(request);
                } catch (IOException | URISyntaxException e) {
                    LOGGER.error("请求发生异常:" + e.getMessage(), e);
                    throw LogicException.le(ErrorMessage.REQUEST_FAIL, new String[]{e.getMessage()});
                } finally {
                    requestLog(request, response);
                }
                break;
            //DELETE请求
            case DELETE:
                try {
                    request = new HttpDelete(uriBuilder.build());
                    addHeader(request);
                    response = setConfig().execute(request);
                } catch (IOException | URISyntaxException e) {
                    LOGGER.error("请求发生异常:" + e.getMessage(), e);
                    throw LogicException.le(ErrorMessage.REQUEST_FAIL, new String[]{e.getMessage()});
                } finally {
                    requestLog(request, response);
                    try {
                        if (response != null) {
                            response.close();
                        }
                    } catch (IOException e) {
                        LOGGER.error("关闭response失败:" + e.getMessage(), e);
                        throw LogicException.le(ErrorMessage.CLOSE_RESPONSE_FAIL, new String[]{e.getMessage()});
                    }
                }
                break;
            //不存在的请求方式
            default:
                throw LogicException.le(ErrorMessage.REQUEST_METHOD_NOT_EXISTS);
        }
        //判断请求是否成功200
        if (response != null && response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            LOGGER.error("请求失败:HttpStatus=" + response.getStatusLine().getStatusCode());
            throw LogicException.le(ErrorMessage.REQUEST_FAIL, new String[]{String.valueOf(response.getStatusLine().getStatusCode())});
        }
        //获取结果
        return result;
    }

    /**
     * 请求方式
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

    /**
     * 添加请求httpEntity
     *
     * @param o entity
     */
    void addHttpEntity(Object o) {
        this.entity = o;
    }

    /**
     * 发起请求
     *
     * @return 请求结果
     */
    public abstract String send();

    /**
     * 请求日志记录
     *
     * @param requestBase 请求
     */
    private void requestLog(HttpRequestBase requestBase, CloseableHttpResponse response) {
        StringBuilder sb = new StringBuilder();
        if (requestBase != null) {
            //记录请求地址
            sb.append("请求地址:").append(requestBase.getURI()).append("\n")
                    .append("请求头:").append(Arrays.toString(requestBase.getAllHeaders())).append("\n")
                    .append("请求方式:").append(requestBase.getMethod()).append("\n")
                    .append("请求体:").append(this.entity).append("\n")
                    .append("请求参数:").append(this.parameters).append("\n");
        }
        if (response != null) {
            try {
                result = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
                sb.append("响应状态码:").append(response.getStatusLine().getStatusCode()).append("\n")
                        .append("响应结果:").append(result).append("\n");
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
                throw LogicException.le(ErrorMessage.GET_RESPONSE_FAIL, new String[]{e.getMessage()});
            }
        }
        LOGGER.info(sb.toString());
    }

    /**
     * 添加请求头
     *
     * @param request 请求
     */
    private void addHeader(HttpRequestBase request) {
        //请求头
        if (headers.size() > 0) {
            headers.forEach(request::addHeader);
        }
        //request authentication
        if (credentials != null) {
            try {
                request.addHeader(new BasicScheme().authenticate(credentials, request, null));
            } catch (AuthenticationException e) {
                LOGGER.error("request authentication fail: " + e.getMessage(), e);
                throw LogicException.le(ErrorMessage.ADD_AUTH_HEAD_FAIL, new String[]{e.getMessage()});
            }
        }
    }

    /**
     * 添加cookie
     *
     * @param cookie cookie
     */
    public void addCookie(BasicClientCookie cookie) {
        this.basicCookieStore.addCookie(cookie);
        StringBuilder sb = new StringBuilder();
        sb.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
        if (cookie.getPath() != null) {
            sb.append("path=").append(cookie.getPath()).append(";");
        }
        if (cookie.getDomain() != null) {
            sb.append("domain=").append(cookie.getDomain()).append(";");
        }
        headers.add(new BasicHeader("Cookie", sb.toString()));
    }
}
