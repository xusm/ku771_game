package com.ku771.pojo.example;

import java.io.Serializable;

/**
 * @title：转账记录分页模板
 * @class：TransferRecordPageExample
 * @author：Eric
 * @date：2018年12月11日
 */
public class TransferRecordPageExample implements Serializable{

	private static final long serialVersionUID = 1L;
	//转账记录ID
	private Integer recordId;
	
	//转出账户ID
	private Integer turnOutId;

	//关联转出账户名称
	private Integer turnOutName;
	
	//转入账户ID
	private Integer turnInId;

	//关联转入账户名称
	private Integer turnInName;
	
	//转入金额
	private double turnMoney;
	
	//转账日期
	private String turnDate;
	
	//状态
	private Integer turnStatus;

	public TransferRecordPageExample() {
		super();
	}

	public TransferRecordPageExample(Integer recordId, Integer turnOutId,
			Integer turnOutName, Integer turnInId, Integer turnInName,
			double turnMoney, String turnDate, Integer turnStatus) {
		super();
		this.recordId = recordId;
		this.turnOutId = turnOutId;
		this.turnOutName = turnOutName;
		this.turnInId = turnInId;
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

	public Integer getTurnOutId() {
		return turnOutId;
	}

	public void setTurnOutId(Integer turnOutId) {
		this.turnOutId = turnOutId;
	}

	public Integer getTurnOutName() {
		return turnOutName;
	}

	public void setTurnOutName(Integer turnOutName) {
		this.turnOutName = turnOutName;
	}

	public Integer getTurnInId() {
		return turnInId;
	}

	public void setTurnInId(Integer turnInId) {
		this.turnInId = turnInId;
	}

	public Integer getTurnInName() {
		return turnInName;
	}

	public void setTurnInName(Integer turnInName) {
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
