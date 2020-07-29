package com.futao.springbootdemo.service.notbusiness;

import com.futao.springbootdemo.service.ExportExcelService;
import com.lazyer.tools.excel.ExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 导出excel
 *
 * @author futao
 * Created on 2019-01-30.
 */
@Slf4j
@Service
public class ExportExcelServiceImpl implements ExportExcelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportExcelServiceImpl.class);

    @Override
    public void export2File(String filePath, String sheetName, String[] columnHeads, List<List<Object>> data) {
        ExcelWriter.export2File(filePath, sheetName, columnHeads, data);
    }

    @Override
    public void export2Response(String fileName, String sheetName, String[] columnHeads, List<List<Object>> data, HttpServletResponse response) {
        ExcelWriter.export2Response(fileName, sheetName, columnHeads, data, response);
    }

    @Override
    public void exportByGetter2Response(String fileName, String sheetName, String[] columnHeads, Method[] methods, List<?> objs, HttpServletResponse response) {
        ExcelWriter.exportByGetter2Response(fileName, sheetName, columnHeads, methods, objs, response);
    }

    @Override
    public void exportByGetter2File(String filePath, String sheetName, String[] columnHeads, Method[] methods, List<?> objs) {
        ExcelWriter.exportByGetter2File(filePath, sheetName, columnHeads, methods, objs);
    }

}
