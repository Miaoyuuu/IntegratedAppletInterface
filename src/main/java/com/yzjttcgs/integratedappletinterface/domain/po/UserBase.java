package com.yzjttcgs.integratedappletinterface.domain.po;

import lombok.Data;

import java.util.Date;

@Data
public class UserBase {
    private String userId;
    private String userType;
    private String status;
    private String merchantNo;
    private String mobile;
    private String email;
    private String userName;
    private String cerType;
    private String cerno;
    private String regChannel;
    private String verificationLevel;
    private String certification;
    private String userLevel;
    private String riskTolerance;
    private String picWay;
    private Date createTime;
    private Date modifyTime;
    private String isPaypwd;
    private String thirdUserId;
    private String cerVerify;
    private String appId;
    private String merchantSignNo;
    private String jobNum;
    private String cardNum;
    private String companyName;
    private String nickName;
    private String shareId;
    private String address;
    private String orgCode;
    private String levelStartTime;
    private String levelEndTime;
}