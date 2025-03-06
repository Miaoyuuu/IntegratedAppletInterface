package com.yzjttcgs.integratedappletinterface.controller;

import com.alibaba.fastjson2.JSON;
import com.yzjttcgs.integratedappletinterface.common.annotation.SignatureValidation;
import com.yzjttcgs.integratedappletinterface.common.domain.R;
import com.yzjttcgs.integratedappletinterface.domain.request.UserCarInfoRequest;
import com.yzjttcgs.integratedappletinterface.service.impl.CarServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-25 15:01:47
 */
@RestController
@RequestMapping("/car")
@Slf4j
public class CarController {

    @Autowired
    private CarServiceImpl carService;

    @RequestMapping("/carOptDispose")
    @SignatureValidation
    public R<String> carOptDispose(@RequestBody @Valid UserCarInfoRequest request){
        log.info("车牌信息更新:{}", JSON.toJSONString(request));
        if ("1".equals(request.getOpType())) {
            carService.bind(request);
        }else if ("2".equals(request.getOpType())){
            carService.unbind(request);
        }
        return R.ok("success");
    }
}
