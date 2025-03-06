package com.yzjttcgs.integratedappletinterface.domain.dto;

import lombok.Data;

@Data
public class OrderPayDto extends ParkDto {

    private static final long serialVersionUID = 1L;
    private String attach;      //备用字段,Json格式字符串:{}
    private String mac;      //报文通讯mac
    private String payreqtype;
    private String ordid;
    private String carseatid;
    private String userid;
    private String carno;
    private String starttime;
    private String parktime;
    private String carfee;
    private String payorder;
    private String operid;
}
