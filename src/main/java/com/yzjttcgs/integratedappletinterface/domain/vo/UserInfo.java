package com.yzjttcgs.integratedappletinterface.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yzjttcgs.integratedappletinterface.domain.po.UserCar;
import lombok.Data;

import java.util.List;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-17 16:22:26
 */
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class UserInfo {
    private String mobile;
    private String userId;
    private String acctId;
    private long balance;
    private List<UserCar> userCar;

}
