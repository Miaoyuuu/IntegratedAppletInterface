package com.yzjttcgs.integratedappletinterface.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-27 9:52:38
 */
@Data
public class PrePayRequest {
    @NotBlank(message = "订单号不能为空")
    private String orderId;
    @NotBlank(message = "下单类型不能为空")
    @Pattern(regexp = "11|12", message = "下单类型只能为：11-室内预支付，12-室内超时缴费")
    private String payReqType;
    private String userId;
}
