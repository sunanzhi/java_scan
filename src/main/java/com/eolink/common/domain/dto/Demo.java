package com.eolink.common.domain.dto;

/**
 * @author sunanzhi work
 */
public class Demo {

    @NotBlank(groups = {Add.class, Edit.class}, message = "通道类型不能为空")
    @Length(max = 3)
    @IsRangeVal(value = {"SMS", "SPU", "EMA", "TPU", "WXO", "WXA"})
    @ApiModelProperty(value = "通道类型(SMS:短信;SPU:自建push;EMA:邮件email;TPU:三方push;WXO:微信公众号t;WXA:微信小程序)")
    @TableField("channelType")
    private String channelType;
}
