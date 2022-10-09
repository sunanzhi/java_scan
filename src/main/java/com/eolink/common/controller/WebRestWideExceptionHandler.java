package com.eolink.common.controller;

import com.eolink.common.domain.dto.BaseResponse;
import com.eolink.common.exception.ApiException;
import com.eolink.common.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

import static com.eolink.common.domain.dto.BaseResponse.fail;
import static com.eolink.common.enumeration.ErrorCodeEnums.PARAM_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author sunanzhi work
 */
@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class WebRestWideExceptionHandler {
    private final MessageSource messageSource;

    @Autowired
    public WebRestWideExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public BaseResponse<?> badRequestExceptionHandler(BadRequestException e) {
        // 获取对应国际化资源错误信息
        var message = Optional.ofNullable(e.getMessageCode())
                .flatMap(messageCode -> getMessage(messageCode, e.getData()))
                .orElseGet(e::getMessage);
        log.error("[{}] catch exception:[{}],error code:[{}]", getClass().getSimpleName(), message, e.getErrorCode(), e);
        return fail(e.getErrorCode(), message, e.getClass().getName());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResponse<?> paramErrorExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String ex = e.getMessage();

        String message = getBindingResultMessage(bindingResult, ex);

        log.error("[{}] catch exception:[{}]", getClass().getSimpleName(), message, e);
        return fail(PARAM_ERROR.getErrorCode(), message, e.getClass().getName());
    }

    @ExceptionHandler(value = BindException.class)
    public BaseResponse<?> bindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String ex = e.getMessage();

        String message = getBindingResultMessage(bindingResult, ex);
        log.error("[{}] catch exception:[{}]", getClass().getSimpleName(), message, e);
        return fail(PARAM_ERROR.getErrorCode(), message, e.getClass().getName());
    }

    /**
     * 获取绑定异常错误信息
     *
     * @param bindingResult
     *         结果
     * @param ex
     *         源错误信息
     *
     * @return String
     */
    private String getBindingResultMessage(BindingResult bindingResult, String ex) {
        var paramError = Optional.of(bindingResult)
                .map(Errors::getFieldErrors)
                .map(errors -> errors
                        .stream()
                        .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                        .reduce((a, b) -> a + ", " + b)
                        .orElseGet(String::new))
                .orElse("");
        return getMessage(PARAM_ERROR.name(), new String[]{paramError}).orElse(ex);
    }

    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ApiException.class)
    public BaseResponse<?> apiExceptionHandler(ApiException e) {
        // 获取对应国际化资源错误信息
        var message = Optional.ofNullable(e.getMessageCode())
                .flatMap(messageCode -> getMessage(messageCode, e.getData()))
                .orElseGet(e::getMessage);
        log.error("[{}] catch exception:[{}],error code:[{}]", getClass().getSimpleName(), message, e.getErrorCode(), e);
        return fail(e.getErrorCode(), message, e.getClass().getName());
    }

    private Optional<String> getMessage(String messageCode, Object[] args) {
        try {
            return Optional.of(messageSource.getMessage(messageCode, args, LocaleContextHolder.getLocale()));
        } catch (Exception e) {
            log.error("rest exception handler get message error,cause : [{}] ,message code : [{}]", e.getMessage(), messageCode);
            return Optional.empty();
        }
    }

    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResponse<?> allExceptionHandler(Exception e) {
        log.error("[{}] catch exception", getClass().getSimpleName(), e);
        return fail(e.getMessage(), e.getClass().getName());
    }
}
