package com.ep.yc.yctcpanalysis.entity;

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
	//device_mn

	/**
	 * 设备MN
	 */
	private String deviceMn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOrganizationNum() {
		return organizationNum;
	}

	public void setOrganizationNum(String organizationNum) {
		this.organizationNum = organizationNum;
	}

	public String getOrganizationName() {
		return organizationName;
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

	public String getDeviceMn() {
		return deviceMn;
	}

	public void setDeviceMn(String deviceMn) {
		this.deviceMn = deviceMn;
	}
}
