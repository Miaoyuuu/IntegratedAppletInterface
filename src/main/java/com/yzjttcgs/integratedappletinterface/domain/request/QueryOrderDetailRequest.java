package com.yzjttcgs.integratedappletinterface.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-19 15:29:41
 */
@Data
public class QueryOrderDetailRequest {
    @NotBlank(message = "车牌号不能为空")
    private String carNo;
    private String mobile;
}
