package com.futao.springmvcdemo.utils.http;

/**
 * GET请求
 *
 * @author futao
 * Created on 2019-01-16.
 */
public class GetRequest extends AbstractBaseRequest {
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
        return this.sendRequest(AbstractBaseRequest.RequestMethod.GET, url);
    }

    public GetRequest(String url) {
        this.url = url;
    }

}
