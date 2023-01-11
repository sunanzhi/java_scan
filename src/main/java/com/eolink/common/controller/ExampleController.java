package com.eolink.common.controller;

import com.eolink.common.domain.dto.BaseResponse;
import com.eolink.common.enumeration.ErrorCodeEnums;
import com.eolink.common.exception.ApiException;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

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

    @PostMapping("/params")
    public BaseResponse<String> params(@Validated @ApiParam(value = "测试") @NotBlank String test
    @ApiParam(value = "测试22") @IsId @NotBlank @Length(max= 18) String id) {
        return BaseResponse.success("im string");
    }

    @PostMapping("/add")
    public BaseResponse<String> add(@ApiParamBody McMt mcMt,
 @ApiParamBody("mtc") @NotEmpty(message = "消息模板通道不能为空") @Valid List<Map<String, Object>> mtc,
             @ApiParam(value = "消息策略") @IsId @Length(max = 36) String strategyId,
             @ApiParam(value = "创建人标识") @IsId @Length(max = 36) @NotBlank (message = "创建人标识不能为空") String mtCreator) {
        return BaseResponse.success("im string");
    }
}
