package com.yzjttcgs.integratedappletinterface.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-26 14:22:09
 */
@Data
public class PayOrderNoticeRequest {
    @NotBlank(message = "订单号不能为空")
    private String orderId;
    @NotBlank(message = "支付金额不能为空")
    private String payAmount;
    @NotBlank(message = "支付时间不能为空")
    private String payTime;
    @NotBlank(message = "支付流水号不能为空")
    private String paySsn;
    @NotBlank(message = "支付方式不能为空")
    @Pattern(regexp = "01|02|05", message = "支付方式只能为：01-支付宝，02-微信，05-钱包")
    private String payPlt;
    @NotBlank(message = "商户支付单号不能为空")
    private String parkPaySsn;
    @NotBlank(message = "优惠金额不能为空")
    private String promoteAmount;
}
