package com.yzjttcgs.integratedappletinterface.mapper;

import com.yzjttcgs.integratedappletinterface.domain.po.UserCar;

import java.util.List;

/**
* @author Miaoyu
* @description 针对表【user_car(用户车牌表)】的数据库操作Mapper
* @createDate 2025-02-18 09:50:01
* @Entity com.yzjttcgs.integratedappletinterface.domain.po.UserCar
*/
public interface UserCarMapper {

    List<UserCar> selectList(UserCar userCar);

    int insert(UserCar userCar);
}




