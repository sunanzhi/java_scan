package com.eolink.common.exception;


import com.eolink.common.enumeration.ErrorCodeEnums;

import java.io.Serial;


/**
 * 自定义异常
 * @author sunanzhi work
 */
public class ApiException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -405393276019235522L;

    /**
     * 国际化资源代码（需要根据语言显示错误信息）
     */
    private String messageCode;
    /**
     * 业务错误代码,如{@link ErrorCodeEnums#getErrorCode()}
     */
    private final Long errorCode;
    /**
     * 携带参数
     */
    private Object[] data;

    /**
     * [慎重使用：属于无包装异常]根据异常错误信息、国际化错误信息码和业务错误码构造自定义异常
     *
     * @param message
     *         异常错误信息
     * @param messageCode
     *         国际化错误信息码
     * @param errorCode
     *         业务错误码
     */
    public ApiException(String message, String messageCode, Long errorCode) {
        super(message);
        this.messageCode = messageCode;
        this.errorCode = errorCode;
    }

    /**
     * 根据异常错误信息、错误源、国际化错误信息码和业务错误码构造自定义异常
     *
     * @param message
     *         异常错误信息
     * @param cause
     *         被包装错误源
     * @param messageCode
     *         国际化错误信息码
     * @param errorCode
     *         业务错误码
     */
    public ApiException(String message, Throwable cause, String messageCode, Long errorCode) {
        super(message, cause);
        this.messageCode = messageCode;
        this.errorCode = errorCode;
    }

    /**
     * 根据错误源、国际化错误信息码和业务错误码构造无自定义异常错误信息的自定义异常
     *
     * @param cause
     *         被包装错误源
     * @param messageCode
     *         国际化错误信息码
     * @param errorCode
     *         业务错误码
     */
    public ApiException(Throwable cause, String messageCode, Long errorCode) {
        super(cause);
        this.messageCode = messageCode;
        this.errorCode = errorCode;
    }

    /**
     * [慎重使用：属于无包装异常]根据业务错误码构造自定义异常
     *
     * @param errorCode
     *         业务错误码
     */
    public ApiException(Long errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * [慎重使用：属于无包装异常]根据异常信息和业务错误码构造自定义异常
     *
     * @param message
     *         异常信息
     * @param errorCode
     *         业务错误码
     */
    public ApiException(String message, Long errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 根据异常信息、错误源和业务错误码构造自定义异常
     *
     * @param message
     *         异常信息
     * @param cause
     *         被包装错误源
     * @param errorCode
     *         业务错误码
     */
    public ApiException(String message, Throwable cause, Long errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * 根据错误源和业务错误码构造自定义异常
     *
     * @param cause
     *         错误源
     * @param errorCode
     *         业务错误码
     */
    public ApiException(Throwable cause, Long errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    /**
     * [慎重使用：属于无包装异常]根据异常信息和国际化错误信息码构造默认业务错误码为0的自定义异常
     *
     * @param message
     *         异常信息
     * @param messageCode
     *         国际化错误资源代码
     */
    public ApiException(String message, String messageCode) {
        super(message);
        this.messageCode = messageCode;
        errorCode = ErrorCodeEnums.FAIL_REQ.getErrorCode();
    }

    /**
     * 根据异常信息、错误源和国际化错误信息码构造默认业务错误码为0的自定义异常
     *
     * @param message
     *         异常信息
     * @param cause
     *         包装错误源
     * @param messageCode
     *         国际化错误资源代码
     */
    public ApiException(String message, Throwable cause, String messageCode) {
        super(message, cause);
        this.messageCode = messageCode;
        errorCode = ErrorCodeEnums.FAIL_REQ.getErrorCode();
    }

    /**
     * 根据异常信息、错误源构造默认业务错误码为0的自定义异常
     *
     * @param message
     *         异常信息
     * @param cause
     *         包装错误源
     */
    public ApiException(String message, Throwable cause) {
        super(message, cause);
        errorCode = ErrorCodeEnums.FAIL_REQ.getErrorCode();
    }

    /**
     * 根据错误源、错误枚举和数据 构造自定义异常
     *
     * @param cause
     *         错误源
     * @param errorCode
     *         错误枚举
     * @param data
     *         数据
     */
    public ApiException(Throwable cause, ErrorCodeEnums errorCode, Object... data) {
        super(cause);
        messageCode = errorCode.name();
        this.errorCode = errorCode.getErrorCode();
        this.data = data;
    }

    /**
     * 根据错误枚举和数据 构造自定义异常
     *
     * @param errorCode
     *         错误枚举
     * @param data
     *         数据
     */
    public ApiException(ErrorCodeEnums errorCode, Object... data) {
        super(errorCode.name());
        messageCode = errorCode.name();
        this.errorCode = errorCode.getErrorCode();
        this.data = data;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public Object[] getData() {
        return data;
    }
}
