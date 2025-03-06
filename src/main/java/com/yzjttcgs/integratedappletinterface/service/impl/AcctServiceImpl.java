package com.yzjttcgs.integratedappletinterface.service.impl;

import com.yzjttcgs.integratedappletinterface.common.annotation.Servicelock;
import com.yzjttcgs.integratedappletinterface.common.exception.ServiceException;
import com.yzjttcgs.integratedappletinterface.domain.po.TAcctInfo;
import com.yzjttcgs.integratedappletinterface.domain.request.UpdateBalanceRequest;
import com.yzjttcgs.integratedappletinterface.mapper.TAcctInfoMapper;
import com.yzjttcgs.integratedappletinterface.mapper.TAcctTranDetMapper;
import com.yzjttcgs.integratedappletinterface.service.IAcctService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-24 15:50:51
 */
@Service
@Slf4j
public class AcctServiceImpl implements IAcctService {

    @Resource
    private TAcctInfoMapper acctInfoMapper;
    @Resource
    private TAcctTranDetMapper acctTranDetMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    @Servicelock
    public Long updateBalance(UpdateBalanceRequest request) {
        long amt = "01".equals(request.getOper()) ? request.getAmount() : -request.getAmount();
        //更新用户余额
        int i1 = acctInfoMapper.updateBalanceByUserIdAndAcctType(amt, request.getUserId(), "01");
        if (i1 != 1) {
            throw new ServiceException("充值失败");
        }

        TAcctInfo tAcctInfo = acctInfoMapper.selectByUserId(request.getUserId());

        if (tAcctInfo == null) {
            throw new ServiceException("充值失败");
        }

        String acctId = tAcctInfo.getAcctId();
        String userId = tAcctInfo.getUserId();
        String acctName = tAcctInfo.getAcctName();

        String uuid = UUID.randomUUID().toString().replace("-", "");

        //入表jstpay.T_ACCT_TRAN_DET
        int i3 = acctTranDetMapper.addTAcctTranDet(uuid,
                amt,
                acctId,
                userId,
                acctName);

        if (i3 != 1) {
            throw new ServiceException("充值失败");
        }

        return tAcctInfo.getBalance();
    }
}
