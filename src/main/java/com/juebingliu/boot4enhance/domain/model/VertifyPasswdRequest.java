package com.juebingliu.boot4enhance.domain.model;

public class VertifyPasswdRequest {
	private String versionNo;//	接口版本号
	private String acqId;//	来源渠道
	private String channelId;//	业务渠道ID
	private String merchId;//	商户ID
	private String bizId;//	请求单号
	private String sendDate;//	订单日期
	private String amount;//	交易金额
	private String ccy;//	交易币种
	private String txnTp;//	交易类型
	private String bankId;//	银行编码
	private String accountNo;//	银行卡号
	private String mobileNum;//	手机号
	private String cardTp;//	借贷标示
	private String userID;//	用户编号
	private String sendTime;//	提交时间
	private String remark;//	备注
	private String pageUrl;//	页面跳转地址
	private String notifyUrl;//	异步通知地址
	private String signInfo;//	签名结果
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public String getAcqId() {
		return acqId;
	}
	public void setAcqId(String acqId) {
		this.acqId = acqId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getMerchId() {
		return merchId;
	}
	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public String getTxnTp() {
		return txnTp;
	}
	public void setTxnTp(String txnTp) {
		this.txnTp = txnTp;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getCardTp() {
		return cardTp;
	}
	public void setCardTp(String cardTp) {
		this.cardTp = cardTp;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getSignInfo() {
		return signInfo;
	}
	public void setSignInfo(String signInfo) {
		this.signInfo = signInfo;
	}
	
}
