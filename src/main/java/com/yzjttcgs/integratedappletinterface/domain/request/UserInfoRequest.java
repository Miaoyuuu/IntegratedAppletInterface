package com.yzjttcgs.integratedappletinterface.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-17 16:04:59
 */
@Data
public class UserInfoRequest {
    @NotBlank(message = "手机号不能为空")
    private String mobile;
}
