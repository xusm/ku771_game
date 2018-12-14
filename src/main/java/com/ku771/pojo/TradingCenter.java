package com.ku771.pojo;

import java.io.Serializable;
import java.math.BigInteger;

public class TradingCenter implements Serializable{

	private static final long serialVersionUID = 1L;
	//交易记录ID
	private Integer tradingId;
	
	//关联会员ID
	private Integer memberId;
	
	//交易时间
	private String tradingDate;
	
	//交易类别；0：存款；1：提款；2：转出；3：转入
	private Integer tradingType;
	
	//交易内容
	private String tradingContent;
	
	//汇款账号
	private BigInteger accountNum;
	
	//交易金额
	private double tradingMoney;
	
	//交易状态
	private Integer tradingStatus;
	
	//优惠
	private String discount;
	
	//手续费
	private double serviceCharge;
	
	//余额
	private double balance;
	
	public TradingCenter() {
		super();
	}

	public TradingCenter(Integer tradingId, Integer memberId,
			String tradingDate, Integer tradingType, String tradingContent,
			BigInteger accountNum, double tradingMoney, Integer tradingStatus,
			String discount, double serviceCharge, double balance) {
		super();
		this.tradingId = tradingId;
		this.memberId = memberId;
		this.tradingDate = tradingDate;
		this.tradingType = tradingType;
		this.tradingContent = tradingContent;
		this.accountNum = accountNum;
		this.tradingMoney = tradingMoney;
		this.tradingStatus = tradingStatus;
		this.discount = discount;
		this.serviceCharge = serviceCharge;
		this.balance = balance;
	}

	public Integer getTradingId() {
		return tradingId;
	}

	public void setTradingId(Integer tradingId) {
		this.tradingId = tradingId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getTradingDate() {
		return tradingDate;
	}

	public void setTradingDate(String tradingDate) {
		this.tradingDate = tradingDate;
	}

	public Integer getTradingType() {
		return tradingType;
	}

	public void setTradingType(Integer tradingType) {
		this.tradingType = tradingType;
	}

	public String getTradingContent() {
		return tradingContent;
	}

	public void setTradingContent(String tradingContent) {
		this.tradingContent = tradingContent;
	}

	public BigInteger getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(BigInteger accountNum) {
		this.accountNum = accountNum;
	}

	public double getTradingMoney() {
		return tradingMoney;
	}

	public void setTradingMoney(double tradingMoney) {
		this.tradingMoney = tradingMoney;
	}

	public Integer getTradingStatus() {
		return tradingStatus;
	}

	public void setTradingStatus(Integer tradingStatus) {
		this.tradingStatus = tradingStatus;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
