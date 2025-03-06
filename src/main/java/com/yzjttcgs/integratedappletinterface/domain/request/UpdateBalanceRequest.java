package com.yzjttcgs.integratedappletinterface.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-24 15:52:23
 */
@Data
public class UpdateBalanceRequest {
    @NotBlank(message = "用户id不能为空")
    private String userId;
    // 操作 01-充值余额 02-扣减余额
    @NotBlank(message = "操作类型不能为空")
    @Pattern(regexp = "01|02", message = "操作类型只能为01或者02")
    private String oper;
    @NotNull(message = "金额不能为空")
    private Long amount;
}
