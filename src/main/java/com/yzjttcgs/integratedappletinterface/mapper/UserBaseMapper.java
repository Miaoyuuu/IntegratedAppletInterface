package com.yzjttcgs.integratedappletinterface.mapper;

import com.yzjttcgs.integratedappletinterface.domain.po.UserBase;

import java.util.List;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-17 16:59:30
 */
public interface UserBaseMapper {
    List<UserBase> selectAll(UserBase userBase);
}
