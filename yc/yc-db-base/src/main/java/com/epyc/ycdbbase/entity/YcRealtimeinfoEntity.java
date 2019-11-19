package com.epyc.ycdbbase.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  wqy
 * @Date 2019-09-10 
 */

@Entity
@Table ( name ="yc_realtimeinfo" )
public class YcRealtimeinfoEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 设备编号
	 */
   	@Column(name = "ydi_device_num" )
	private String ydiDeviceNum;

	/**
	 * 设备名称
	 */
   	@Column(name = "ydi_device_name" )
	private String ydiDeviceName;

	/**
	 * 所属企业
	 */
   	@Column(name = "ydi_belong_to_enterprise" )
	private String ydiBelongToEnterprise;

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

	/**
	 * 设备MN
	 */
	@Column(name = "ydi_device_mn" )
	private String ydiDeviceMn;

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

	public String getYdiDeviceMn() {
		return ydiDeviceMn;
	}

	public void setYdiDeviceMn(String ydiDeviceMn) {
		this.ydiDeviceMn = ydiDeviceMn;
	}
}
