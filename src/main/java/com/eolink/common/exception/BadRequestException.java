package com.eolink.common.exception;


import com.eolink.common.enumeration.ErrorCodeEnums;

import java.io.Serial;


/**
 * @author sunanzhi work
 */
public class BadRequestException extends ApiException {
    @Serial
    private static final long serialVersionUID = -1318944809191477853L;

    public BadRequestException(String message, String messageCode, Long errorCode) {
        super(message, messageCode, errorCode);
    }

    public BadRequestException(String message, Throwable cause, String messageCode, Long errorCode) {
        super(message, cause, messageCode, errorCode);
    }

    public BadRequestException(Throwable cause, String messageCode, Long errorCode) {
        super(cause, messageCode, errorCode);
    }

    public BadRequestException(Long errorCode) {
        super(errorCode);
    }

    public BadRequestException(String message, Long errorCode) {
        super(message, errorCode);
    }

    public BadRequestException(String message, Throwable cause, Long errorCode) {
        super(message, cause, errorCode);
    }

    public BadRequestException(Throwable cause, Long errorCode) {
        super(cause, errorCode);
    }

    public BadRequestException(String message, String messageCode) {
        super(message, messageCode);
    }

    public BadRequestException(String message, Throwable cause, String messageCode) {
        super(message, cause, messageCode);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause, ErrorCodeEnums errorCode, Object... data) {
        super(cause, errorCode, data);
    }

    public BadRequestException(ErrorCodeEnums errorCode, Object... data) {
        super(errorCode, data);
    }
}
