package com.futao.springbootdemo.service.notbusiness;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.futao.springbootdemo.foundation.ApplicationException;
import com.futao.springbootdemo.model.system.ErrorMessage;
import com.futao.springbootdemo.service.ReadExcelService;
import com.futao.springbootdemo.utils.DateTools;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 读取excel
 *
 * @author futao
 * Created on 2019-05-08.
 */
@Slf4j
@Service
public class ReadExcelServiceImpl implements ReadExcelService {

    private static final String SAMPLE_XLSX_FILE_PATH = "./user data.xlsx";

    /**
     * 读取excel
     *
     * @param fileName         文件名
     * @param skipHeader       忽略开头几行
     * @param headerDefinition 头定义
     * @return JSON数组
     */
    @Override
    public JSONArray read(String fileName, int skipHeader, int headerDefinition) {
        //开始时间
        long startTime = System.currentTimeMillis();
        //读取的row数量
        AtomicInteger readRowCount = new AtomicInteger();
        //空行数
        AtomicInteger blankRowCount = new AtomicInteger();
        //TODO 分页查询
        int start = 0;
        int limit = 0;
        //当前excel
        JSONArray result;
        try (Workbook workbook = WorkbookFactory.create(new File(fileName))) {
            log.info(">>> 当前excel共有: {}页", workbook.getNumberOfSheets());
            //遍历excel的每一页
//            workbook.forEach(sheet -> {
            //当前只读取第一页数据
            Sheet sheet = workbook.getSheetAt(0);
            //头信息
            String[] headerDefinitions = getHeaderDefinition(sheet.getRow(headerDefinition - 1));
            log.info("sheetName:{}", sheet.getSheetName());
            //当前页数据量大小
            int lastRowNum = sheet.getLastRowNum();
            log.info("当前页数据量大小为: {}条", lastRowNum + 1);
            //定义结果存放JSON数组
            result = new JSONArray(lastRowNum + 1);
            for (int i = 0; i < lastRowNum + 1; i++) {
                //忽略header
                if (i < skipHeader) {
                    continue;
                }
                //当前row
                Row row = sheet.getRow(i);
                //当前row为空行，则继续下一次循环
                if (row == null) {
                    blankRowCount.getAndIncrement();
                    continue;
                }
                //当前row的列数
                short lastCellNum = row.getLastCellNum();
                log.info("当前row的列数为: {}条", lastCellNum);
                //当前Row的结果
                JSONObject rowResult = new JSONObject(lastCellNum + 1);
                //将当前结果加入到最终的JSONArray中
                result.add(rowResult);
                for (int j = 0; j < lastCellNum; j++) {
                    //当前单元格数据
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        continue;
                    }
                    String cellValue = printCellValue(cell, rowResult, headerDefinitions[j]);
                    log.info("当前单元格数据(当前row第{}条数据)：{}", j + 1, cellValue);
                }
                readRowCount.getAndIncrement();
            }
//            });
            log.info(">>> 成功读取{}行数据，忽略{}行头数据，忽略{}行空白数据，耗时{}毫秒", readRowCount, skipHeader, blankRowCount, System.currentTimeMillis() - startTime);
        } catch (InvalidFormatException | IOException e) {
            log.error("读取excel异常", e);
            throw ApplicationException.ae(ErrorMessage.ApplicationErrorMessage.EXPORT_EXCEL_FAIL);
        }
        return result;
    }

    /**
     * 读取头信息
     *
     * @param headerRow 头Row
     * @return
     */
    private String[] getHeaderDefinition(Row headerRow) {
        short lastCellNum = headerRow.getLastCellNum();
        String[] headers = new String[lastCellNum];
        for (int i = 0; i < headers.length; i++) {
            headers[i] = headerRow.getCell(i).getStringCellValue();
        }
        return headers;
    }

    /**
     * 获取当前cell的数据类型
     *
     * @param cell 当前cell
     * @return 对应的数据类型枚举
     */
    private static String printCellValue(Cell cell, JSONObject jsonObject, String key) {
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                boolean booleanCellValue = cell.getBooleanCellValue();
                jsonObject.put(key, booleanCellValue);
                return String.valueOf(booleanCellValue);
            case STRING:
                String stringValue = cell.getRichStringCellValue().getString();
                jsonObject.put(key, stringValue);
                return stringValue;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    String date = DateTools.dateToString(cell.getDateCellValue(), DateTools.yyyyMMddHHmmss);
                    jsonObject.put(key, date);
                    return date;
                } else {
                    double cellValue = cell.getNumericCellValue();
                    jsonObject.put(key, cellValue);
                    return String.valueOf(cellValue);
                }
            case FORMULA:
                String cellFormula = cell.getCellFormula();
                jsonObject.put(key, cellFormula);
                return String.valueOf(cellFormula);
            case BLANK:
                jsonObject.put(key, StringUtils.EMPTY);
                return StringUtils.EMPTY;
            default:
                jsonObject.put(key, null);
                throw ApplicationException.ae(ErrorMessage.ApplicationErrorMessage.UNKNOW_DATA_TYPE);
        }
    }

    /**
     * Cell数据类型定义枚举
     */
    @AllArgsConstructor
    @Getter
    private enum CellDataType {
        /**
         * Boolean
         */
        BOOLEAN("布尔值"),
        /**
         * 字符串
         */
        STRING("字符串"),
        /**
         * 数字
         */
        NUMERIC("数字"),
        /**
         * 公式
         */
        FORMULA("公式"),
        /**
         * 空格
         */
        BLANK("空格");
        /**
         * 类型描述
         */
        private String desc;
    }
}
