package com.ep.yc.ycview.entity;

/**
 * @Description  
 * @Author  wqy
 * @Date 2019-09-17 
 */

public class YcPlatformInfoEntity {


	private Long id;

	/**
	 * 对接平台IP
	 */
	private String toIp;

	/**
	 * 对接平台IP端口
	 */
	private String toPort;

	/**
	 * 对接平台名称
	 */
	private String toPlatformName;

	/**
	 * 是否启动，0启动，1未启动
	 */
	private Long state;

	/**
	 * 协议类型
	 */
	private String agreementType;

	/**
	 * 对接平台密码
	 */
	private String pw;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToIp() {
		return this.toIp;
	}

	public void setToIp(String toIp) {
		this.toIp = toIp;
	}

	public String getToPort() {
		return this.toPort;
	}

	public void setToPort(String toPort) {
		this.toPort = toPort;
	}

	public String getToPlatformName() {
		return this.toPlatformName;
	}

	public void setToPlatformName(String toPlatformName) {
		this.toPlatformName = toPlatformName;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public String getAgreementType() {
		return agreementType;
	}

	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
}
