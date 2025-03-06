package com.yzjttcgs.integratedappletinterface.domain.dto;

import lombok.Data;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-26 10:35:27
 */
@Data
public class UserCarBindDto extends ParkDto{
    private String userid;
    private String carno;
    private String optype; //0：绑定 1：解绑
    private String bindCarTime;
    private String attach;
    private String mac;
}
