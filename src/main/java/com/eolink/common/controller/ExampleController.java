package com.eolink.common.controller;

import com.eolink.common.domain.dto.BaseResponse;
import com.eolink.common.enumeration.ErrorCodeEnums;
import com.eolink.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例
 * @author sunanzhi work
 */
@RestController
@Slf4j
@RequestMapping("/common/example")
public class ExampleController {

    /**
     * 返回布尔值
     * @return Boolean
     */
    @RequestMapping("/returnBool")
    public BaseResponse<Boolean> returnBool() {
        return BaseResponse.success(true);
    }

    /**
     * 返回字符串
     * @return String
     */
    @RequestMapping(value = "/returnString", path = "/returnString")
    public BaseResponse<String> returnString() {
        return BaseResponse.success("im string");
    }

    /**
     * 抛出异常
     * @return String
     */
    @PostMapping("/throwException")
    public BaseResponse<String> throwException() {
        throw new ApiException("throw api exception", ErrorCodeEnums.FAIL_REQ.getErrorCode());
    }
}
