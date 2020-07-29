package com.futao.springbootdemo.service.notbusiness;

import com.alibaba.fastjson.JSONArray;
import com.futao.springbootdemo.service.ReadExcelService;
import com.lazyer.tools.excel.ExcelReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Positive;

/**
 * 读取excel
 *
 * @author futao
 * Created on 2019-05-08.
 */
@Slf4j
@Service
public class ReadExcelServiceImpl implements ReadExcelService {

    /**
     * 读取excel
     *
     * @param fileName         文件名
     * @param skipHeader       忽略开头几行
     * @param headerDefinition 头定义
     * @return JSON数组
     */
    @Override
    public JSONArray read(
            String fileName,
            int skipHeader,
            @Positive(message = "您必须定义头信息，它们将作为JSON结果集的key")
                    int headerDefinition,
            @Positive(message = "sheetNum页码从1开始，请确认您的输入")
                    int sheetNum
    ) {
        return ExcelReader.read(fileName, skipHeader, headerDefinition, sheetNum);
    }

}
