package com.futao.springbootdemo.service;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 导出excel
 *
 * @author futao
 * Created on 2019-01-30.
 */
public interface ExportExcelService {

    void export2File(String filePath, String sheetName, String[] columnHeads, List<List<Object>> data);

    void export2Response(String fileName, String sheetName, String[] columnHeads, List<List<Object>> data, HttpServletResponse response);

    void exportByGetter2Response(String fileName, String sheetName, String[] columnHeads, Method[] methods, List<?> objs, HttpServletResponse response);

    void exportByGetter2File(String filePath, String sheetName, String[] columnHeads, Method[] methods, List<?> objs);
}
