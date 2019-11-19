package com.ep.yc.ycview.entity;

import java.util.Date;

/**
 * @Description  
 * @Author  wqy
 * @Date 2019-09-17 
 */

public class YcDeviceInfoEntity {


	private Long id;

	/**
	 * 设备号
	 */
	private String deviceNum;

	/**
	 * 设备名称
	 */
	private String deviceName;

	/**
	 * 录入时间
	 */
	private Date inputTime;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 经度
	 */
	private String longitude;

	/**
	 * 纬度
	 */
	private String latitude;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 组织机构编号
	 */
	private String organizationNum;

	/**
	 * 组织机构名称
	 */
	private String organizationName;

	/**
	 * 视频编码
	 */
	private String videoCode;

	/**
	 * 对接平台
	 */
	private String toPlatform;

	/**
	 * 是否在线
	 */
	private String isOnLine;

	/**
	 * MN
	 */
	private String deviceMn;

	public String getDeviceMn() {
		return deviceMn;
	}

	public void setDeviceMn(String deviceMn) {
		this.deviceMn = deviceMn;
	}

	public String getIsOnLine() {
		return isOnLine;
	}

	public void setIsOnLine(String isOnLine) {
		this.isOnLine = isOnLine;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceNum() {
		return this.deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Date getInputTime() {
		return this.inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOrganizationNum() {
		return this.organizationNum;
	}

	public void setOrganizationNum(String organizationNum) {
		this.organizationNum = organizationNum;
	}

	public String getOrganizationName() {
		return this.organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getVideoCode() {
		return videoCode;
	}

	public void setVideoCode(String videoCode) {
		this.videoCode = videoCode;
	}

	public String getToPlatform() {
		return toPlatform;
	}

	public void setToPlatform(String toPlatform) {
		this.toPlatform = toPlatform;
	}
}
