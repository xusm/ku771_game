package com.ku771.pojo;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * @title：会员信息
 * @class：Member   
 * @author：Eric
 * @date：2018年12月6日
 */
public class Member implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//会员ID
	private Integer memberId;
	//会员账号
	private String memberNum;
	//昵称
	private String nickName;
	//经销商账号
	private String distributorNum;
	//登录密码
	private String password;
	//手机号码
	private String phoneNum;
	//邮箱
	private String email;
	//会员级别
	private String level;
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
	//提款密码
	private String drawingPassword;
	//是否使用提款密码；0：使用，1：不使用
	private Integer isUseDrawingPassword;
	//提款次数
	private Integer drwingFrequency;
	
	public Member() {
		super();
	}

	public Member(Integer memberId, String memberNum, String nickName,
			String distributorNum, String password, String phoneNum,
			String email, String level, Integer accpetDiscount,
			String weChatNum, BigInteger qQNum, String province, String city,
			String address, String drawingPassword,
			Integer isUseDrawingPassword) {
		super();
		this.memberId = memberId;
		this.memberNum = memberNum;
		this.nickName = nickName;
		this.distributorNum = distributorNum;
		this.password = password;
		this.phoneNum = phoneNum;
		this.email = email;
		this.level = level;
		this.accpetDiscount = accpetDiscount;
		this.weChatNum = weChatNum;
		QQNum = qQNum;
		this.province = province;
		this.city = city;
		this.address = address;
		this.drawingPassword = drawingPassword;
		this.isUseDrawingPassword = isUseDrawingPassword;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDistributorNum() {
		return distributorNum;
	}

	public void setDistributorNum(String distributorNum) {
		this.distributorNum = distributorNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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

	public String getDrawingPassword() {
		return drawingPassword;
	}

	public void setDrawingPassword(String drawingPassword) {
		this.drawingPassword = drawingPassword;
	}

	public Integer getIsUseDrawingPassword() {
		return isUseDrawingPassword;
	}

	public void setIsUseDrawingPassword(Integer isUseDrawingPassword) {
		this.isUseDrawingPassword = isUseDrawingPassword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getDrwingFrequency() {
		return drwingFrequency;
	}

	public void setDrwingFrequency(Integer drwingFrequency) {
		this.drwingFrequency = drwingFrequency;
	}
}
