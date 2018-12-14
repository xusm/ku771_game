package com.ku771.pojo.example;

import java.io.Serializable;

/**
 * @title：转账记录(模板)
 * @class：TransferRecordExample
 * @author：Eric
 * @date：2018年12月11日
 */
public class TransferRecordExample implements Serializable{

	private static final long serialVersionUID = 1L;
	//转账记录ID
	private Integer recordId;
	
	//转出账户名称
	private String turnOutName;

	//转入账户名称
	private String turnInName;
	
	//转入金额
	private double turnMoney;
	
	//转账日期
	private String turnDate;
	
	//转账状态
	private Integer turnStatus;

	public TransferRecordExample() {
		super();
	}

	public TransferRecordExample(Integer recordId, String turnOutName,
			String turnInName, double turnMoney, String turnDate,
			Integer turnStatus) {
		super();
		this.recordId = recordId;
		this.turnOutName = turnOutName;
		this.turnInName = turnInName;
		this.turnMoney = turnMoney;
		this.turnDate = turnDate;
		this.turnStatus = turnStatus;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getTurnOutName() {
		return turnOutName;
	}

	public void setTurnOutName(String turnOutName) {
		this.turnOutName = turnOutName;
	}

	public String getTurnInName() {
		return turnInName;
	}

	public void setTurnInName(String turnInName) {
		this.turnInName = turnInName;
	}

	public double getTurnMoney() {
		return turnMoney;
	}

	public void setTurnMoney(double turnMoney) {
		this.turnMoney = turnMoney;
	}

	public String getTurnDate() {
		return turnDate;
	}

	public void setTurnDate(String turnDate) {
		this.turnDate = turnDate;
	}

	public Integer getTurnStatus() {
		return turnStatus;
	}

	public void setTurnStatus(Integer turnStatus) {
		this.turnStatus = turnStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
