package com.futao.springbootdemo.utils.http;

/**
 * DELETE请求
 *
 * @author futao
 * Created on 2019-01-16.
 */
public class DeleteRequest extends AbstractBaseRequest {
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
        return this.sendRequest(RequestMethod.DELETE, url);
    }

    public DeleteRequest(String url) {
        this.url = url;
    }
}
