package com.epyc.ycdbbase.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  wqy
 * @Date 2019-09-17 
 */

@Entity
@Table ( name ="yc_device_info" )
public class YcDeviceInfoEntity  {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id" )
	private Long id;

	/**
	 * 设备号
	 */
   	@Column(name = "device_num" )
	private String deviceNum;

	/**
	 * 设备名称
	 */
   	@Column(name = "device_name" )
	private String deviceName;

	/**
	 * 录入时间
	 */
   	@Column(name = "input_time" )
	private Date inputTime;

	/**
	 * 地址
	 */
   	@Column(name = "address" )
	private String address;

	/**
	 * 经度
	 */
   	@Column(name = "longitude" )
	private String longitude;

	/**
	 * 纬度
	 */
   	@Column(name = "latitude" )
	private String latitude;

	/**
	 * 备注
	 */
   	@Column(name = "remarks" )
	private String remarks;

	/**
	 * 组织机构编号
	 */
   	@Column(name = "organization_num" )
	private String organizationNum;

	/**
	 * 组织机构名称
	 */
   	@Column(name = "organization_name" )
	private String organizationName;

	/**
	 * 视频编码
	 */
   	@Column(name = "video_code" )
	private String videoCode;

	/**
	 * 对接平台
	 */
   	@Column(name = "to_platform" )
	private String toPlatform;
   	//device_mn

	/**
	 * 设备MN
	 */
	@Column(name = "device_mn" )
	private String deviceMn;

	public String getDeviceMn() {
		return deviceMn;
	}

	public void setDeviceMn(String deviceMn) {
		this.deviceMn = deviceMn;
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
		return this.videoCode;
	}

	public void setVideoCode(String videoCode) {
		this.videoCode = videoCode;
	}

	public String getToPlatform() {
		return this.toPlatform;
	}

	public void setToPlatform(String toPlatform) {
		this.toPlatform = toPlatform;
	}

}
