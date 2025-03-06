package com.yzjttcgs.integratedappletinterface.domain.request;

import lombok.Data;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-28 17:04:52
 */
@Data
public class CouponLockRequest {
    private String userCpIds;
    private String lockType;
}
