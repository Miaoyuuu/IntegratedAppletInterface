package com.yzjttcgs.integratedappletinterface.mapper;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-24 17:11:31
 */
public interface TAcctTranDetMapper {

    int addTAcctTranDet(String uuid, long amt, String acctId, String userId, String acctName);
}
