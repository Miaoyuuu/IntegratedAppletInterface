package com.yzjttcgs.integratedappletinterface.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-19 16:41:38
 */
@Data
public class TMessage {
    private Integer id;
    private String msgId;
    private String phone;
    private String content;
    private String tpCode;
    private Date createTime;
    private Date lastUpdateTime;
    private Integer submitTimes;
    private Integer failTimes;
    private Integer successTimes;
    private String errCode;
    private String errDesc;

}
