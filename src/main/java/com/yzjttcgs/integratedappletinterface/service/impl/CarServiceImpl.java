package com.yzjttcgs.integratedappletinterface.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.yzjttcgs.integratedappletinterface.common.exception.ServiceException;
import com.yzjttcgs.integratedappletinterface.domain.dto.UserCarBindDto;
import com.yzjttcgs.integratedappletinterface.domain.po.UserBase;
import com.yzjttcgs.integratedappletinterface.domain.po.UserCar;
import com.yzjttcgs.integratedappletinterface.domain.request.UserCarInfoRequest;
import com.yzjttcgs.integratedappletinterface.mapper.UserBaseMapper;
import com.yzjttcgs.integratedappletinterface.mapper.UserCarMapper;
import com.yzjttcgs.integratedappletinterface.service.ICarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-25 15:11:51
 */
@Service
@Slf4j
public class CarServiceImpl implements ICarService {

    @Resource
    private UserCarMapper userCarMapper;
    @Resource
    private UserBaseMapper userBaseMapper;
    @Autowired
    private JieyiServiceImpl jieyiService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bind(UserCarInfoRequest request) {
        UserCar userCar = new UserCar();
        userCar.setCarNo(request.getCarNo());
        List<UserCar> userCarList = userCarMapper.selectList(userCar);
        if (CollectionUtil.isNotEmpty(userCarList)) {
            UserCar car = userCarList.get(0);
            String userId = car.getUserId();
            if (request.getUserId().equals(userId)) {
                throw new ServiceException("该用户已绑定该车牌");
            } else {
                UserBase userBase = new UserBase();
                userBase.setUserId(request.getUserId());
                List<UserBase> userBases = userBaseMapper.selectAll(userBase);
                userBase = userBases.get(0);
                String mobile = userBase.getMobile();
                String secretMobile = mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
                throw new ServiceException("该车牌已被" + secretMobile + "绑定，可通过车牌验证找回");
            }
        }
        userCar = new UserCar();
        userCar.setUserId(request.getUserId());
        userCarList = userCarMapper.selectList(userCar);
        if (CollectionUtil.isNotEmpty(userCarList) && userCarList.size() >= 3) {
            throw new ServiceException("用户最多绑定3辆车");
        }
        userCar = new UserCar();
        userCar.setCarNo(request.getCarNo());
        userCar.setCarColor(request.getPlateColor());
        userCar.setUserId(request.getUserId());
        userCar.setCreateTime(new Date());
        int i = userCarMapper.insert(userCar);
        if (i <= 0) {
            throw new ServiceException("绑定失败");
        }
        UserCarBindDto dto = new UserCarBindDto();
        dto.setUserid(request.getUserId());
        dto.setCarno(request.getCarNo());
        dto.setOptype("0");
        dto.setBindCarTime(new Date().toString());
        jieyiService.bindCar(dto);
    }

    @Override
    public void unbind(UserCarInfoRequest request) {

    }
}
