package com.yzjttcgs.integratedappletinterface.service.impl;

import com.alibaba.fastjson2.JSON;
import com.yzjttcgs.integratedappletinterface.common.utils.JieyiUtils;
import com.yzjttcgs.integratedappletinterface.domain.dto.UserCarBindDto;
import com.yzjttcgs.integratedappletinterface.service.IJieyiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-26 10:52:34
 */
@Service
@Slf4j
public class JieyiServiceImpl implements IJieyiService {
    @Override
    public void bindCar(UserCarBindDto dto) {

        try {
            JieyiUtils.updParkDto(dto, "usercarbind");
            dto.setReqchnl("other");
            log.info("同步新增车牌到云平台 dto={}", JSON.toJSONString(dto));
            String jsonObject = JieyiUtils.platformAOP(dto);
            log.info("同步新增车牌到云平台返回结果：" + jsonObject);
            Map<String, Object> ret = JSON.parseObject(jsonObject, Map.class);

            if (!"0000".equals(ret.get("resultcode") + "")) {
                throw new Exception("同步新增车牌到云平台失败");
            }
        } catch (Exception e) {
            log.error("=================【" + getClass().getName() + "】应用异常：：：", JSON.toJSONString(e));
        }
    }
}
