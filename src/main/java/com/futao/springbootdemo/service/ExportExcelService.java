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

    void export2File(String fileName, String sheetName, String[] columnHeads, List<List<Object>> data);

    /**
     * 导出excel
     *
     * @param fileName    文件名
     * @param sheetName   工作簿名
     * @param columnHeads 列头
     * @param data        数据
     * @param response    响应
     */
    void export(String fileName, String sheetName, String[] columnHeads, List<List<Object>> data, HttpServletResponse response);

    /**
     * 导出excel
     *
     * @param fileName    文件名
     * @param sheetName   sheetName
     * @param columnHeads 列头
     * @param methods     方法
     * @param objs        数据
     * @param response    响应
     */
    void export(String fileName, String sheetName, String[] columnHeads, Method[] methods, List<?> objs, HttpServletResponse response);
}
