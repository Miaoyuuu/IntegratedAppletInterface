package com.yzjttcgs.integratedappletinterface.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-18 10:25:12
 */
@Data
public class TAcctInfo {
    // 账户标识
    private String acctId;
    // 会员标识,会员规则:9开头为C会员;1开头为B会员;内部账会员(001-内部户)+(01-备付金账号,02-待清算户,03-代扣长款户,04-代付长款,05-代扣短款,06-代付短款)+(2位标识)
    private String userId;
    // 会员姓名
    private String acctName;
    // 账户状态（1-正常，2-冻结）
    private String acctStatus;
    // 账户类型(01-基本户,02-待结算账户，03-备付金账户，04-待清算账户，05-代扣长款户，06-代付长款户，07-代扣短款户，08-代付短款户，09-贷款收款户、10-贷款付款户、11-积分户)
    private String acctType;
    // 业务来源（1-APP，2-OMS，0-系统）
    private String sourceFrom;
    // 账户等级（0-临时账户，1-一类账户，2-二类账户，3-三类账户）
    private String acctLevel;
    // 昨日余额
    private long yesterdayBalance;
    // 当前余额
    private long balance;
    // 冻结金额
    private long frozenBalance;
    // 开户日期
    private Integer openDate;
    // 上次动户日期
    private Integer changeDate;
    // 备注
    private String remark;
    // 创建人（系统默认“99”，其它为运营人员ID号）
    private String createUser;
    // 最后更新
    private String updateUser;
    // 新增时间
    private Date createTime;
    // 最后更新时间
    private Date updateTime;
    // 单日当前已使用余额(默认为0分)，在账户等级更新时无须重新初始化
    private Long limitedBalanceByDay;
    // 日限额更新时间
    private Date liBalDayUptTime;
    // 账户等级已使用余额(默认为0分)，在账户等级更新时无须重新初始化
    private Long limitedBalanceByAll;
    // 账户等级限额更新时间
    private Date liBalAllUptTime;
}
