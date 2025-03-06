package com.yzjttcgs.integratedappletinterface.domain.dto;

import lombok.Data;

@Data
public class ParkQueryorderInputDto extends ParkDto {

    private static final long serialVersionUID = 1L;
    private String ordid; // 订单号
    private String attach; // 备用字段,Json格式字符串:{}
    private String carno;// 车牌号 输入车牌号后仅按照车牌号查询当前停车
    private String carseatid;// 泊位号
    private String mac; // 报文通讯mac
    private String queryamt;//是否要查询金额	0无需查询（默认），1需要查询
    private String movecar;//是否查询挪车电话:0.不查询，1.查询
    /*动态二维码参数:动态二维码后面带的完整参数，
     * 比如完整二维码为：http://www.jslife.com.cn/pi?key=p12344555,01，
     * 这个字段就送p12344555,01
     */
    private String qrcodeparam;
}
