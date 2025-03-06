package com.yzjttcgs.integratedappletinterface.controller;

import com.yzjttcgs.integratedappletinterface.common.annotation.SignatureValidation;
import com.yzjttcgs.integratedappletinterface.common.domain.R;
import com.yzjttcgs.integratedappletinterface.domain.request.UserInfoRequest;
import com.yzjttcgs.integratedappletinterface.domain.vo.UserInfo;
import com.yzjttcgs.integratedappletinterface.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-17 11:30:19
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/info")
    @SignatureValidation
    public R<UserInfo> info(@RequestBody @Valid UserInfoRequest request) {
        String mobile = request.getMobile();
        log.info("会员信息查询:{}", mobile);
        UserInfo userInfo = userService.selectUserInfo(mobile);
        return R.ok(userInfo);
    }

    @RequestMapping("/register")
    @SignatureValidation
    public R<String> register(@RequestBody @Valid UserInfoRequest request) {
        String mobile = request.getMobile();
        log.info("会员注册:{}", mobile);
        String userId = userService.register(mobile);
        return R.ok(userId);
    }

}
