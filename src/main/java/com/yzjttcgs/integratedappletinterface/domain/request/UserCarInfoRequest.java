package com.yzjttcgs.integratedappletinterface.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-25 15:04:20
 */
@Data
public class UserCarInfoRequest {
    @NotBlank(message = "用户Id不能为空")
    private String userId;
    @NotBlank(message = "车牌号不能为空")
    private String carNo;
    @NotBlank(message = "车牌颜色不能为空")
    private String plateColor;
    @NotBlank(message = "操作类型不能为空")
    @Pattern(regexp = "[12]", message = "操作类型只能为1或者2")
    private String opType;
}
