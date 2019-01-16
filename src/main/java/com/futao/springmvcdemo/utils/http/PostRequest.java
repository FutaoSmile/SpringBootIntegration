package com.futao.springmvcdemo.utils.http;

/**
 * @author futao
 * Created on 2019-01-16.
 */
public class PostRequest extends BaseRequest {
    public void request(String url) {
        this.request_(RequestMethod.POST, url);
    }

    public void addEntity(Object o) {
        this.addHttpEntity(o);
    }
}
