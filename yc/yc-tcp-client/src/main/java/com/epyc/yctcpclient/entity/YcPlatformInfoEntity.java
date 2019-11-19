package com.epyc.yctcpclient.entity;

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

}
