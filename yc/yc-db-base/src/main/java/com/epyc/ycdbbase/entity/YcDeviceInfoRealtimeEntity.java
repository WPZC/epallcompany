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
@Table ( name ="yc_device_realtime" )
public class YcDeviceInfoRealtimeEntity {


	@Id
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
	 * pm2.5
	 */
	@Column(name = "pm25" )
	private String pm25;

	/**
	 * pm10
	 */
	@Column(name = "pm10" )
	private String pm10;

	/**
	 * 噪音
	 */
	@Column(name = "noise" )
	private String noise;

	/**
	 * 空气温度
	 */
	@Column(name = "air_temperature" )
	private String airTemperature;

	/**
	 * 空气湿度
	 */
	@Column(name = "air_humidity" )
	private String airHumidity;

	/**
	 * 风速
	 */
	@Column(name = "wind_speed" )
	private Double windSpeed;

	/**
	 * 风向
	 */
	@Column(name = "wind_direction" )
	private String windDirection;

	/**
	 * tsp
	 */
	@Column(name = "tsp" )
	private String tsp;

	/**
	 * 富氧因子
	 */
	@Column(name = "oxygen_factor" )
	private String oxygenFactor;

	/**
	 * 大气压力
	 */
	@Column(name = "atmospheric_pressure" )
	private String atmosphericPressure;

	/**
	 * 更新时间
	 */
	@Column(name = "updatetime" )
	private Date updatetime;

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
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
