package com.futao.springbootdemo.service.notbusiness;

import com.futao.springbootdemo.model.system.Constant;
import com.futao.springbootdemo.model.system.ErrorMessage;
import com.futao.springbootdemo.service.ExportExcelService;
import com.futao.springbootdemo.utils.ServiceTools;
import com.lazyer.foundation.foundation.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
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


    /**
     * 导出excel到文件
     *
     * @param fileName    文件名
     * @param sheetName   sheetName
     * @param columnHeads 列头
     * @param data        数据
     */
    @Override
    public void export2File(String fileName, String sheetName, String[] columnHeads, List<List<Object>> data) {
        SXSSFWorkbook workbook = genExcel(sheetName, columnHeads, data);
        try {
            workbook.write(FileUtils.openOutputStream(new File(fileName + ".xlsx")));
        } catch (IOException e) {
            log.error("导出excel失败", e);
            throw ApplicationException.ae(ErrorMessage.ApplicationErrorMessage.EXPORT_EXCEL_FAIL);
        }
        log.info("导出成功...");
    }

    @Override
    public void export(String fileName, String sheetName, String[] columnHeads, List<List<Object>> data, HttpServletResponse response) {

        //设置响应头
        response.setCharacterEncoding(Constant.UTF8_ENCODE);
        response.setContentType("application/vnd.ms-excel");

        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", Constant.UTF8_ENCODE));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        SXSSFWorkbook workbook = genExcel(sheetName, columnHeads, data);
        //创建一个输出流
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            LOGGER.error("导出excel异常:{}", e.getMessage(), e);
            throw ApplicationException.ae(ErrorMessage.ApplicationErrorMessage.EXPORT_EXCEL_FAIL);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    LOGGER.error("关闭输出流异常:{}", e.getMessage(), e);
                    throw ApplicationException.ae(ErrorMessage.ApplicationErrorMessage.CLOSE_OUTPUT_STREAM_FAIL);
                }
            }
            try {
                workbook.close();
            } catch (IOException e) {
                LOGGER.error("关闭输出流异常:{}", e.getMessage(), e);
                throw ApplicationException.ae(ErrorMessage.ApplicationErrorMessage.CLOSE_OUTPUT_STREAM_FAIL);
            }
        }
    }


    @Override
    public void export(String fileName, String sheetName, String[] columnHeads, Method[] methods, List<?> objs, HttpServletResponse response) {
        int length = methods.length;
        if (columnHeads == null) {
            columnHeads = new String[methods.length];
            for (int i = 0; i < methods.length; i++) {
                columnHeads[i] = ServiceTools.getFieldName(methods[i]);
            }
        }
        List<List<Object>> dataList = new ArrayList<>();
        objs.forEach(obj -> {
            ArrayList<Object> rowData = new ArrayList<>();
                    for (int i = 0; i < methods.length; i++) {
                        try {
                            rowData.set(i, methods[i].invoke(obj));
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            LOGGER.error("执行方法异常");
                            throw ApplicationException.ae(ErrorMessage.ApplicationErrorMessage.INVOKE_METHOD_FAIL);
                        }
                    }
                    dataList.add(rowData);
                }
        );
        export(fileName, sheetName, columnHeads, dataList, response);
    }


    /**
     * 将数据写入Excel
     *
     * @param sheetName   sheetName
     * @param columnHeads 列头
     * @param data        数据
     * @return
     */
    private SXSSFWorkbook genExcel(String sheetName, String[] columnHeads, List<List<Object>> data) {
        //创建poi导出数据对象
        SXSSFWorkbook workbook = new SXSSFWorkbook();

        //创建sheet页
        SXSSFSheet sheet = workbook.createSheet(sheetName);

        //创建表头
        SXSSFRow headRow = sheet.createRow(0);
        if (columnHeads != null && columnHeads.length >= 1) {
            for (int i = 0; i < columnHeads.length; i++) {
                headRow.createCell(i).setCellValue(columnHeads[i]);
            }
        }

        data.forEach(objArray -> {
            SXSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            for (int i = 0; i < objArray.size(); i++) {
                dataRow.createCell(i).setCellValue(String.valueOf(objArray.get(i)));
            }
        });
        return workbook;
    }
}
