package com.eolink.common.enumeration;

/**
 * @author sunanzhi work
 */
public enum ErrorCodeEnums {
    /**
     * 预留编码表 -1 ~ 9999 通用编码
     */
    FAIL_REQ(-1),
    SUCCESS(0),
    /**
     * 请求参数错误
     */
    PARAM_ERROR(1),
    OPERATION_LOG_PATH_PRIORITY_NONE(2),
    OPERATION_LOG_PATH_CONFLICT(3),
    OPERATION_LOG_REQUEST_METHOD_CONFLICT(4),
    UNKNOWN_ERROR(5),

    ;

    private final long errorCode;

    ErrorCodeEnums(long errorCode) {
        this.errorCode = errorCode;
    }

    public long getErrorCode() {
        return errorCode;
    }
}
