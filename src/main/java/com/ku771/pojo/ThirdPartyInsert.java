package com.ku771.pojo;

import java.io.Serializable;

/**
 * @title：第三方平台接入表信息
 * @class：ThirdPartyInsert
 * @author：Eric
 * @date：2018年12月14日
 */
public class ThirdPartyInsert implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//第三方平台接入表ID
	private Integer id;
	
	//关联会员ID
	private Integer memberId;
	
	//关联子账户ID
	private Integer sonId;
	
	//当前接入状态;0:未接入；1：已接入
	private Integer insertStatus;
	
	
	public ThirdPartyInsert() {
		super();
	}

	public ThirdPartyInsert(Integer id, Integer memberId, Integer sonId,
			Integer insertStatus) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.sonId = sonId;
		this.insertStatus = insertStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getSonId() {
		return sonId;
	}

	public void setSonId(Integer sonId) {
		this.sonId = sonId;
	}

	public Integer getInsertStatus() {
		return insertStatus;
	}

	public void setInsertStatus(Integer insertStatus) {
		this.insertStatus = insertStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
