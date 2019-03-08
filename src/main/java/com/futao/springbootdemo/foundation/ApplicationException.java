package com.futao.springbootdemo.foundation;

/**
 * 统一提示系统异常
 *
 * @author futao
 * Created on 2019-01-25.
 */
public class ApplicationException extends RuntimeException {
    protected ApplicationException() {
    }

    protected ApplicationException(String message) {
        super(message);
    }

    protected ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ApplicationException(Throwable cause) {
        super(cause);
    }

    protected ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 抛出逻辑异常
     *
     * @param errorMsg 异常信息
     * @return
     */
    public static ApplicationException ae(String errorMsg) {
        return new ApplicationException(errorMsg);
    }

    /**
     * 抛出逻辑异常，带有占位符
     *
     * @param errorMsg 错误详情
     * @param args     替换占位符内的内容
     * @return
     */
    public static ApplicationException ae(String errorMsg, Object[] args) {
        return new ApplicationException(String.format(errorMsg, args));
    }
}
