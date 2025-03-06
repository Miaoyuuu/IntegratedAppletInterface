package com.yzjttcgs.integratedappletinterface.domain.po;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @TableName user_car
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserCar {
    private Integer id;

    private String userId;

    private String carNo;

    private Integer ifHold;

    private String holdTypeId;

    private String holdType;

    private String vin;

    private String engineNo;

    private Integer brandId;

    private Integer groupId;

    private Integer seriesId;

    private String isAuth;

    private Integer isWt;

    private String isDefault;

    private Date holdTime;

    private Date updateTime;

    private Date createTime;

    private String carColor;

}