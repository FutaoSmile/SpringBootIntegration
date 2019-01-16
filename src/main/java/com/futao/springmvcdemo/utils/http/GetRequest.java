package com.futao.springmvcdemo.utils.http;

/**
 * @author futao
 * Created on 2019-01-16.
 */
public class GetRequest extends BaseRequest {
    public void request(String url) {
        this.request_(BaseRequest.RequestMethod.GET, url);
    }
}
