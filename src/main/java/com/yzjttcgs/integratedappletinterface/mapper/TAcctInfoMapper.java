package com.yzjttcgs.integratedappletinterface.mapper;

import com.yzjttcgs.integratedappletinterface.domain.po.TAcctInfo;

/**
 * @author Miaoyu
 * @description 针对表【t_acct_info】的数据库操作Mapper
 * @createDate 2025-02-18 10:23:37
 * @Entity com.yzjttcgs.integratedappletinterface.domain.po.TAcctInfo
 */
public interface TAcctInfoMapper {
    public TAcctInfo selectByUserId(String userId);

    int updateBalanceByUserIdAndAcctType(long amt, String userId, String acctType);
}




