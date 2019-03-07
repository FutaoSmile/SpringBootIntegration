package com.futao.springbootdemo.model.system;

import com.futao.springbootdemo.utils.DateTools;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * 统一返回Rest风格的数据结构
 *
 * @author futao
 * Created on 2018/9/22-21:47.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
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
     * 服务器当前时间（便于查找定位请求时间，因为实际开发过程中服务器时间可能跟本地时间不一致，加上这个时间戳便于后期定位）
     */
    private Timestamp serverTime;

    /**
     * @param success      请求结果是否成功
     * @param code         成功/错误码
     * @param data         请求成功返回的数据/请求异常返回的异常详情
     * @param errorMessage 请求发生异常时给用户的提示
     */
    public RestResult(boolean success, String code, Object data, Object errorMessage) {
        this.success = success;
        this.code = code;
        this.data = data;
        this.errorMessage = errorMessage;
        this.serverTime = DateTools.currentTimeStamp();
    }
}

