package com.ku771.pojo.example;

import java.io.Serializable;

/**
 * @title：账户
 * @class：MainAccount
 * @author：Eric
 * @date：2018年12月9日
 */
public class AccountExample implements Serializable{

	private static final long serialVersionUID = 1L;
	//账户ID
	private Integer mainId;

	//关联子账户表ID
	private Integer sonId;
	
	//关联子账户表名称
	private String sonName;
	
	//关联会员ID
	private Integer memberId;
	
	//余额
	private double balance;
	
	//关联子账户表切换线路
	private String lineAddr;
	
	//关联子账户表线路状态
	private Integer lineStatus;
	
	//关联子账户表备注信息
	private String remark;
	
	public AccountExample() {
		super();
	}

	public AccountExample(Integer mainId, Integer sonId, String sonName,
			Integer memberId, double balance, String lineAddr,
			Integer lineStatus, String remark) {
		super();
		this.mainId = mainId;
		this.sonId = sonId;
		this.sonName = sonName;
		this.memberId = memberId;
		this.balance = balance;
		this.lineAddr = lineAddr;
		this.lineStatus = lineStatus;
		this.remark = remark;
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

	public String getSonName() {
		return sonName;
	}

	public void setSonName(String sonName) {
		this.sonName = sonName;
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

	public String getLineAddr() {
		return lineAddr;
	}

	public void setLineAddr(String lineAddr) {
		this.lineAddr = lineAddr;
	}

	public Integer getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(Integer lineStatus) {
		this.lineStatus = lineStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
