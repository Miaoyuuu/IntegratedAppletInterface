package com.yzjttcgs.integratedappletinterface.service;

import com.yzjttcgs.integratedappletinterface.domain.vo.UserInfo;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-17 11:31:57
 */
public interface IUserService {
    String selectUserId(String mobile);
    UserInfo selectUserInfo(String mobile);

    String register(String mobile);
}
