package com.yzjttcgs.integratedappletinterface.domain.dto;

public class ParkPayNoticeDto extends ParkDto{

    private static final long serialVersionUID = 1L;
    
    private String ordid ;
    private String payamt ;
    private String paytime ;
    private String status ;
    private String payseq ;
    private String payplt ;
    private String parkpayssn ;
	private String promoteamount ; //优惠金额
	private String paychnlseq ;  //支付流水号 改 支付渠道订单号
    private String attach;      //备用字段,Json格式字符串:{}
    private String mac;      //报文通讯mac
	private String secpayplt;//二级支付平台
	private String payflowno;//支付渠道流水号

	public String getSecpayplt() {
		return secpayplt;
	}

	public void setSecpayplt(String secpayplt) {
		this.secpayplt = secpayplt;
	}

	public String getPayflowno() {
		return payflowno;
	}

	public void setPayflowno(String payflowno) {
		this.payflowno = payflowno;
	}

	public String getPromoteamount() {
		return promoteamount;
	}

	public void setPromoteamount(String promoteamount) {
		this.promoteamount = promoteamount;
	}

	public String getPayamt() {
		return payamt;
	}

	public void setPayamt(String payamt) {
		this.payamt = payamt;
	}

	public String getPaytime() {
		return paytime;
	}

	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayseq() {
		return payseq;
	}

	public void setPayseq(String payseq) {
		this.payseq = payseq;
	}

	public String getPayplt() {
		return payplt;
	}

	public void setPayplt(String payplt) {
		this.payplt = payplt;
	}

	public String getParkpayssn() {
		return parkpayssn;
	}

	public void setParkpayssn(String parkpayssn) {
		this.parkpayssn = parkpayssn;
	}

	public String getOrdid() {
        return ordid;
    }

    public void setOrdid(String ordid) {
        this.ordid = ordid;
    }

    
    
    public String getPaychnlseq() {
		return paychnlseq;
	}

	public void setPaychnlseq(String paychnlseq) {
		this.paychnlseq = paychnlseq;
	}

	public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
    
    
}
