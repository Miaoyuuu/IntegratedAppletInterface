package com.yzjttcgs.integratedappletinterface.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.yzjttcgs.integratedappletinterface.common.constant.CommonConstants;
import com.yzjttcgs.integratedappletinterface.common.exception.ServiceException;
import com.yzjttcgs.integratedappletinterface.common.utils.HttpUtils;
import com.yzjttcgs.integratedappletinterface.domain.po.TAcctInfo;
import com.yzjttcgs.integratedappletinterface.domain.po.UserBase;
import com.yzjttcgs.integratedappletinterface.domain.po.UserCar;
import com.yzjttcgs.integratedappletinterface.domain.vo.UserInfo;
import com.yzjttcgs.integratedappletinterface.mapper.TAcctInfoMapper;
import com.yzjttcgs.integratedappletinterface.mapper.TMessageMapper;
import com.yzjttcgs.integratedappletinterface.mapper.UserBaseMapper;
import com.yzjttcgs.integratedappletinterface.mapper.UserCarMapper;
import com.yzjttcgs.integratedappletinterface.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-17 11:32:23
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Resource
    private UserBaseMapper userBaseMapper;
    @Resource
    private UserCarMapper userCarMapper;
    @Resource
    private TAcctInfoMapper acctInfoMapper;
    @Resource
    private TMessageMapper messageMapper;

    /**
     * 获取用户信息
     *
     * @param mobile
     * @return
     */
    @Override
    public UserInfo selectUserInfo(String mobile) {
        String userId = selectUserId(mobile);
        if (userId == null){
            throw new ServiceException("用户不存在");
        }
        TAcctInfo tAcctInfo = acctInfoMapper.selectByUserId(userId);
        UserCar userCarParam = new UserCar();
        userCarParam.setUserId(userId);
        List<UserCar> userCarList = userCarMapper.selectList(userCarParam);
        if (CollectionUtil.isNotEmpty(userCarList)) {
            userCarList = userCarList.stream().map(userCar -> {
                UserCar car = new UserCar();
                car.setCarNo(userCar.getCarNo());
                car.setCarColor(userCar.getCarColor());
                car.setIfHold("JYF".equals(userCar.getHoldType()) ? 1 : 0);
                return car;
            }).collect(Collectors.toList());
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setBalance(tAcctInfo.getBalance());
        userInfo.setMobile(mobile);
        userInfo.setUserId(userId);
        userInfo.setAcctId(tAcctInfo.getAcctId());
        userInfo.setUserCar(userCarList);
        return userInfo;
    }

    public String selectUserId(String mobile) {
        UserBase userBase = new UserBase();
        userBase.setMobile(mobile);
        userBase.setUserType("C");
        List<UserBase> userBaseList = userBaseMapper.selectAll(userBase);
        if (CollectionUtil.isEmpty(userBaseList)) {
            return null;
        }
        userBase = userBaseList.get(0);
        return userBase.getUserId();
    }

    @Override
    public String register(String mobile) {
        Map<String, Object> param = new HashMap<>();
        param.put("mobile", mobile);
        String result = HttpUtils.sendPost(CommonConstants.YXYZ_URL + "/jst-park-app/sms/sendVerify", JSON.toJSONString(param));
        log.info("发送验证码返回:{}", result);
        JSONObject jo = JSON.parseObject(result);
        if (!"200".equals(jo.getString("code"))) {
            throw new ServiceException("注册失败");
        }
        // 您申请的验证码987133,请在10分钟内完成验证!
        String content = messageMapper.selectLatestContentByPhone(mobile);
        String verifyNum = content.substring(7, 13);
        param.put("password", DigestUtil.md5Hex("123456"));
        param.put("verifyNum", verifyNum);
        param.put("LATITUDE", "0.0");
        param.put("LONGITUDE", "0.0");
        param.put("address", "扬州");
        param.put("deviceType", "1");
        param.put("appVersion", "yxyz.v2.5.5");
        result = HttpUtils.sendPost(CommonConstants.YXYZ_URL + "/jst-park-app/member/register", JSON.toJSONString(param));
        log.info("注册返回:{}", result);
        jo = JSON.parseObject(result);
        if ("MB300008".equals(jo.getString("code"))) {
            throw new ServiceException(jo.getString("msg"));
        }
        if (!"200".equals(jo.getString("code"))) {
            throw new ServiceException("注册失败");
        }
        return jo.getJSONObject("data").getString("userId");
    }

}
