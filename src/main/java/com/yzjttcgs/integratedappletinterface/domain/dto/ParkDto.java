package com.yzjttcgs.integratedappletinterface.domain.dto;

import com.alibaba.fastjson2.util.DateUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-26 10:35:49
 */
@Data
public class ParkDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String instid;
    private String mchntid;
    private String txntime;
    private String txncode;
    private String seqid;
    private String reqchnl;
    private String mac;

    public String updTxntime() {
        txntime = DateUtils.format(new Date(), "yyyyMMddHHmmss");
        return txntime;
    }

    public String updSeqid() {
        seqid = DateUtils.format(new Date(), "yyyyMMddHHmmssSSS");
        return seqid;

    }
}
