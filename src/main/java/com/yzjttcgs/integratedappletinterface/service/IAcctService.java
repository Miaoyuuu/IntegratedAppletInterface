package com.yzjttcgs.integratedappletinterface.service;

import com.yzjttcgs.integratedappletinterface.domain.request.UpdateBalanceRequest;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-24 15:50:40
 */
public interface IAcctService {
    Long updateBalance(UpdateBalanceRequest request);
}
