package com.yzjttcgs.integratedappletinterface.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-28 14:33:02
 */
@Data
public class CouponUseRequest {
    @NotBlank(message = "手机号不能为空")
    private String mobile;
    @NotBlank(message = "车牌号不能为空")
    private String carNo;
    @NotBlank(message = "车场ID不能为空")
    private String parkId;
    @NotBlank(message = "车场名称不能为空")
    private String parkName;
    @NotBlank(message = "入场时间不能为空")
    private String intoTime;
    @NotBlank(message = "支付时间不能为空")
    private String payTime;
    @NotBlank(message = "支付方式不能为空")
    @Pattern(regexp = "([0123])", message = "支付方式只能是：0-钱包，1-微信，2-支付宝，3-全免")
    private String payType;
    @NotBlank(message = "应付金额不能为空")
    private String payable;
    @NotBlank(message = "实付金额不能为空")
    private String paidin;
    @NotBlank(message = "免费时长不能为空")
    private String freeTime;
    @NotBlank(message = "优惠券ID不能为空")
    private String userCpIds;
    @NotBlank(message = "支付流水号不能为空")
    private String parkPaySsn;
}
