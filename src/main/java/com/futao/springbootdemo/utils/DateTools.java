package com.futao.springbootdemo.utils;

import com.futao.springbootdemo.model.entity.BaseEntity;
import com.futao.springbootdemo.model.system.ErrorMessage;
import com.lazyer.foundation.foundation.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author futao
 * Created on 2019-02-26.
 */
public class DateTools {

    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

    public static final String yyyyMMddhhmmss = "yyyy-MM-dd hh:mm:ss";

    public static final String yyyyMMddhhmmssS = "yyyy-MM-dd hh:mm:ss.S";

    public static final String yyyyMMdd = "yyyy-MM-dd";

    public static final String yyyyMMddChinese = "yyyy年MM月dd日";

    private static final Logger LOGGER = LoggerFactory.getLogger(DateTools.class);

    /**
     * 将日期转成指定时间格式的字符串
     *
     * @param date    日期
     * @param pattern 时间格式
     * @return 格式化之后的时间字符串
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * *
     * 将时间字符串转为时间对象
     *
     * @param dateString 时间字符串
     * @param pattern    时间格式
     * @return 转化之后的时间对象
     */

    public static Date stringToDate(String dateString, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            LOGGER.error("时间转换异常:", e);
            throw ApplicationException.ae(ErrorMessage.ApplicationErrorMessage.DATE_PARSE_FAIL);
        }
    }

    /**
     * 获取指定格式的今天的时间字符串
     *
     * @param pattern 时间格式
     * @return 格式化的今天的时间字符串
     */
    public static String today(String pattern) {
        Date date = new Date();
        return dateToString(date, pattern);
    }

    /**
     * 获取今天加/减{days}天后/前的时间字符串
     *
     * @param pattern 时间格式
     * @param days    加上/减去多少天
     * @return 加/减{days}天后的时间字符串
     */
    public static String todayAfter(String pattern, int days) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return dateToString(calendar.getTime(), pattern);
    }

    /**
     * 获取当前时间戳
     *
     * @return 当前时间戳
     */
    public static Timestamp currentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }


    /**
     * 当前时间戳字符串
     *
     * @return
     */
    public static String currentTimestampString() {
        return dateToString(new Date(), yyyyMMddHHmmss);
    }

    /**
     * 将字符串转为时间戳类型
     *
     * @param timestampString 时间戳字符串
     * @return 转换后的时间戳
     */
    public static Timestamp stringToTimeStamp(String timestampString) {
        return new Timestamp(stringToDate(timestampString, yyyyMMddHHmmss).getTime());
    }

    /**
     * 获取当前时间前/后的时间戳
     *
     * @param timestampString 时间戳字符串
     * @param timeType        类型
     * @param count           添加的时长
     * @return
     */
    public static Timestamp afterSomeTime(String timestampString, TimeTypeEnum timeType, int count) {
        Date date = addTimes(stringToDate(timestampString, yyyyMMddHHmmss), timeType, count);
        return new Timestamp(date.getTime());
    }

    /**
     * 设置创建时间与最后修改时间
     *
     * @param entity 实体对象
     * @param <T>    extend BaseEntity
     */
    public static <T extends BaseEntity> void setCreateAndLastModifyTimeNow(T entity) {
        entity.setCreateTime(currentTimeStamp());
        entity.setLastModifyTime(currentTimeStamp());
    }

    private static Date addTimes(Date date, TimeTypeEnum timeType, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(timeType.getType(), count);
        return calendar.getTime();
    }

    /**
     * 时间类型枚举
     */
    public enum TimeTypeEnum {
        /**
         * 秒
         */
        SECOND(Calendar.SECOND),
        /**
         * 分钟
         */
        MIN(Calendar.MINUTE),
        /**
         * 小时
         */
        HOUR(Calendar.HOUR),
        /**
         * 天
         */
        DAY(Calendar.DATE),
        /**
         * 月
         */
        MONTH(Calendar.MONTH),
        /**
         * 年
         */
        YEAR(Calendar.YEAR);

        /**
         * 类型
         */
        private int type;

        TimeTypeEnum(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }
}
