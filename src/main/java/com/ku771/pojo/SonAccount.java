package com.ku771.pojo;

import java.io.Serializable;

/**
 * @title：子账户
 * @class：MainAccount
 * @author：Eric
 * @date：2018年12月10日
 */
public class SonAccount implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//子账户ID
	private Integer sonId;
	
	//子账户名称
	private String sonName;
	
	//智能切换路线
	private String lineAddr;
	
	//路线状态
	private Integer lineStatus;
	
	//备注信息
	private String remark;

	public SonAccount() {
		super();
	}

	public SonAccount(Integer sonId, String sonName, String lineAddr,
			Integer lineStatus, String remark) {
		super();
		this.sonId = sonId;
		this.sonName = sonName;
		this.lineAddr = lineAddr;
		this.lineStatus = lineStatus;
		this.remark = remark;
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
