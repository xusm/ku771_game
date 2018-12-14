package com.ku771.pojo.example;

import java.io.Serializable;

/**
 * @title：子账户(模板，用于加载下拉框)
 * @class：SonAccountLoadExample
 * @author：Eric
 * @date：2018年12月11日
 */
public class SonAccountLoadExample implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//子账户ID
	private Integer sonId;
	
	//子账户名称
	private String sonName;
	
	public SonAccountLoadExample() {
		super();
	}

	public SonAccountLoadExample(Integer sonId, String sonName) {
		super();
		this.sonId = sonId;
		this.sonName = sonName;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
