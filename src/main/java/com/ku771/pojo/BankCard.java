package com.ku771.pojo;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @title：银行卡信息
 * @class：BankCard
 * @author：Eric
 * @date：2018年12月8日
 */
public class BankCard implements Serializable {

	private static final long serialVersionUID = 1L;
	//银行卡ID
	private Integer bankCardId;
	//关联会员ID
	private Integer memberId;
	//户名
	private String accountName;
	//银行卡号
	private BigInteger bankCardNum;
	//所属银行
	private String belongCard;
	//省份
	private String province;
	//城市
	private String city;
	
	//预留字段
	private String reserve1;
	private String reserve2;
	private String reserve3;
	private String reserve4;
	private String reserve5;
	private String reserve6;
	private String reserve7;
	
	public BankCard() {
		super();
	}

	public BankCard(Integer bankCardId, Integer memberId, String accountName,
			BigInteger bankCardNum, String belongCard, String province,
			String city, String reserve1, String reserve2, String reserve3,
			String reserve4, String reserve5, String reserve6, String reserve7) {
		super();
		this.bankCardId = bankCardId;
		this.memberId = memberId;
		this.accountName = accountName;
		this.bankCardNum = bankCardNum;
		this.belongCard = belongCard;
		this.province = province;
		this.city = city;
		this.reserve1 = reserve1;
		this.reserve2 = reserve2;
		this.reserve3 = reserve3;
		this.reserve4 = reserve4;
		this.reserve5 = reserve5;
		this.reserve6 = reserve6;
		this.reserve7 = reserve7;
	}

	public Integer getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(Integer bankCardId) {
		this.bankCardId = bankCardId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigInteger getBankCardNum() {
		return bankCardNum;
	}

	public void setBankCardNum(BigInteger bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	public String getBelongCard() {
		return belongCard;
	}

	public void setBelongCard(String belongCard) {
		this.belongCard = belongCard;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	public String getReserve6() {
		return reserve6;
	}

	public void setReserve6(String reserve6) {
		this.reserve6 = reserve6;
	}

	public String getReserve7() {
		return reserve7;
	}

	public void setReserve7(String reserve7) {
		this.reserve7 = reserve7;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
