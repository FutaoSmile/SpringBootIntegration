package com.futao.springbootdemo.utils.http;

/**
 * PUT请求
 *
 * @author futao
 * Created on 2019-01-16.
 */
public class PutRequest extends AbstractBaseRequest {

    /**
     * 请求地址
     */
    private String url;

    /**
     * 发起请求
     *
     * @return 请求结果
     */
    @Override
    public String send() {
        return this.sendRequest(AbstractBaseRequest.RequestMethod.PUT, url);
    }

    /**
     * 添加httpEntity
     *
     * @param o entity
     */
    public void addEntity(Object o) {
        this.addHttpEntity(o);
    }

    public PutRequest(String url) {
        this.url = url;
    }
}
