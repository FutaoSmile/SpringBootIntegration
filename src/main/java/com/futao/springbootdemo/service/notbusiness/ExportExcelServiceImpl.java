package com.futao.springbootdemo.service.notbusiness;

import com.futao.springbootdemo.foundation.ApplicationException;
import com.futao.springbootdemo.model.system.Constant;
import com.futao.springbootdemo.model.system.ErrorMessage;
import com.futao.springbootdemo.service.ExportExcelService;
import com.futao.springbootdemo.utils.ServiceTools;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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
@Service
public class ExportExcelServiceImpl implements ExportExcelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportExcelServiceImpl.class);

    @Override
    public void export(String fileName, String sheetName, String[] columnHeads, List<Object[]> data, HttpServletResponse response) {
        //创建poi导出数据对象
        SXSSFWorkbook workbook = new SXSSFWorkbook();

        //创建sheet页
        SXSSFSheet sheet = workbook.createSheet(sheetName);


        //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
//        CellRangeAddress regin = new CellRangeAddress(0, 1, (short) 0, (short) 12);
//        sheet.addMergedRegion(regin);

//        SXSSFRow headTitle = sheet.createRow(0);
//        headTitle.createCell(0).setCellValue("重点项目计划表");

        //创建表头
        SXSSFRow headRow = sheet.createRow(0);
        if (columnHeads != null && columnHeads.length >= 1) {
            for (int i = 0; i < columnHeads.length; i++) {
                headRow.createCell(i).setCellValue(columnHeads[i]);
            }
        }

        data.forEach(objArray -> {
            SXSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            for (int i = 0; i < objArray.length; i++) {
                dataRow.createCell(i).setCellValue(String.valueOf(objArray[i]));
            }
        });

        //设置响应头
        response.setCharacterEncoding(Constant.UTF8_ENCODE);
        response.setContentType("application/vnd.ms-excel");

        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", Constant.UTF8_ENCODE));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //创建一个输出流
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            LOGGER.error("导出excel异常:" + e.getMessage(), e);
            throw ApplicationException.le(ErrorMessage.ApplicationErrorMessage.EXPORT_EXCEL_FAIL);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    LOGGER.error("关闭输出流异常:" + e.getMessage(), e);
                    throw ApplicationException.le(ErrorMessage.ApplicationErrorMessage.CLOSE_OUTPUT_STREAM_FAIL);
                }
            }
            try {
                workbook.close();
            } catch (IOException e) {
                LOGGER.error("关闭输出流异常:" + e.getMessage(), e);
                throw ApplicationException.le(ErrorMessage.ApplicationErrorMessage.CLOSE_OUTPUT_STREAM_FAIL);
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
        List<Object[]> dataList = new ArrayList<>();
        objs.forEach(obj -> {
                    Object[] rowData = new Object[length];
                    for (int i = 0; i < methods.length; i++) {
                        try {
                            rowData[i] = methods[i].invoke(obj);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            LOGGER.error("执行方法异常");
                            throw ApplicationException.le(ErrorMessage.ApplicationErrorMessage.INVOKE_METHOD_FAIL);
                        }
                    }
                    dataList.add(rowData);
                }
        );
        export(fileName, sheetName, columnHeads, dataList, response);
    }
}
