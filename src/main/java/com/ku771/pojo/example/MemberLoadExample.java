package com.ku771.pojo.example;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * @title：加载会员资料模板
 * @class：MemberLoadExample
 * @author：Eric
 * @date：2018年12月7日
 */
public class MemberLoadExample implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//会员ID
	private Integer memberId;
	//昵称
	private String nickName;
	//手机号码
	private String phoneNum;
	//邮箱
	private String email;
	//是否接受优惠讯息
	private Integer accpetDiscount;
	//微信号码
	private String weChatNum;
	//QQ号码
	private BigInteger QQNum;
	//省份
	private String province;
	//城市
	private String city;
	//详细收货地址
	private String address;
	//是否使用提款密码；0：使用，1：不使用
	private Integer isUseDrawingPassword;
	
	//管理银行卡资料--户名
	private String accountName;
	
	public MemberLoadExample() {
		super();
	}

	public MemberLoadExample(Integer memberId, String nickName,
			String phoneNum, String email, Integer accpetDiscount,
			String weChatNum, BigInteger qQNum, String province, String city,
			String address, Integer isUseDrawingPassword, String accountName) {
		super();
		this.memberId = memberId;
		this.nickName = nickName;
		this.phoneNum = phoneNum;
		this.email = email;
		this.accpetDiscount = accpetDiscount;
		this.weChatNum = weChatNum;
		QQNum = qQNum;
		this.province = province;
		this.city = city;
		this.address = address;
		this.isUseDrawingPassword = isUseDrawingPassword;
		this.accountName = accountName;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAccpetDiscount() {
		return accpetDiscount;
	}

	public void setAccpetDiscount(Integer accpetDiscount) {
		this.accpetDiscount = accpetDiscount;
	}

	public String getWeChatNum() {
		return weChatNum;
	}

	public void setWeChatNum(String weChatNum) {
		this.weChatNum = weChatNum;
	}

	public BigInteger getQQNum() {
		return QQNum;
	}

	public void setQQNum(BigInteger qQNum) {
		QQNum = qQNum;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getIsUseDrawingPassword() {
		return isUseDrawingPassword;
	}

	public void setIsUseDrawingPassword(Integer isUseDrawingPassword) {
		this.isUseDrawingPassword = isUseDrawingPassword;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
