package com.epyc.ycdbbase.entity;

import javax.persistence.*;

/**
 * @Description  
 * @Author  wqy
 * @Date 2019-09-21 
 */

@Entity
@Table ( name ="yc_platform_info" )
public class YcPlatformInfoEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 对接平台IP
	 */
   	@Column(name = "to_ip" )
	private String toIp;

	/**
	 * 对接平台IP端口
	 */
   	@Column(name = "to_port" )
	private String toPort;

	/**
	 * 对接平台名称
	 */
   	@Column(name = "to_platform_name" )
	private String toPlatformName;

	/**
	 * 是否启动，0启动，1未启动
	 */
   	@Column(name = "state" )
	private Long state;

	/**
	 * 协议类型
	 */
	@Column(name = "agreement_type" )
   	private String agreementType;

	/**
	 * 对接平台密码
	 */
	@Column(name = "pw" )
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
		return this.state;
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
