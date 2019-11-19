package com.ep.yc.ycview.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Description  
 * @Author  wqy
 * @Date 2019-09-17 
 */

public class YcDeviceInfoRealtimeEntity {



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
	 * 组织机构编号
	 */
	private String organizationNum;

	/**
	 * 组织机构名称
	 */
	private String organizationName;


	/**
	 * pm2.5
	 */
	private String pm25;

	/**
	 * pm10
	 */
	private String pm10;

	/**
	 * 噪音
	 */
	private String noise;

	/**
	 * 空气温度
	 */
	private String airTemperature;

	/**
	 * 空气湿度
	 */
	private String airHumidity;

	/**
	 * 风速
	 */
	private Double windSpeed;

	/**
	 * 风向
	 */
	private String windDirection;

	/**
	 * tsp
	 */
	private String tsp;

	/**
	 * 富氧因子
	 */
	private String oxygenFactor;

	/**
	 * 大气压力
	 */
	private String atmosphericPressure;

	/**
	 * 更新时间
	 */
	private String updatetime;

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

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

	public String getPm25() {
		return pm25;
	}

	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}

	public String getPm10() {
		return pm10;
	}

	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}

	public String getNoise() {
		return noise;
	}

	public void setNoise(String noise) {
		this.noise = noise;
	}

	public String getAirTemperature() {
		return airTemperature;
	}

	public void setAirTemperature(String airTemperature) {
		this.airTemperature = airTemperature;
	}

	public String getAirHumidity() {
		return airHumidity;
	}

	public void setAirHumidity(String airHumidity) {
		this.airHumidity = airHumidity;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getTsp() {
		return tsp;
	}

	public void setTsp(String tsp) {
		this.tsp = tsp;
	}

	public String getOxygenFactor() {
		return oxygenFactor;
	}

	public void setOxygenFactor(String oxygenFactor) {
		this.oxygenFactor = oxygenFactor;
	}

	public String getAtmosphericPressure() {
		return atmosphericPressure;
	}

	public void setAtmosphericPressure(String atmosphericPressure) {
		this.atmosphericPressure = atmosphericPressure;
	}
}
