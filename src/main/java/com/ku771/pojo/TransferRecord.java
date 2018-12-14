package com.ku771.pojo;

import java.io.Serializable;

/**
 * @title：转账记录
 * @class：TransferTecord
 * @author：Eric
 * @date：2018年12月9日
 */
public class TransferRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	//转账记录ID
	private Integer recordId;
	
	//关联会员ID
	private Integer memberId;
	
	//转出账户ID
	private Integer turnOutId;
	
	//转入账户ID
	private Integer turnInId;
	
	//转入金额
	private double turnMoney;
	
	//转账日期
	private String turnDate;
	
	//转账状态
	private Integer turnStatus;

	public TransferRecord() {
		super();
	}

	public TransferRecord(Integer recordId, Integer memberId,
			Integer turnOutId, Integer turnInId, double turnMoney,
			String turnDate, Integer turnStatus) {
		super();
		this.recordId = recordId;
		this.memberId = memberId;
		this.turnOutId = turnOutId;
		this.turnInId = turnInId;
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

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getTurnOutId() {
		return turnOutId;
	}

	public void setTurnOutId(Integer turnOutId) {
		this.turnOutId = turnOutId;
	}

	public Integer getTurnInId() {
		return turnInId;
	}

	public void setTurnInId(Integer turnInId) {
		this.turnInId = turnInId;
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
