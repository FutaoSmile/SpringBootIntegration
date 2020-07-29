package com.futao.springbootdemo.service;

import com.alibaba.fastjson.JSONArray;

import javax.validation.constraints.Positive;

/**
 * @author futao
 * Created on 2019-05-08.
 */
public interface ReadExcelService {

    JSONArray read(
            String fileName,
            int skipHeader,
            @Positive(message = "您必须定义头信息，它们将作为JSON结果集的key")
                    int headerDefinition,
            @Positive(message = "sheetNum页码从1开始，请确认您的输入")
                    int sheetNum
    );
}
