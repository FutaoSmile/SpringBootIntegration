package com.futao.springmvcdemo.utils

import com.futao.springmvcdemo.model.system.UTF8_ENCODE
import org.apache.http.HttpStatus
import org.apache.http.NameValuePair
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils

/**
 * @author futao
 * Created on 2018/11/9.
 * @see <a href="https://blog.csdn.net/zhuwukai/article/details/78644484">https://blog.csdn.net/zhuwukai/article/details/78644484</a>
 */
class HttpRequestUtils {
    fun request() {
        val builder = HttpClientBuilder.create().build()

        //配置信息
        val config = RequestConfig
                //TODO("这几个时间是啥意思啊")
                .custom()
                //连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setConnectTimeout(5000)
                //读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setSocketTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build()

        //构造请求参数
        val list = ArrayList<NameValuePair>()
        list.add(BasicNameValuePair("mobile", "admin"))
        list.add(BasicNameValuePair("password", "123456789"))
        val requestEntity = UrlEncodedFormEntity(list, UTF8_ENCODE)


        //构造POST请求
        val httpPost = HttpPost("http://localhost:8888/user/login")
        httpPost.entity = requestEntity
        httpPost.config = config

        //发起请求
        val response = builder.execute(httpPost)

        val statusCode = response.statusLine.statusCode
        if (HttpStatus.SC_OK == statusCode) {
            val responseEntity = response.entity
            println("请求成功: ${EntityUtils.toString(responseEntity, UTF8_ENCODE)}")
        } else {
            println("请求失败: code:$statusCode")
        }
    }
}