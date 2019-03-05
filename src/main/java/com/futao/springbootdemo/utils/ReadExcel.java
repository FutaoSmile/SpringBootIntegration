package com.futao.springbootdemo.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析excel
 *
 * @author futao
 * Created on 2018/11/7.
 */
public class ReadExcel {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
    private static final String TABLE_NAME = "Table name:";
    private static final String FIELD_NAME = "FieldName";
    private static final String TYPE = "TYPE";
    private static final String LENGTH = "Length";
    private static final String DECIMAL_LENGTH = "DecimalLength";
    private static final String NOT_NULL = "NOT_NULL";
    private static final String PRIMARY_KEY = "PRIMARY_KEY";
    private static final String FOREIGN_KEY = "FOREIGN_KEY";
    private static final String INDEX = "INDEX";
    private static final String DESCRIPTION = "DESCRIPTION";

    public static void read(String path) {
        List<String> tables = new ArrayList<>();
        try {
            // 创建文件对象
            File excelFile = new File(path);
            // 文件流
            FileInputStream in = new FileInputStream(excelFile);
            checkExcelVaild(excelFile);
            Workbook workbook = getWorkbook(in, excelFile);
            // Sheet的数量
            int sheetCount = workbook.getNumberOfSheets();
            /**
             * 设置当前excel中sheet的下标：0开始
             */
            // 遍历第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);

            //获取总行数
            StringBuilder sql = new StringBuilder();
            //遍历所有行
            for (Row row : sheet) {
                if (TABLE_NAME.equals(getCellValue(row.getCell(0)))) {
                    sql.append(");create table `")
                            .append(row.getCell(1).toString().trim())
                            .append("`(");
                } else if (FIELD_NAME.equals(getCellValue(row.getCell(0)))) {
                } else {
                    sql.append("`")
                            //字段名
                            .append(getCellValue(row.getCell(0)))
                            .append("`")
                            .append(" ")
                            //字段类型
                            .append(getCellValue(row.getCell(1)))
                            .append(" ")
                            //length
//                            .append(getCellValue(row.getCell(2)))
                            .append(" ");
                    if (getCellValue(row.getCell(4)).equals(NOT_NULL)) {
                        sql.append("NOT NULL")
                                .append(" ");
                    }
                    sql.append(",");
                }
            }

            System.out.println("========" + sql.substring(2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 判断文件是否是excel
     *
     * @throws Exception
     */
    public static void checkExcelVaild(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        if ((file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX))) {
            throw new Exception("文件不是Excel");
        }
    }

    /**
     * 判断Excel的版本,获取Workbook
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream in, File file) throws IOException {
        Workbook wb = null;
        //Excel 2003
        if (file.getName().endsWith(EXCEL_XLS)) {
            wb = new HSSFWorkbook(in);
            // Excel 2007/2010
        } else if (file.getName().endsWith(EXCEL_XLSX)) {
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 获取当前cell的类型
     *
     * @param cell
     * @return
     */
    private static Object getValue(Cell cell) {
        Object obj = null;
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case ERROR:
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                obj = cell.getNumericCellValue();
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return StringUtils.EMPTY;
        } else {
            return cell.toString().trim();
        }
    }
}
