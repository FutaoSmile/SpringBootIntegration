package com.futao.springbootdemo.utils.http;

/**
 * POST请求
 *
 * @author futao
 * Created on 2019-01-16.
 */
public class PostRequest extends AbstractBaseRequest {
    /**
     * 请求地址
     */
    private String url;

    /**
     * 发起请求
     */
    @Override
    public String send() {
        return this.sendRequest(RequestMethod.POST, url);
    }

    /**
     * 添加body请求参数
     *
     * @param o 请求参数
     */
    public void addEntity(Object o) {
        this.addHttpEntity(o);
    }

    public PostRequest(String url) {
        this.url = url;
    }
}
