package com.yzjttcgs.integratedappletinterface.domain.request;

import lombok.Data;

/**
 * Description
 * Author Miaoyu
 * Date 2025-03-03 11:11:18
 */
@Data
public class CouponQuickPayRequest {
    private String mobile;
    private String parkId;
    private String payMoney;
    private String hours;
}
