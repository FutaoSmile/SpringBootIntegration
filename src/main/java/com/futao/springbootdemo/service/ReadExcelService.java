package com.futao.springbootdemo.service;

import com.alibaba.fastjson.JSONArray;

/**
 * @author futao
 * Created on 2019-05-08.
 */
public interface ReadExcelService {
    /**
     * 读取excel
     *
     * @param fileName         文件名
     * @param skipHeader       忽略开头几行
     * @param headerDefinition 头定义
     * @return JSON数组
     */
    JSONArray read(String fileName, int skipHeader, int headerDefinition);
}
