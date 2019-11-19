package com.epyc.yctcpclient.entity;

import java.util.Date;

/**
 * @Description  
 * @Author  wqy
 * @Date 2019-09-10 
 */

public class YcRealtimeinfoEntity {

	private Long id;

	/**
	 * 设备编号
	 */
	private String ydiDeviceNum;

	/**
	 * 设备名称
	 */
	private String ydiDeviceName;

	/**
	 * 所属企业
	 */
	private String ydiBelongToEnterprise;

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
	private Date updatetime;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYdiDeviceNum() {
		return this.ydiDeviceNum;
	}

	public void setYdiDeviceNum(String ydiDeviceNum) {
		this.ydiDeviceNum = ydiDeviceNum;
	}

	public String getYdiDeviceName() {
		return this.ydiDeviceName;
	}

	public void setYdiDeviceName(String ydiDeviceName) {
		this.ydiDeviceName = ydiDeviceName;
	}

	public String getYdiBelongToEnterprise() {
		return this.ydiBelongToEnterprise;
	}

	public void setYdiBelongToEnterprise(String ydiBelongToEnterprise) {
		this.ydiBelongToEnterprise = ydiBelongToEnterprise;
	}

	public String getPm25() {
		return this.pm25;
	}

	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}

	public String getPm10() {
		return this.pm10;
	}

	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}

	public String getNoise() {
		return this.noise;
	}

	public void setNoise(String noise) {
		this.noise = noise;
	}

	public String getAirTemperature() {
		return this.airTemperature;
	}

	public void setAirTemperature(String airTemperature) {
		this.airTemperature = airTemperature;
	}

	public String getAirHumidity() {
		return this.airHumidity;
	}

	public void setAirHumidity(String airHumidity) {
		this.airHumidity = airHumidity;
	}

	public Double getWindSpeed() {
		return this.windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getWindDirection() {
		return this.windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getTsp() {
		return this.tsp;
	}

	public void setTsp(String tsp) {
		this.tsp = tsp;
	}

	public String getOxygenFactor() {
		return this.oxygenFactor;
	}

	public void setOxygenFactor(String oxygenFactor) {
		this.oxygenFactor = oxygenFactor;
	}

	public String getAtmosphericPressure() {
		return this.atmosphericPressure;
	}

	public void setAtmosphericPressure(String atmosphericPressure) {
		this.atmosphericPressure = atmosphericPressure;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
