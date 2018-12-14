package com.ku771.pojo;

import java.io.Serializable;

/**
 * @title：账户
 * @class：MainAccount
 * @author：Eric
 * @date：2018年12月9日
 */
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	//账户ID
	private Integer mainId;

	//子账户表ID
	private Integer sonId;
	
	//关联会员ID
	private Integer memberId;
	
	//余额
	private double balance;
	
	public Account() {
		super();
	}

	public Account(Integer mainId, Integer sonId, Integer memberId,
			double balance) {
		super();
		this.mainId = mainId;
		this.sonId = sonId;
		this.memberId = memberId;
		this.balance = balance;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public Integer getSonId() {
		return sonId;
	}

	public void setSonId(Integer sonId) {
		this.sonId = sonId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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
