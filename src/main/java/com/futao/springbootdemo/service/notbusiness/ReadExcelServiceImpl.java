package com.futao.springbootdemo.service.notbusiness;

import com.futao.springbootdemo.foundation.ApplicationException;
import com.futao.springbootdemo.model.system.ErrorMessage;
import com.futao.springbootdemo.service.ReadExcelService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author futao
 * Created on 2019-05-08.
 */
@Slf4j
@Service
public class ReadExcelServiceImpl implements ReadExcelService {

    private static final String SAMPLE_XLSX_FILE_PATH = "./bytonAppDbScheme1557308457837.xlsx";

    public void read(int skipHeader) {
//        LinkedHashMap
        //开始时间
        long startTime = System.currentTimeMillis();
        //读取的row数量
        AtomicInteger readRowCount = new AtomicInteger();
        //空行数
        AtomicInteger blankRowCount = new AtomicInteger();
        int start = 0;
        int limit = 0;
        //当前excel
        try (Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH))) {
            log.info(">>> 当前excel共有: {}页", workbook.getNumberOfSheets());
            //遍历excel的每一页
            workbook.forEach(sheet -> {
                log.info("sheetName:{}", sheet.getSheetName());
                //当前页数据量大小
                int lastRowNum = sheet.getLastRowNum();
                log.info("当前页数据量大小为: {}条", lastRowNum + 1);
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
                    for (int j = 0; j < lastCellNum; j++) {
                        //当前单元格数据
                        Cell cell = row.getCell(j);
                        CellDataType dataType = printCellValue(cell);
//                        log.info("当前单元格数据(当前row第{}条数据)：{}", j + 1, cellValue);
                    }
                    readRowCount.getAndIncrement();
                }
            });
            log.info(">>> 成功读取{}行数据，忽略{}行头数据，忽略{}行空白数据，耗时{}毫秒", readRowCount, skipHeader, blankRowCount, System.currentTimeMillis() - startTime);
        } catch (InvalidFormatException | IOException e) {
            log.error("读取excel异常", e);
            throw ApplicationException.ae(ErrorMessage.ApplicationErrorMessage.EXPORT_EXCEL_FAIL);
        }
    }

    @Test
    public void test1() {
        read(1);
    }

    /**
     * 获取当前cell的数据类型
     *
     * @param cell 当前cell
     * @return 对应的数据类型枚举
     */
    private static CellDataType printCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                log.error("BOOLEAN" + cell.getBooleanCellValue());
                return CellDataType.BOOLEAN;
            case STRING:
                log.error("STRING" + cell.getRichStringCellValue().getString());
                return CellDataType.STRING;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    log.error(cell.getDateCellValue() + "");
                } else {
                    log.error(cell.getNumericCellValue() + "");
                }
//                String date = DateTools.dateToString(cell.getDateCellValue(), DateTools.yyyyMMdd);
//                log.warn("NUMERIC" + StringUtils.repeat("-=", 30));
                return CellDataType.NUMERIC;
            case FORMULA:
                log.error("FORMULA" + cell.getCellFormula());
                return CellDataType.FORMULA;
            case BLANK:
                log.error("BLANK");
                return CellDataType.BLANK;
            default:
                log.error("DEFAULT");
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
