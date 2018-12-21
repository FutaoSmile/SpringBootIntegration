package com.futao.springmvcdemo.model.system;

import com.alibaba.fastjson.annotation.JSONField;
import org.joda.time.DateTime;

import java.sql.Timestamp;

/**
 * @author futao
 * Created on 2018/9/22-21:47.
 * 统一返回Rest风格的数据结构
 */
public class RestResult {
    /**
     * 请求成功code
     */
    public static final String SUCCESS_CODE = "0";
    /**
     * 系统异常code
     */
    public static final String SYSTEM_ERROR_CODE = "-1";
    /**
     * 未重写hibernate validator错误信息的异常code
     */
    public static final String NOT_RE_WRITE_ERROR_MESSAGE = "-99";


    /**
     * 请求是否成功
     */
    private boolean success;
    /**
     * 成功或者失败的code错误码
     */
    private String code;
    /**
     * 成功时返回的数据，失败时返回具体的异常信息
     */
    private Object data;
    /**
     * 请求失败返回的提示信息，给前端进行页面展示的信息
     */
    private Object errorMessage;
    /**
     * 服务器当前时间（便于查找定位请求时间，因为实际开发过程中服务器时间可能跟本地时间不一致，加上这个时间戳便于日后定位）
     */
    @JSONField(format = "yyyy-MM-dd")
    private Timestamp currentTime;

    public RestResult() {
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", data=" + data +
                ", errorMessage=" + errorMessage +
                ", currentTime=" + currentTime +
                '}';
    }


    public RestResult(boolean success, String code, Object data, Object errorMessage) {
        this.success = success;
        this.code = code;
        this.data = data;
        this.errorMessage = errorMessage;
        this.currentTime = new Timestamp(new DateTime().getMillis());
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Timestamp getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Timestamp currentTime) {
        this.currentTime = currentTime;
    }
}
