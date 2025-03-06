package com.yzjttcgs.integratedappletinterface.service;

import com.yzjttcgs.integratedappletinterface.domain.request.UserCarInfoRequest;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-25 15:11:42
 */
public interface ICarService {
    void bind(UserCarInfoRequest request);
    void unbind(UserCarInfoRequest request);
}
