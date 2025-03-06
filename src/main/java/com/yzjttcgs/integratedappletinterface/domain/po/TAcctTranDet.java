package com.yzjttcgs.integratedappletinterface.domain.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-24 17:15:43
 */
@Data
public class TAcctTranDet {

    /**
     * 交易明细流水号
     */
    private String id;

    /**
     * 账户交易码
     */
    private String tradeCode;

    /**
     * 账户标识
     */
    private String acctId;

    /**
     * 会员标识
     */
    private String userId;

    /**
     * 账户名称
     */
    private String acctName;

    /**
     * 业务来源（1-APP，2-OMS，3-交易核心）
     */
    private String sourceFrom;

    /**
     * 交易金额
     */
    private Long tranAmount;

    /**
     * 币别（1-CNY，2-积分）
     */
    private Integer currency;

    /**
     * 交易方向（D-存入，C-支取）
     */
    private String tranDec;

    /**
     * 业务类型（00-线上充值，01-线上提现 ，02-支付 03-线下充值 04-线下提现 05-结算 06-退款 07-代扣 08-代扣清算，09-代扣长款挂账、10-代扣短款挂账、11-代扣短款冲销、12-代扣长款冲销、13-代付长款挂账、14-代付短款挂账、15-代付长款冲销、16-代付短款冲销、17-出款、18-收款,19-代付）
     */
    private String busTranType;

    /**
     * 交易日期8位数字
     */
    private Integer busTransDate;

    /**
     * 交易流水号（交易核心订单流水号）
     */
    private String busNo;

    /**
     * 支付流水号(账户前置流水号)
     */
    private String payNo;

    /**
     * 对方账户标识
     */
    private String oppAcctId;

    /**
     * 对方会员标识
     */
    private String oppUserId;

    /**
     * 对方账户名称
     */
    private String oppAcctName;

    /**
     * 操作类型1-正常，2-调账,3-冲正
     */
    private String operType;

    /**
     * 被冲销标志 (Y已冲销  N无冲销)
     */
    private String writeoffFlag;

    /**
     * 关联流水号（关联流水号）
     */
    private String linkTranId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人（系统默认“99”，其它为运营人员ID号）
     */
    private String createUser;

    /**
     * 最后更新
     */
    private String updateUser;

    /**
     * 新增时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 账户类型(01-基本户)
     */
    private String acctType;

    /**
     * 对方账户类型(01-基本户)
     */
    private String oppAcctType;
}
