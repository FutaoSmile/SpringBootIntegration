package com.futao.springbootdemo.apigen.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.futao.springbootdemo.apigen.model.ApiDesc;
import com.futao.springbootdemo.apigen.model.ApiMethod;
import com.futao.springbootdemo.utils.DateTools;
import com.futao.springbootdemo.utils.http.AbstractBaseRequest;
import com.futao.springbootdemo.utils.http.GetRequest;

import java.util.ArrayList;

/**
 * @author futao
 * Created on 2019-04-28.
 */
public class GenApiDesc {
    public static void main(String[] args) {
        AbstractBaseRequest request = new GetRequest("http://localhost:8888/v2/api-docs");
        String result = request.send();
        JSONObject resultJson = JSON.parseObject(result);

        ApiDesc apiDesc = new ApiDesc();


        loadInfo(resultJson, apiDesc);
        loadPaths(resultJson, apiDesc);
        System.out.println(JSON.toJSONString(apiDesc));

    }

    /**
     * 加载基本配置信息
     *
     * @param jsonObject
     * @param apiDesc
     */
    private static void loadInfo(JSONObject jsonObject, ApiDesc apiDesc) {
        JSONObject info = jsonObject.getJSONObject("info");
        apiDesc.setDesc(info.getString("description"));
        apiDesc.setVersion(info.getString("version"));
        apiDesc.setTitle(info.getString("title"));
        apiDesc.setCreateTime(DateTools.currentTimeStamp());
        apiDesc.setBaseUrl(jsonObject.getString("host") + jsonObject.getString("basePath"));
    }

    private static void loadPaths(JSONObject jsonObject, ApiDesc apiDesc) {
        JSONObject paths = jsonObject.getJSONObject("paths");
        ArrayList<ApiMethod> methodArrayList = new ArrayList<>(paths.size());

        paths.forEach((path, obj) -> {

        });
    }
}
