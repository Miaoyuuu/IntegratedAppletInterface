package com.yzjttcgs.integratedappletinterface.controller;

import com.alibaba.fastjson2.JSON;
import com.yzjttcgs.integratedappletinterface.common.annotation.SignatureValidation;
import com.yzjttcgs.integratedappletinterface.common.domain.R;
import com.yzjttcgs.integratedappletinterface.domain.request.UpdateBalanceRequest;
import com.yzjttcgs.integratedappletinterface.service.impl.AcctServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-24 14:45:35
 */
@RestController
@RequestMapping("/acct")
@Slf4j
public class AcctController {

    @Autowired
    private AcctServiceImpl acctService;

    @RequestMapping("/updateBalance")
    @SignatureValidation
    public R<Long> updateBalance(@RequestBody @Valid UpdateBalanceRequest request){
        log.info("余额更新:{}", JSON.toJSONString(request));
        Long balance = acctService.updateBalance(request);
        return R.ok(balance);
    }
}
